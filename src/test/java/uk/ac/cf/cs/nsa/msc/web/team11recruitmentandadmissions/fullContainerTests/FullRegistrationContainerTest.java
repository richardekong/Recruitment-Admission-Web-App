package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.fullContainerTests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("registertest")
@SpringBootTest
@AutoConfigureMockMvc
public class FullRegistrationContainerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;

    @Order(1)
    @DisplayName("Verify that a request get the register page will succeed with 200 (ok) response")
    @Test
    public void verifyRequestForRegisterPage() throws Exception {
        mockMvc.perform(post("/register")
                        .contentType(MediaType.TEXT_HTML)
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @Order(2)
    @DisplayName("Verify that a request to register a new user will succeed with 200 (ok) response")
    @Test
    public void verifyRequestToRegisterNewUser() throws Exception {

        mockMvc.perform(post("/register-save")
                        .contentType(MediaType.TEXT_HTML)
                        .with(csrf())
                        .param("username", "Bennitoe10")
                        .param("password", "password"))
                .andExpect(status().is3xxRedirection());
    }

    @Order(3)
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


    @Order(4)
    @DisplayName("Verify that a request to register a new user with incomplete" +
            "credentials will fail with 400 (error) response")
    @Test
    public void verifyRequestToRegisterNewUserInCompleteCredentials() throws Exception {
        ResultActions actions1 = mockMvc.perform(post("/register-save")
                .contentType(MediaType.TEXT_HTML)
                .with(csrf())
                .param("username", "fake_user"));
        ResultActions actions2 = mockMvc.perform(post("/register-save")
                .with(csrf())
                .param("password", ""));
        actions1.andExpect(status().is4xxClientError());
        actions2.andExpect(status().is4xxClientError());
    }




}
