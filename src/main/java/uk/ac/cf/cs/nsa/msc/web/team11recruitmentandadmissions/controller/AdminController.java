package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.mapper.UserMapper;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.ManageUser;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.SummaryFragmentModel;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service.CandidateService;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service.PlacesOfferedService;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service.PredictionService;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

@Controller
public class AdminController implements SummaryFragmentModel {
    @Autowired
    UserMapper userMapper;

    private CandidateService candidateService;

    private PredictionService predictionService;

    private PlacesOfferedService placesOfferedService;


    @Autowired
    public void setCandidateService(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @Autowired
    public void setPredictionService(PredictionService predictionService) {
        this.predictionService = predictionService;
    }

    @Autowired
    public void setPlacesOfferedService(PlacesOfferedService placesOfferedService) {
        this.placesOfferedService = placesOfferedService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/admin")
    public String userMapper(Model m) {
        List<ManageUser> users = userMapper.findAll();
        m.addAttribute("user", users);
        setModelsAttributesForSummaryFragment(m, predictionService, placesOfferedService, candidateService);
        return "admin";
    }

    @RequestMapping("/admin/delete")
    public String deleteUser(Long uid, Model m) {
        userMapper.delete(uid);
        setModelsAttributesForSummaryFragment(m, predictionService, placesOfferedService, candidateService);
        return "redirect:/admin";
    }
}

