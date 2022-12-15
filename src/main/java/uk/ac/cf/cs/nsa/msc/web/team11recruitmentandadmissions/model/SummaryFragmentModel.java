package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model;

import org.springframework.ui.Model;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service.CandidateService;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service.PlacesOfferedService;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service.PredictionService;

public interface SummaryFragmentModel {

    default void setModelsAttributesForSummaryFragment(
            Model model,
            PredictionService predictionService,
            PlacesOfferedService placesOfferedService,
            CandidateService candidateService) {
        model.addAttribute("offersRecommended", predictionService.offersRecommended());
        model.addAttribute("placesOffered", placesOfferedService.getMostRecentPlacesOffered());
        model.addAttribute("offersSent", candidateService.countCandidatesByOfferEmailSent(YesOrNoOption.YES));
    }
}
