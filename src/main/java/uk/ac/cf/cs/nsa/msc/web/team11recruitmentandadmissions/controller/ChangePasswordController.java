//package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.ManageUser ;
//import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service.UserMapper ;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import javax.annotation.Resource;
//
//@Controller
//public class ChangePasswordController {
//    @Resource
//    private UserMapper UserMapper;
//
//    @RequestMapping("/changePassword")
//    public String register() {
//        return "adminchangepassword";
//    }
//
//    @RequestMapping("/changePassword-error")
//    public String registerError(Model model) {
//        model.addAttribute("error", true);
//        return "adminchangepassword";
//    }
//
//    @RequestMapping("/changePassword-save")
//    public String updateUser(@ModelAttribute ManageUser manageUser,
//                               Model model) {
//        if (manageUser.getUsername() == null || manageUser.getPassword() == null) {
//            model.addAttribute("error", true);
//            return "adminchangepassword";
//        }
//        try {
//            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//            String password = bCryptPasswordEncoder.encode(manageUser.getPassword());
//            manageUser.setPassword(password);
//            UserMapper.update(manageUser);
//            return "redirect:/admin";
//        } catch (Exception e) {
//            model.addAttribute("error", true);
//            return "adminchangepassword";
//        }
//    }
//}
