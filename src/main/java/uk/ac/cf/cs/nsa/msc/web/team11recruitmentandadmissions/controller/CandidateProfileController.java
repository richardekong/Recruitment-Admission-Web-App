package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.Candidate;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.SummaryFragmentModel;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.response.CustomException;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.response.Response;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service.CandidateService;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service.PlacesOfferedService;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service.PredictionService;

import java.util.Optional;

@Controller
public class CandidateProfileController implements SummaryFragmentModel {

    private CandidateService candidateService;

    private PredictionService predictionService;

    private PlacesOfferedService placesOfferedService;

    @Autowired
    void setCandidateService(CandidateService service) {
        this.candidateService = service;
    }

    @Autowired
    void setPredictionService(PredictionService service){
        this.predictionService = service;
    }

    @Autowired
    void setPlacesOfferedService(PlacesOfferedService service){
        this.placesOfferedService = service;
    }

    @GetMapping("/profile/{studentNo}")
    public String showCandidateProfilePage(@PathVariable String studentNo, Model model) {
        Optional<Candidate> unConfirmedCandidate = Optional.of(candidateService.findByStudentNo(studentNo));
        unConfirmedCandidate.ifPresentOrElse(
                candidate -> {
                    model.addAttribute("Candidate", candidate);
                    System.out.println(candidate);
                    },
                () -> {
                    String errorMessage = HttpStatus.NOT_FOUND.getReasonPhrase();
                    HttpStatus status = HttpStatus.NOT_FOUND;
                    model.addAttribute("error", new Response(
                            status.value(),
                            errorMessage,
                            System.currentTimeMillis()
                    ));
                    throw new CustomException(errorMessage, status);
                });
        model.addAttribute("Candidate", unConfirmedCandidate.get());
        setModelsAttributesForSummaryFragment(model, predictionService, placesOfferedService, candidateService);
        return "candidate-profile";
    }


    @Override
    public void setModelsAttributesForSummaryFragment(
            Model model,
            PredictionService predictionService,
            PlacesOfferedService placesOfferedService,
            CandidateService candidateService) {
        SummaryFragmentModel.super.setModelsAttributesForSummaryFragment(model, predictionService, placesOfferedService,candidateService);
    }
}

