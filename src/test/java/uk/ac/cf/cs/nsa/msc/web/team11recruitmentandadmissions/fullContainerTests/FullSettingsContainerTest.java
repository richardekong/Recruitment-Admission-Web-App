package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.fullContainerTests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class FullSettingsContainerTest {

    @Autowired
    private MockMvc mockMvc;


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

}
