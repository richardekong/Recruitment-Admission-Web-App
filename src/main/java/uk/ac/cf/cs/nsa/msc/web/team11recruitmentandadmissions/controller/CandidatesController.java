package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.Candidate;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.response.CustomException;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.response.Response;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service.CandidateService;

import java.util.List;
import java.util.Optional;

@Controller
public class CandidatesController {

    private CandidateService candidateService;


    @Autowired
    public void setCandidateService(CandidateService service) {
        this.candidateService = service;
    }

    @GetMapping("/candidates")
    public String showCandidatesPage(Model model) {
        Optional<List<Candidate>> unConfirmedCandidates = Optional.of(candidateService.findAll());
        unConfirmedCandidates.ifPresentOrElse(
                candidates -> {
                    model.addAttribute("candidates", candidates);
                },
                () ->{
                    HttpStatus status = HttpStatus.NO_CONTENT;
                    model.addAttribute("error",new Response(status.value(), status.getReasonPhrase(), System.currentTimeMillis()));
                    throw new CustomException(HttpStatus.NO_CONTENT.getReasonPhrase(), HttpStatus.NO_CONTENT);
                });

        model.addAttribute("Candidates", unConfirmedCandidates.get());
        return "candidates";
    }

}
