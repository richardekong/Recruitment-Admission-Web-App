package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.controller.*;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.mapper.UserMapper;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.Candidate;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.repository.CandidateRepository;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.security.MyUserDetailsServiceImpl;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service.*;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.stub.TestData;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebMvcTest(CandidatesController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class CandidatesControllerLightWeightTest {


    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CandidateServiceImpl candidateService;
    @MockBean
    private CandidateSortingServiceImpl sortingService;
    @MockBean
    private PredictionServiceImpl predictionService;
    @MockBean
    private PlacesOfferedServiceImpl placesOfferedService;
    @MockBean
    private MyUserDetailsServiceImpl userDetailsService;
    @MockBean
    private UserMapper userMapper;

    @WithMockUser(username = "Richard", password = "password", roles = "USER")
    @Test
    public void verifyCandidatesPageRequest() throws Exception{
        List<Candidate> candidates = List.of(TestData.createCandidate());
        given(candidateService.findAll()).willReturn(candidates);
        mockMvc.perform(get("/candidates").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Richard")));
    }

}

