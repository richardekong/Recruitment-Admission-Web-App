package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.controller;


import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.response.Response;

import static uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.Constant.AUTHENTICATION_ERROR_MESSAGE;


@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        try {
            return "login";
        }catch (BadCredentialsException bce){
            System.err.println(bce.getMessage());
            model.addAttribute("error", new Response(HttpStatus.UNAUTHORIZED.value(), bce.getMessage(), System.currentTimeMillis()));
            return "login";
        }
    }


    @RequestMapping("/login-error")
    public String loginError(Model model) {
        // login failed
        model.addAttribute("error",
                new Response(HttpStatus.UNAUTHORIZED.value(), AUTHENTICATION_ERROR_MESSAGE, System.currentTimeMillis()));
        return "login";
    }

}
