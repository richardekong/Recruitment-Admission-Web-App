package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.controller;

import org.apache.poi.sl.draw.geom.GuideIf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.response.Response;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service.UserService;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.ManagedUser;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.SummaryFragmentModel;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service.CandidateService;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service.PlacesOfferedService;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service.PredictionService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.lang.String.format;

@Controller
public class AdminController implements SummaryFragmentModel {
    private UserService userService;

    private CandidateService candidateService;

    private PredictionService predictionService;

    private PlacesOfferedService placesOfferedService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

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
        List<ManagedUser> users = userService.findAllManagedUsers();
        m.addAttribute("users", users);
        setModelsAttributesForSummaryFragment(m, predictionService, placesOfferedService, candidateService);
        return "admin";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin")
    public String modifyUser(@RequestParam Map<String, Object> params, Model model) {
        //obtain the id from the request parameters
        Long id = Long.valueOf((String) params.getOrDefault("uid",0L));
        //look up a user with this id
        Optional<ManagedUser> optionalUser = userService.findById(id);
        //act upon the user in database with the parameters supplied
        String actionToPerform = (String) params.get("action");
        switch (actionToPerform){
            case "update":
                performUpdateAction(params, model, optionalUser);
                break;
            case "delete":
                performDeleteAction(model, optionalUser);
                break;
            default:
                break;

        }
        setModelsAttributesForSummaryFragment(model, predictionService, placesOfferedService, candidateService);
        return "redirect:/admin";
    }

    private void performDeleteAction(Model model, Optional<ManagedUser> optionalUser) {
        if (optionalUser.isPresent()){
            ManagedUser userToDelete = optionalUser.get();
            userService.delete(userToDelete.getUid());
            model.addAttribute("deleteSuccess", new Response(
                    HttpStatus.TEMPORARY_REDIRECT.value(),
                    format("%s deleted", userToDelete.getUsername()),
                    System.currentTimeMillis()
            ));
        }else {
            model.addAttribute("deleteError", new Response(
                    HttpStatus.NOT_FOUND.value(),
                    "User not Found",
                    System.currentTimeMillis()
            ));
        }
    }

    private void performUpdateAction(Map<String, Object> params, Model model, Optional<ManagedUser> optionalUser) {
        if (optionalUser.isPresent()) {
            ManagedUser user = optionalUser.get();
            if (params.get("username") != null) {
                user.setUsername((String) params.get("username"));
            }
            if (params.get("roles") != null) {
                user.setUserRole((String) params.get("roles"));
            }
            userService.updateUser(user);
            model.addAttribute("modificationSuccess", new Response(
                    HttpStatus.TEMPORARY_REDIRECT.value(),
                    format("%s modified", user.getUsername()),
                    System.currentTimeMillis()
            ));
        } else {
            model.addAttribute("modificationError", new Response(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "User not found",
                    System.currentTimeMillis()
            ));
        }
    }

}

