package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.mapper.UserMapper;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.ManageUser;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    UserMapper userMapper;
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/admin")
    public String userMapper(Model m){
        List<ManageUser> users = userMapper.findAll();
        m.addAttribute("user",users);
        return "admin";
    }
    @RequestMapping("/admin/delete")
    public String deleteUser(Long uid) {
        userMapper.delete(uid);
        return "redirect:/admin";
    }
}
