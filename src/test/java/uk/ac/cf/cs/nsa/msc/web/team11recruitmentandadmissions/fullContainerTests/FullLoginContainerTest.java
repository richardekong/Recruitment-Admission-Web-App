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
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.repository.UserRepository;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class FullLoginContainerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;

    @DisplayName("Verify that a request to the login page contain Login text")
    @Test
    public void verifyRequestForLoginPage() throws Exception {
        mockMvc.perform(get("/login")
                        .contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Login")))
                .andExpect(content().string(containsStringIgnoringCase("username")))
                .andExpect(content().string(containsStringIgnoringCase("password")));
    }

    @WithMockUser(username = "Richard", password = "password",roles = "USER")
    @DisplayName("Verify that a request to logout will redirect the user to the login page with a 3xx status code")
    @Test
    public void verifyThatRequestToLogoutIsSuccessful() throws Exception{

        String redirectedURL = mockMvc.perform(post("/logout")
                .contentType(MediaType.TEXT_HTML)
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andReturn()
                .getResponse()
                .getRedirectedUrl();
        assertEquals(redirectedURL, "/login");
    }

}
