package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.MultiValueMap;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.Candidate;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.ManagedUser;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.repository.CandidateRepository;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.repository.UserRepository;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.stub.TestData;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class FullContainerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    UserRepository userRepository;

    @DisplayName("Verify that a request get the register page will succeed with 200 (ok) response")
    @Test
    public void verifyRequestForRegisterPage() throws Exception {
        mockMvc.perform(post("/register")
                        .contentType(MediaType.TEXT_HTML)
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @DisplayName("Verify that a request get the error-register " +
            "page will redirect user to register page")
    @Test
    public void verifyRequestForErrorRegisterPage() throws Exception {
      ResultActions actions =   mockMvc.perform(post("/register-error")
                        .contentType(MediaType.TEXT_HTML)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection());
      String URL = actions.andReturn().getResponse().getRedirectedUrl();
      assertEquals(URL, "http://localhost/login");
    }


    @DisplayName("Verify that a request to register a new user will succeed with 200 (ok) response")
    @Test
    public void verifyRequestToRegisterNewUser() throws Exception {
        mockMvc.perform(post("/register-save")
                        .contentType(MediaType.TEXT_HTML)
                        .with(csrf())
                        .param("username", "Scotty")
                        .param("password", "Password"))
                .andExpect(status().isOk());
    }

    @DisplayName("Verify that a request to register a new user with complete" +
            "credentials will fail with 400 (error) response")
    @Test
    public void verifyRequestToRegisterNewUserInCompleteCredentials() throws Exception {
        ResultActions actions1 = mockMvc.perform(post("/register-save")
                        .contentType(MediaType.TEXT_HTML)
                        .with(csrf())
                        .param("username", "fake_user"));
        ResultActions actions2 = mockMvc.perform(post("/register-save")
                .with(csrf())
                .param("password", "fake_password"));
        actions1.andExpect(status().is4xxClientError());
        actions2.andExpect(status().is4xxClientError());
    }

    @DisplayName("Verify that a request to the login page contain Login text")
    @Test
    public void verifyRequestForLoginPage() throws Exception {
        mockMvc.perform(get("/login")
                        .contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Log in")))
                .andExpect(content().string(containsStringIgnoringCase("username")))
                .andExpect(content().string(containsStringIgnoringCase("password")));
    }

    @WithMockUser(username = "Admin", password = "password", roles = "ADMIN")
    @DisplayName("Verify that request to view admin page is successful")
    @Test
    public void verifyThatRequestToAdminPageIsSuccessful() throws Exception {

        List<String> expectedUsers = userRepository.findAllManagedUsers()
                .stream()
                .map(ManagedUser::getUsername)
                .collect(Collectors.toList());

        mockMvc.perform(get("/admin")
                        .with(csrf())
                        .contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string(stringContainsInOrder(expectedUsers)));
    }

    @WithMockUser(username = "Admin", password = "password", roles = "ADMIN")
    @DisplayName("Verify that request to update user is successful")
    @Test
    public void verifyThatRequestToUpdateUserIsSuccessful() throws Exception {
        mockMvc.perform(post("/admin").with(csrf())
                        .contentType(MediaType.TEXT_HTML)
                        .param("uid", "1")
                        .param("username", "dave")
                        .param("roles", "ADMIN")
                        .param("action", "update"))
                .andExpect(status().is3xxRedirection());
        ManagedUser user = userRepository.findById(1L).orElse(new ManagedUser());
        assertEquals(user.getUserRole(), "ADMIN");
        assertEquals(user.getUsername(), "dave");
    }

    @DisplayName("Verify that an unauthorized request to update a user will respond with 302 redirect status")
    @Test
    public void verifyThatUnAuthorizedRequestToUpdateUserWillReturnError404() throws Exception {
        mockMvc.perform(post("/admin")
                        .with(csrf())
                        .contentType(MediaType.TEXT_HTML)
                        .param("uid", "1")
                        .param("username", "Richard")
                        .param("roles", "USER")
                        .param("action", "update"))
                .andExpect(status().is3xxRedirection())
                .andExpect(content().string(blankString()));
    }

    @WithMockUser(username = "Admin", password = "password", roles = "ADMIN")
    @DisplayName("Verify that request to delete user is successful")
    @Test
    public void verifyThatRequestToDeleteUserIsSuccessful() throws Exception {
        mockMvc.perform(post("/admin").with(csrf())
                        .contentType(MediaType.TEXT_HTML)
                        .param("uid", "1")
                        .param("username", "Richard")
                        .param("roles", "USER")
                        .param("action", "delete"))
                .andExpect(status().is3xxRedirection());
        ManagedUser user = userRepository.findById(1L)
                .orElse(new ManagedUser());
        assertNull(user.getUserRole());
        assertNull(user.getUsername());
    }

    @DisplayName("Verify that an unauthorized request to delete a user will respond with 302 redirect status")
    @Test
    public void verifyThatUnAuthorizedRequestToDeleteUserWillReturnError404() throws Exception {
        mockMvc.perform(post("/admin")
                        .with(csrf())
                        .contentType(MediaType.TEXT_HTML)
                        .param("uid", "1")
                        .param("username", "Richard")
                        .param("roles", "USER")
                        .param("action", "delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(content().string(blankString()));
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

    @DisplayName("Verify that a request to update a candidate is successful")
    @WithMockUser(username = "Richard", password = "password", roles = "USER")
    @Test
    public void verifyRequestForUpdateIsSuccessful() throws Exception {

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
    public void verifyThatRequestForSearchIsSuccessful() throws Exception {

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

    @DisplayName("Verify that a request to the settings page will produce" +
            "response 200 (ok) with a template containing specific texts")
    @WithMockUser(username = "Faisal", password = "password", roles = "USER")
    @Test
    public void verifyRequestForSettingsPage() throws Exception {
        mockMvc.perform(get("/settings")
                        .with(csrf())
                        .contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Settings")))
                .andExpect(content().string(containsString("Browse")))
                .andExpect(content().string(containsString("Submit")));
    }

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


}
