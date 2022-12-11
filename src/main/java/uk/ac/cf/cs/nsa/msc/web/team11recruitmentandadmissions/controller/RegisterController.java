package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.ManageUser ;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.mapper.UserMapper ;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
@Controller
public class RegisterController {

    @Resource
    private UserMapper UserMapper;

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/register-error")
    public String registerError(Model model) {
        model.addAttribute("error", true);
        return "register";
    }

    @RequestMapping("/register-save")
    public String registerSave(@ModelAttribute ManageUser manageUser,
                               Model model) {
        if (manageUser.getUsername() == null || manageUser.getPassword() == null || manageUser.getUserRole() == null) {
            model.addAttribute("error", true);
            return "register";
        }
        try {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String password = bCryptPasswordEncoder.encode(manageUser.getPassword());
            manageUser.setPassword(password);
            UserMapper.insert(manageUser);
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("error", true);
            return "register";
        }
    }
}