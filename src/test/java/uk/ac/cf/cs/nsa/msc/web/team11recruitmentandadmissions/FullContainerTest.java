package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class FullContainerTest {


    @Autowired
    private MockMvc mockMvc;


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
