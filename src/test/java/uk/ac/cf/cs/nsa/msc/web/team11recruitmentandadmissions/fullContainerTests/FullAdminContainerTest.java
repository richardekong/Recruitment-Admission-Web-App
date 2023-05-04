package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.fullContainerTests;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.ManagedUser;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.blankString;
import static org.hamcrest.Matchers.stringContainsInOrder;
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
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FullAdminContainerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;

    @Order(1)
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

    @Order(2)
    @WithMockUser(username = "Admin", password = "password", roles = "ADMIN")
    @DisplayName("Verify that request to update user is successful")
    @Test
    public void verifyThatRequestToUpdateUserIsSuccessful() throws Exception {
        mockMvc.perform(post("/admin").with(csrf())
                        .contentType(MediaType.TEXT_HTML)
                        .param("uid", "1")
                        .param("username", "dave")
                        .param("roles", "USER")
                        .param("action", "update"))
                .andExpect(status().is3xxRedirection());
        ManagedUser user = userRepository.findById(1L).orElse(new ManagedUser());
        System.out.println(user);
        assertEquals(user.getUserRole(), "USER");
        assertEquals(user.getUsername(), "dave");
    }


    @Order(3)
    @DisplayName("Verify that an unauthorized request to update a user will respond with 302 redirect status")
    @Test
    public void verifyThatUnAuthorizedRequestToUpdateUserWillReturnResponse302() throws Exception {
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

    @Order(4)
    @DisplayName("Verify that an unauthorized request to delete a user will respond with 302 redirect status")
    @Test
    public void verifyThatUnAuthorizedRequestToDeleteUserWillReturnResponse404() throws Exception {
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

    @Order(5)
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

}

