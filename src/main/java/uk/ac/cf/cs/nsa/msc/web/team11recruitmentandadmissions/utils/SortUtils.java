package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.Candidate;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.response.Response;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service.CandidateSortingService;

import java.time.format.DateTimeParseException;
import java.util.List;

import static uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.Constant.PAGE_SIZE;
import static uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.Constant.START_PAGE;

public class SortUtils {

    public static List<Candidate> sort(Model model, CandidateSortingService sortingService, String sortAttribute, Sort.Direction direction) {
        List<Candidate> sortedCandidates = List.of();
        switch (sortAttribute) {
            case "studentNo":
                sortedCandidates = sortingService.sort(createPageAndSortRequest(direction, "studentNo")).getContent();
                break;
            case "gender":
                sortedCandidates = sortingService.sortByGender(createPageAndSortRequest(direction, "gender")).getContent();
                break;
            case "feeStatus":
                sortedCandidates = sortingService.sortByFeeStatus(createPageAndSortRequest(direction, "feeStatus")).getContent();
                break;
            case "names":
                sortedCandidates = sortingService.sort(createPageAndSortRequest(direction, "firstName", "surname")).getContent();
                break;
            case "surname":
                sortedCandidates = sortingService.sort(createPageAndSortRequest(direction,"surname")).getContent();
                break;
            case "firstName":
                sortedCandidates = sortingService.sort(createPageAndSortRequest(direction, "firstName")).getContent();
                break;
            case "applicationStatusCode":
                sortedCandidates = sortingService.sortByApplicationStatusCode(createPageAndSortRequest(direction, "applicationStatusCode")).getContent();
                break;
            case "recordFirstCreated":
                try {
                    sortedCandidates = sortingService.sort(createPageAndSortRequest(direction, "recordFirstCreated")).getContent();
                } catch (DateTimeParseException e) {
                    model.addAttribute("sortError", new Response(HttpStatus.BAD_REQUEST.value(), "Wrong Date format, please use YYYY-MM-DD", System.currentTimeMillis()));
                }
                break;
            case "offerCondition":
                sortedCandidates = sortingService.sort(createPageAndSortRequest(direction, "offerCondition")).getContent();
                break;
            case "personalID":
                sortedCandidates = sortingService.sort(createPageAndSortRequest(direction, "personalID")).getContent();
                break;
            case "dateOfBirth":
                try {
                    sortedCandidates = sortingService.sort(createPageAndSortRequest(direction, "dateOfBirth")).getContent();
                } catch (DateTimeParseException e) {
                    model.addAttribute("sortError", new Response(HttpStatus.BAD_REQUEST.value(), "Wrong Date format, please use YYYY-MM-DD", System.currentTimeMillis()));
                }
                break;
            case "totalPersonalStatementScore":
                sortedCandidates = sortingService.sort(createPageAndSortRequest(direction, "totalPersonalStatementScore")).getContent();
                break;
            case "totalInterviewScore":
                sortedCandidates = sortingService.sort(createPageAndSortRequest(direction, "totalInterviewScore")).getContent();
                break;
            case "offerEmailSent":
                sortedCandidates = sortingService.sortByOfferEmailSent(createPageAndSortRequest(direction, "offerEmailSent")).getContent();
                break;
            case "inviteInterview":
                sortedCandidates = sortingService.sortByInviteInterview(createPageAndSortRequest(direction, "inviteInterview")).getContent();
                break;
            case "nationality":
                sortedCandidates = sortingService.sort(createPageAndSortRequest(direction, "nationality")).getContent();
                break;
            case "correspondenceLangWelsh":
                sortedCandidates = sortingService.sortByCorrespondenceLangWelsh(createPageAndSortRequest(direction, "correspondenceLangWelsh")).getContent();
                break;
            case "welshSpeaker":
                sortedCandidates = sortingService.sortByWelshSpeaker(createPageAndSortRequest(direction, "welshSpeaker")).getContent();
                break;
            case "contextualFlag":
                sortedCandidates = sortingService.sortByContextualFlag(createPageAndSortRequest(direction, "contextualFlag")).getContent();
                break;
            case "keepingWarmEmailSent":
                sortedCandidates = sortingService.sortByKeepingWarmEmailSent(createPageAndSortRequest(direction, "keepingWarmEmailSent")).getContent();
                break;
            case "FTPChecked":
                sortedCandidates = sortingService.sortByFTPChecked(createPageAndSortRequest(direction, "FTPChecked")).getContent();
                break;
            case "nonStandardQualificationsChaserEmail":
                sortedCandidates = sortingService.sortByNonStandardQualificationsChaserEmail(createPageAndSortRequest(direction, "nonStandardQualificationsChaserEmail")).getContent();
                break;
            case "entryYear":
                sortedCandidates = sortingService.sort(createPageAndSortRequest(direction, "entryYear")).getContent();
                break;
            case "latestDecisionCode":
                sortedCandidates = sortingService.sort(createPageAndSortRequest(direction, "latestDecisionCode")).getContent();
                break;
            case "countryOfDomicile":
                sortedCandidates = sortingService.sort(createPageAndSortRequest(direction, "countryOfDomicile")).getContent();
                break;
            case "homeEmail":
                sortedCandidates = sortingService.sort(createPageAndSortRequest(direction, "homeEmail")).getContent();
                break;
            case "applicationStatusHCare":
                sortedCandidates = sortingService.sort(createPageAndSortRequest(direction, "applicationStatusHCare")).getContent();
                break;
            case "interviewDate":
                sortedCandidates = sortingService.sort(createPageAndSortRequest(direction, "interviewDate")).getContent();
                break;
            default:
                sortedCandidates = sortingService.sortNothing().getContent();
        }
        return sortedCandidates;
    }

    private static PageRequest createPageAndSortRequest(Sort.Direction direction, String... properties) {
        return PageRequest.of(START_PAGE, PAGE_SIZE, Sort.by(direction, properties));
    }
}
