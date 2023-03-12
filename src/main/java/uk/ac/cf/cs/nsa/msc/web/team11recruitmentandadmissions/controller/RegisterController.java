package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.ManagedUser;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.response.CustomException;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
import java.util.Map;

@Controller
public class RegisterController {

    @Resource
    private UserService userService;

    @RequestMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public String register() {
        return "register";
    }

    @RequestMapping("/register-error")
    public String registerError(Model model) {
        model.addAttribute("error", true);
        return "register";
    }

    @PostMapping("/register-save")
    public String registerUser(@RequestParam Map<String,Object> params, Model model) {
        String username = (String) params.getOrDefault("username", "");
        String password = (String) params.getOrDefault("password", "");

        if ((username == null || password == null)
                || (username.isEmpty() || password.isEmpty())) {
            model.addAttribute("error", true);
            throw new CustomException(
                    "Incomplete Credentials",
                    HttpStatus.BAD_REQUEST
            );
        }

        try {
            BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
            ManagedUser userToSave = new ManagedUser();
            userToSave.setUserId(0L);
            userToSave.setUsername(username);
            userToSave.setUserRole("USER");
            userToSave.setPassword(bcrypt.encode(password));
            System.out.println(userToSave);
            userService.insert(userToSave);
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("error", true);
            throw new CustomException(
                    "Incomplete Credentials",
                    HttpStatus.BAD_REQUEST
            );
        }
    }

}