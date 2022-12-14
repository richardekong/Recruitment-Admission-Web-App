package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service.PredictionService;

@RestController
public class OfferPredictionsController {

    private PredictionService predictionService;

    @Autowired
    public void setPredictionService(PredictionService service){
        this.predictionService = service;
    }

    @GetMapping("/ratio")
    public double predictRatio(){
        return predictionService.eliminateOutliers();
    }
}

