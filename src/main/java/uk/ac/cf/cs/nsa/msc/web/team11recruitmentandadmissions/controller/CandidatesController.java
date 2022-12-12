package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.*;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.response.CustomException;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.response.Response;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service.CandidateService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
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

    private void validateRequestThenUpdate(Map<String, Object> candidateMap, Candidate candidateToUpdate) {
        if (candidateMap.containsKey("UCASCardiffCourseCode") && candidateMap.get("UCASCardiffCourseCode") != null) {
            candidateToUpdate.setUCASCardiffCourseCode((String) candidateMap.get("UCASCardiffCourseCode"));
        }
        if (candidateMap.containsKey("cardiffCourseCode") && candidateMap.get("cardiffCourseCode") != null) {
            candidateToUpdate.setCardiffCourseCode((String) candidateMap.get("cardiffCourseCode"));
        }
        if (candidateMap.containsKey("recordFirstCreated") && candidateMap.get("recordFirstCreated") != null) {
            candidateToUpdate.setRecordFirstCreated(LocalDate.parse((String) candidateMap.get("recordFirstCreated")));
        }
        if (candidateMap.containsKey("entryYear") && candidateMap.get("entryYear") != null) {
            candidateToUpdate.setEntryYear((String) candidateMap.get("entryYear"));
        }
        if (candidateMap.containsKey("personalID") && candidateMap.get("personalID") != null) {
            candidateToUpdate.setPersonalID((String) candidateMap.get("personalID"));
        }
        if (candidateMap.containsKey("applicationStatusCode") && candidateMap.get("applicationStatusCode") != null) {
            switch (candidateMap.get("applicationStatusCode").toString()) {
                case "A": {
                    candidateToUpdate.setApplicationStatusCode(ApplicationStatusCode.APPLICATION);
                    break;
                }
                case "GF": {
                    candidateToUpdate.setApplicationStatusCode(ApplicationStatusCode.GATHERED_FIELD_ON_HOLD);
                    break;
                }
                case "OR": {
                    candidateToUpdate.setApplicationStatusCode(ApplicationStatusCode.OTHER_REASON_ON_HOLD);
                    break;
                }
                case "OH": {
                    candidateToUpdate.setApplicationStatusCode(ApplicationStatusCode.AWAITING_INTERVIEW_ON_HOLD);
                    break;
                }
                case "R": {
                    candidateToUpdate.setApplicationStatusCode(ApplicationStatusCode.REJECTED);
                    break;
                }
                case "C": {
                    candidateToUpdate.setApplicationStatusCode(ApplicationStatusCode.CONDITIONAL_OFFER_MADE);
                    break;
                }
                case "CF": {
                    candidateToUpdate.setApplicationStatusCode(ApplicationStatusCode.CONDITIONAL_OFFER_FIRMED);
                    break;
                }
                case "CI": {
                    candidateToUpdate.setApplicationStatusCode(ApplicationStatusCode.CONDITIONAL_OFFER_INSURED);
                    break;
                }
                case "CD": {
                    candidateToUpdate.setApplicationStatusCode(ApplicationStatusCode.CONDITIONAL_OFFER_DECLINED);
                    break;
                }
                case "U": {
                    candidateToUpdate.setApplicationStatusCode(ApplicationStatusCode.UNCONDITIONAL_OFFER_MADE);
                    break;
                }
                case "UF": {
                    candidateToUpdate.setApplicationStatusCode(ApplicationStatusCode.UNCONDITIONAL_OFFER_FIRMED);
                    break;
                }
                case "UI": {
                    candidateToUpdate.setApplicationStatusCode(ApplicationStatusCode.UNCONDITIONAL_OFFER_INSURED);
                    break;
                }
                case "UD": {
                    candidateToUpdate.setApplicationStatusCode(ApplicationStatusCode.UNCONDITIONAL_OFFER_DECLINED);
                    break;
                }
                case "W": {
                    candidateToUpdate.setApplicationStatusCode(ApplicationStatusCode.WITHDRAWN);
                    break;
                }
                default: {
                    break;
                }
            }
        }
        if (candidateMap.containsKey("latestDecisionCode") && candidateMap.get("latestDecisionCode") != null) {
            candidateToUpdate.setLatestDecisionCode((String) candidateMap.get("latestDecisionCode"));
        }
        if (candidateMap.containsKey("firstName") && candidateMap.get("firstName") != null) {
            candidateToUpdate.setFirstName((String) candidateMap.get("firstName"));
        }
        if (candidateMap.containsKey("surname") && candidateMap.get("surname") != null) {
            candidateToUpdate.setSurname((String) candidateMap.get("surname"));
        }
        if (candidateMap.containsKey("dateOfBirth") && candidateMap.get("dateOfBirth") != null) {
            candidateToUpdate.setDateOfBirth(LocalDate.parse((String) candidateMap.get("dateOfBirth")));
        }
        if (candidateMap.containsKey("gender") && candidateMap.get("gender") != null) {
            switch (candidateMap.get("gender").toString()) {
                case "F": {
                    candidateToUpdate.setGender(Gender.FEMALE);
                    break;
                }
                case "M": {
                    candidateToUpdate.setGender(Gender.MALE);
                    break;
                }
                default: {
                    break;
                }
            }
        }
        if (candidateMap.containsKey("feeStatus") && candidateMap.get("feeStatus") != null) {
            switch (candidateMap.get("feeStatus").toString()) {
                case "H": {
                    candidateToUpdate.setFeeStatus(FeeStatus.HOME);
                    break;
                }
                case "I": {
                    candidateToUpdate.setFeeStatus(FeeStatus.INTERNATIONAL);
                    break;
                }
                default: {
                    break;
                }
            }
        }
        if (candidateMap.containsKey("correspondenceLangWelsh") && candidateMap.get("correspondenceLangWelsh") != null) {
            switch (candidateMap.get("correspondenceLangWelsh").toString()) {
                case "Y": {
                    candidateToUpdate.setCorrespondenceLangWelsh(YesOrNoOption.YES);
                    break;
                }
                case "N": {
                    candidateToUpdate.setCorrespondenceLangWelsh(YesOrNoOption.NO);
                    break;
                }
                default: {
                    break;
                }
            }
        }
        if (candidateMap.containsKey("welshSpeaker") && candidateMap.get("welshSpeaker") != null) {
            switch (candidateMap.get("welshSpeaker").toString()) {
                case "Y": {
                    candidateToUpdate.setWelshSpeaker(WelshSpeaker.YES);
                    break;
                }
                case "N": {
                    candidateToUpdate.setWelshSpeaker(WelshSpeaker.NO);
                    break;
                }
                case "N/A": {
                    candidateToUpdate.setWelshSpeaker(WelshSpeaker.NA);
                    break;
                }
                default: {
                    break;
                }
            }
        }
        if (candidateMap.containsKey("countryOfDomicile") && candidateMap.get("countryOfDomicile") != null) {
            candidateToUpdate.setCountryOfDomicile((String) candidateMap.get("countryOfDomicile"));
        }
        if (candidateMap.containsKey("nationality") && candidateMap.get("nationality") != null) {
            candidateToUpdate.setNationality((String) candidateMap.get("nationality"));
        }
        if (candidateMap.containsKey("homeEmail") && candidateMap.get("homeEmail") != null) {
            candidateToUpdate.setHomeEmail((String) candidateMap.get("homeEmail"));
        }
        if (candidateMap.containsKey("dateReceived") && candidateMap.get("dateReceived") != null) {
            candidateToUpdate.setDateReceived((String) candidateMap.get("dateReceived"));
        }
        if (candidateMap.containsKey("contextualFlag") && candidateMap.get("contextualFlag") != null) {
            switch (candidateMap.get("contextualFlag").toString()) {
                case "Y": {
                    candidateToUpdate.setContextualFlag(YesOrNoOption.YES);
                    break;
                }
                case "N": {
                    candidateToUpdate.setContextualFlag(YesOrNoOption.NO);
                    break;
                }
                default: {
                    break;
                }
            }
        }
        if (candidateMap.containsKey("applicationStatusHCare") && candidateMap.get("applicationStatusHCare") != null) {
            candidateToUpdate.setApplicationStatusHCare((String) candidateMap.get("applicationStatusHCare"));
        }
        if (candidateMap.containsKey("applicationStatusComments") && candidateMap.get("applicationStatusComments") != null) {
            candidateToUpdate.setApplicationStatusComments((String) candidateMap.get("applicationStatusComments"));
        }
        if (candidateMap.containsKey("feeStatusComments") && candidateMap.get("feeStatusComments") != null) {
            candidateToUpdate.setFeeStatusComments((String) candidateMap.get("feeStatusComments"));
        }
        if (candidateMap.containsKey("highestLevelQualification") && candidateMap.get("highestLevelQualification") != null) {
            candidateToUpdate.setHighestLevelQualification((String) candidateMap.get("highestLevelQualification"));
        }
        if (candidateMap.containsKey("gradesAchieved") && candidateMap.get("gradesAchieved") != null) {
            candidateToUpdate.setGradesAchieved((String) candidateMap.get("gradesAchieved"));
        }
        if (candidateMap.containsKey("keepingWarmEmailSent") && candidateMap.get("keepingWarmEmailSent") != null) {
            switch (candidateMap.get("keepingWarmEmailSent").toString()) {
                case "Y": {
                    candidateToUpdate.setKeepingWarmEmailSent(YesOrNoOption.YES);
                    break;
                }
                case "N": {
                    candidateToUpdate.setKeepingWarmEmailSent(YesOrNoOption.NO);
                    break;
                }
                default: {
                    break;
                }
            }
        }
        if (candidateMap.containsKey("totalPersonalStatementScore") && candidateMap.get("totalPersonalStatementScore") != null) {
            candidateToUpdate.setTotalPersonalStatementScore(Integer.parseInt((String) candidateMap.get("totalPersonalStatementScore")));
        }
        if (candidateMap.containsKey("inviteInterview") && candidateMap.get("inviteInterview") != null) {
            switch (candidateMap.get("inviteInterview").toString()) {
                case "Y": {
                    candidateToUpdate.setInviteInterview(YesOrNoOption.YES);
                    break;
                }
                case "N": {
                    candidateToUpdate.setInviteInterview(YesOrNoOption.NO);
                    break;
                }
                default: {
                    break;
                }
            }
        }
        if (candidateMap.containsKey("interviewDate") && candidateMap.get("interviewDate") != null) {
            candidateToUpdate.setInterviewDate(LocalDate.parse((String) candidateMap.get("interviewDate")));
        }
        if (candidateMap.containsKey("interviewInviteComments") && candidateMap.get("interviewInviteComments") != null) {
            candidateToUpdate.setInterviewInviteComments((String) candidateMap.get("interviewInviteComments"));
        }
        if (candidateMap.containsKey("totalInterviewScore") && candidateMap.get("totalInterviewScore") != null) {
            candidateToUpdate.setTotalInterviewScore(Integer.parseInt((String) candidateMap.get("totalInterviewScore")));
        }
        if (candidateMap.containsKey("FTPChecked") && candidateMap.get("FTPChecked") != null) {
            switch (candidateMap.get("FTPChecked").toString()) {
                case "Y": {
                    candidateToUpdate.setFTPChecked(YesOrNoOption.YES);
                    break;
                }
                case "N": {
                    candidateToUpdate.setFTPChecked(YesOrNoOption.NO);
                    break;
                }
                default: {
                    break;
                }
            }
        }
        if (candidateMap.containsKey("offerConditions") && candidateMap.get("offerConditions") != null) {
            candidateToUpdate.setOfferConditions((String) candidateMap.get("offerConditions"));
        }
        if (candidateMap.containsKey("nonStandardQualificationsChaserEmail") && candidateMap.get("nonStandardQualificationsChaserEmail") != null) {
            switch (candidateMap.get("nonStandardQualificationsChaserEmail").toString()) {
                case "Y": {
                    candidateToUpdate.setNonStandardQualificationsChaserEmail(YesOrNoOption.YES);
                    break;
                }
                case "N": {
                    candidateToUpdate.setNonStandardQualificationsChaserEmail(YesOrNoOption.NO);
                    break;
                }
                default: {
                    break;
                }
            }
        }
        if (candidateMap.containsKey("gradesAchievedAfter") && candidateMap.get("gradesAchievedAfter") != null) {
            candidateToUpdate.setGradesAchievedAfter((String) candidateMap.get("gradesAchievedAfter"));
        }
        if (candidateMap.containsKey("confirmationComments") && candidateMap.get("confirmationComments") != null) {
            candidateToUpdate.setConfirmationComments((String) candidateMap.get("confirmationComments"));
        }
        if (candidateMap.containsKey("offerEmailSent") && candidateMap.get("offerEmailSent") != null) {
            switch (candidateMap.get("offerEmailSent").toString()) {
                case "Y": {
                    candidateToUpdate.setOfferEmailSent(YesOrNoOption.YES);
                    break;
                }
                case "N": {
                    candidateToUpdate.setOfferEmailSent(YesOrNoOption.NO);
                    break;
                }
                default: {
                    break;
                }
            }
        }
        if (candidateMap.containsKey("issueDate") && candidateMap.get("issueDate") != null) {
            candidateToUpdate.setIssueDate((String) candidateMap.get("issueDate"));
        }
        if (candidateMap.containsKey("DBSCertNumber") && candidateMap.get("DBSCertNumber") != null) {
            candidateToUpdate.setDBSCertNumber((String) candidateMap.get("DBSCertNumber"));
        }
        if (candidateMap.containsKey("FAStatus") && candidateMap.get("FAStatus") != null) {
            candidateToUpdate.setFAStatus((String) candidateMap.get("FAStatus"));
        }
        if (candidateMap.containsKey("updateService") && candidateMap.get("updateService") != null) {
            candidateToUpdate.setUpdateService((String) candidateMap.get("updateService"));
        }
        if (candidateMap.containsKey("essentialToDos") && candidateMap.get("essentialToDos") != null) {
            candidateToUpdate.setEssentialToDos((String) candidateMap.get("essentialToDos"));
        }
        if (candidateMap.containsKey("enrolmentCriteriaComments") && candidateMap.get("enrolmentCriteriaComments") != null) {
            candidateToUpdate.setEnrolmentCriteriaComments((String) candidateMap.get("enrolmentCriteriaComments"));
        }
    }

}

