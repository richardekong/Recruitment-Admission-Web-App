package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.fullContainerTests;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.MultiValueMap;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.Candidate;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.repository.CandidateRepository;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.stub.TestData;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class FullCandidatesContainerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CandidateRepository candidateRepository;

    @DisplayName("Verify a request to the candidate page will have response 200 (ok) with" +
            "a template containing 'Richard'")
    @WithMockUser(username = "Faisal", password = "password", roles = "USER")
    @Test
    public void verifyRequestForCandidatesPage() throws Exception {
        mockMvc.perform(get("/candidates")
                        .with(csrf())
                        .contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Richard")));
    }

    @DisplayName("Verify that an unauthorized user is redirected to the login page")
    @Test
    public void verifyUnAuthorizedRequestIsRedirectedToLoginPage() throws Exception {
        mockMvc.perform(get("/candidates")
                        .contentType(MediaType.TEXT_HTML))
                .andExpect(status().is3xxRedirection());
        var redirectedUrl = mockMvc
                .perform(get("/candidates"))
                .andReturn()
                .getResponse().getRedirectedUrl();
        assertEquals(redirectedUrl, "http://localhost/login");
    }

    @DisplayName("Verify that a request to view a candidate's profile page " +
            "produces a 200 (ok) response with a template containing specific candidate properties")
    @WithMockUser(username = "Bhatt", password = "password", roles = "USER")
    @Test
    public void verifyRequestForCandidateProfilePage() throws Exception {
        mockMvc.perform(get("/profile/01932678")
                        .with(csrf())
                        .contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string(containsStringIgnoringCase("Richard")))
                .andExpect(content().string(containsStringIgnoringCase("Ekong")))
                .andExpect(content().string(containsString("B720")));
    }

    @DisplayName("Verify that a request to update a candidate is successful")
    @WithMockUser(username = "Richard", password = "password", roles = "USER")
    @Test
    public void verifyRequestForUpdatingACandidateIsSuccessful() throws Exception {

        Candidate candidateToUpdate = candidateRepository.findCandidateByStudentNo("01932679");
        candidateToUpdate.setTotalInterviewScore(60);
        MultiValueMap<String, String> params = TestData.createCandidateMap(candidateToUpdate);
        mockMvc.perform(post("/candidates/update")
                        .with(csrf())
                        .contentType(MediaType.TEXT_HTML)
                        .params(params))
                .andExpect(status().is3xxRedirection());
        mockMvc.perform(get("/candidates")
                        .with(csrf())
                        .contentType(MediaType.TEXT_HTML))
                .andExpect(content().string(containsString("Candidates")))
                .andExpect(content().string(containsString("Settings")))
                .andExpect(content().string(containsString("Barry")))
                .andExpect(content().string(containsString("60")));
    }

    @WithMockUser(username = "Yibo", password = "password", roles = "USER")
    @DisplayName("Verify that a request to search a candidate is successful")
    @Test
    public void verifyThatRequestForSearchingACandidateIsSuccessful() throws Exception {

        mockMvc.perform(get("/q")
                        .with(csrf())
                        .contentType(MediaType.TEXT_HTML)
                        .param("firstName", "Adele")
                        .param("order", "ASC"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("01932679")));
    }


    @WithMockUser(username = "Richard", password = "password", roles = "USER")
    @DisplayName("Verify that a request to sort candidates is successful")
    @Test
    public void verifyThatRequestToSortCandidateIsSuccessful() throws Exception {
        List<String> expectedSurnames = candidateRepository.findAllCandidates()
                .stream().sorted(Comparator.comparing(Candidate::getSurname))
                .map(Candidate::getSurname)
                .collect(Collectors.toList());

        mockMvc.perform(get("/sortBy")
                        .with(csrf())
                        .contentType(MediaType.TEXT_HTML)
                        .param("attr", "surname")
                        .param("order", "ASC"))
                .andExpect(status().isOk())
                .andExpect(content().string(stringContainsInOrder(expectedSurnames)));
    }



}
