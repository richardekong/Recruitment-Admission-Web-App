package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecruitmentAndAdmissionController {


    @GetMapping("/login")
    public String showLoginPage(){
        return "login";
    }

    @GetMapping("/settings")
    public String showSettingsPage(){
        return "Settings";
    }

    @GetMapping("/profile")
    public String showCandidateProfilePage(){
        return "candidate-profile";
    }

    @GetMapping("/candidates")
    public String showCandidatesPage(){
        return "candidates";
    }
}
