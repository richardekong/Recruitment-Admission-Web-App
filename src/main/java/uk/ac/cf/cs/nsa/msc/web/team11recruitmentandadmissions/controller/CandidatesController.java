package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.utils.SortUtils;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.*;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.response.CustomException;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.response.Response;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service.CandidateService;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service.CandidateSortingService;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service.PlacesOfferedService;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service.PredictionService;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.utils.UpdateUtils;

import java.time.LocalDate;
import java.util.*;

import static uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.utils.SortUtils.sort;
import static uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.utils.UpdateUtils.validateRequestThenUpdate;

@Controller
public class CandidatesController implements SummaryFragmentModel {

    private CandidateService candidateService;

    private CandidateSortingService sortingService;

    private PredictionService predictionService;

    private PlacesOfferedService placesOfferedService;

    @Autowired
    public void setCandidateService(CandidateService service) {
        this.candidateService = service;
    }

    @Autowired
    public void setSortingService(CandidateSortingService service) {
        this.sortingService = service;
    }

    @Autowired
    public void setPredictionService(PredictionService service) {
        this.predictionService = service;
    }

    @Autowired
    void setPlacesOfferedService(PlacesOfferedService service) {
        this.placesOfferedService = service;
    }

    @GetMapping("/candidates")
    public String showCandidatesPage(Model model) {
        Optional<List<Candidate>> unConfirmedCandidates = Optional.of(candidateService.findAll());
        unConfirmedCandidates.ifPresentOrElse(
                candidates -> model.addAttribute("candidates", candidates),
                () -> {
                    HttpStatus status = HttpStatus.NO_CONTENT;
                    model.addAttribute("candidateLoadErrorDisplay", new Response(status.value(), status.getReasonPhrase(), System.currentTimeMillis()));
                    throw new CustomException(HttpStatus.NO_CONTENT.getReasonPhrase(), HttpStatus.NO_CONTENT);
                });

        model.addAttribute(
                "candidateLoadSuccessDisplay",
                new Response(HttpStatus.OK.value(), "Candidates Loaded Successfully!", System.currentTimeMillis())
        );
        model.addAttribute("candidates", unConfirmedCandidates.get());
        setModelsAttributesForSummaryFragment(model, predictionService, placesOfferedService, candidateService);
        return "candidates";
    }

    @PostMapping("/candidates/update")
    public String editCandidateRecord(@RequestParam Map<String, Object> candidateMap, Model model) {
        String studentNo = String.valueOf(candidateMap.get("studentNo"));
        Optional<Candidate> unConfirmedCandidateToUpdate = Optional.of(candidateService.findByStudentNo(studentNo));
        unConfirmedCandidateToUpdate.ifPresentOrElse(candidateTobeUpdated -> {
            validateRequestThenUpdate(candidateMap, candidateTobeUpdated);
            candidateService.updateCandidate(candidateTobeUpdated);
        }, () -> {
            model.addAttribute("updateError", new Response(HttpStatus.BAD_REQUEST.value(), "Failed to update this candidate", System.currentTimeMillis()));
            throw new CustomException(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND);
        });
        model.addAttribute("updateSuccess", new Response(HttpStatus.ACCEPTED.value(), "Update accepted", System.currentTimeMillis()));
        return "redirect:/candidates";
    }

    @GetMapping("/q")
    public String queryCandidates(@RequestParam Map<String, String> params, Model model){
        return "candidates";
    }

    @GetMapping("/sortBy")
    public String sortCandidates(@RequestParam Map<String, String> requestParameters, Model model) {
        List<Candidate> sortedCandidates;
        String sortAttribute = requestParameters.get("attr");
        String sortOrder = requestParameters.getOrDefault("order", "ASC");
        boolean isAscending = sortOrder.equalsIgnoreCase("ASC");
        Sort.Direction direction = isAscending ? Sort.Direction.ASC : Sort.Direction.DESC;
        sortedCandidates = sort(model, sortingService, sortAttribute, direction);
        model.addAttribute("candidates", sortedCandidates);
        setModelsAttributesForSummaryFragment(model, predictionService, placesOfferedService, candidateService);
        return "candidates";
    }

    @Override
    public void setModelsAttributesForSummaryFragment(
            Model model,
            PredictionService predictionService,
            PlacesOfferedService placesOfferedService,
            CandidateService candidateService) {
        SummaryFragmentModel.super.setModelsAttributesForSummaryFragment(model, predictionService, placesOfferedService, candidateService);
    }
}

