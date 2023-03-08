package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.controller;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.MultiValueMap;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.ApplicationStatusCode;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.Candidate;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service.*;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.stub.TestData;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.stringContainsInOrder;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.Constant.PAGE_SIZE;
import static uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.Constant.START_PAGE;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@WebMvcTest(CandidatesController.class)
public class CandidatesControllerLightWeightTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CandidateService candidateService;
    @MockBean
    private CandidateSortingService sortingService;
    @MockBean
    private PredictionService predictionService;
    @MockBean
    private PlacesOfferedService placesOfferedService;

    @MockBean
    private UserService userService;


    @DisplayName("Verify that an unauthorized access to the candidates page will " +
            "return unsuccessful with 4xx status code")
    @Test
    public void verifyRedirectForUnAuthorizedAccessToCandidatePage() throws Exception {
        mockMvc.perform(get("/candidates")
                        .with(csrf()))
                .andExpect(status().isUnauthorized());
    }

    @WithMockUser(username = "Richard", password = "password", roles = "USER")
    @DisplayName("Verify that request to get candidate page produces 200 (ok) with " +
            "the specified texts")
    @Test
    public void verifyCandidatesPageRequest() throws Exception {
        List<Candidate> candidates = List.of(TestData.createCandidate());
        given(candidateService.findAll()).willReturn(candidates);
        mockMvc.perform(get("/candidates").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Marry")))
                .andExpect(content().string(containsString("Chaplin")));
        ;
    }

    @WithMockUser(username = "Faisal", password = "password", roles = "USER")
    @DisplayName("Verify that a request to edit a candidate record will return " +
            "successfully with a 302 (redirect) to the candidate page " +
            "with the new changes in place")
    @Test
    public void verifyCandidateUpdateIsSuccessful() throws Exception {
        //given: candidate's detail to update is prepare
        Candidate candidateToUpdate = TestData.createCandidate();
        String studentNo = candidateToUpdate.getStudentNo();
        //given: the prepared details are found when searched for, by student number,
        given(candidateService.findByStudentNo(studentNo)).willReturn(candidateToUpdate);
        //when: the found candidate details are altered
        candidateToUpdate.setTotalInterviewScore(40);
        candidateToUpdate.setApplicationStatusCode(ApplicationStatusCode.REJECTED);
        //given: the changes are persisted
        given(candidateService.updateCandidate(candidateToUpdate)).willReturn(candidateToUpdate);
        MultiValueMap<String, String> params = TestData.createCandidateMap(candidateToUpdate);
        //when: a request are made to submit changes made
        mockMvc.perform(post("/candidates/update")
                        .with(csrf())
                        .contentType(MediaType.TEXT_HTML)
                        .params(params))
                //then: expect 302 redirection request back to candidate page
                .andExpect(status().is3xxRedirection());
        //then: verify that the most recent changes were made
        given(candidateService.findAll()).willReturn(List.of(candidateToUpdate));
        mockMvc.perform(get("/candidates")
                        .with(csrf()))
                .andExpect(content().string(containsString("40")))
                .andExpect(content().string(containsString("R")));
    }

    @WithMockUser(username = "Yinzou", password = "password", roles = "USER")
    @DisplayName("Verify that a request to search a candidate's record " +
            "returns successfully with a 200 (ok)")
    @Test
    public void verifyCandidateSearchISSuccessful() throws Exception {
        Candidate candidateToSearchFor = TestData.createCandidate();

        Sort sort = Sort.by("firstName").ascending();
        PageRequest pageRequest = PageRequest.of(START_PAGE, PAGE_SIZE, sort);
        given(candidateService.findCandidatesByFirstName(candidateToSearchFor.getFirstName(), pageRequest))
                .willReturn(
                        new SliceImpl<>(
                                List.of(candidateToSearchFor),
                                pageRequest, true));
        mockMvc.perform(get("/q")
                        .with(csrf())
                        .contentType(MediaType.TEXT_HTML)
                        .param("firstName", "Marry")
                        .param("order", "ASC"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Chaplin")));
    }

    @WithMockUser(username = "Yibo", password = "password", roles = "USER")
    @DisplayName("Verify that a request to retrieve a sorted list of returns" +
            "successfully with 200 (ok)")
    @Test
    public void verifyRequestForCandidatesAreReturnedIsSorted() throws Exception {
        Candidate candidate1 = TestData.createCandidate();
        Candidate candidate2 = TestData.createCandidate();
        Candidate candidate3 = TestData.createCandidate();
        candidate1.setStudentNo("01");
        candidate2.setStudentNo("02");
        candidate3.setStudentNo("03");
        List<Candidate> candidates = List.of(candidate1, candidate2, candidate3);
        given(candidateService.saveAll(new LinkedList<>(candidates))).willReturn(candidates);
        Sort sortParam = Sort.by("studentNo").ascending();
        PageRequest pr = PageRequest.of(START_PAGE, PAGE_SIZE, sortParam);
        given(sortingService.sort(pr)).willReturn(new SliceImpl<>(candidates, pr, true));
        mockMvc.perform(get("/sortBy").with(csrf())
                        .contentType(MediaType.TEXT_HTML)
                        .param("attr", "studentNo")
                        .param("order", "ASC"))
                .andExpect(status().isOk())
                .andExpect(content().string(stringContainsInOrder(
                        candidates.stream()
                                .map(Candidate::getStudentNo)
                                .collect(Collectors.toList())
                )));
    }


}

