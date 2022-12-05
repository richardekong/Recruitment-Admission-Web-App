package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CandidatesController {

    @GetMapping("/candidates")
    public String showCandidatesPage() {
        return "candidates";
    }

}
