package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CandidateProfileController {

    @GetMapping("/profile")
    public String showCandidateProfilePage() {
        return "candidate-profile";
    }


}
