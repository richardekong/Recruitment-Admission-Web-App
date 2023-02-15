package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.utils;

import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.ApplicationStatusCode;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.Candidate;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.YesOrNoOption;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.response.Response;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service.CandidateService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class SearchUtils {

    public static List<Candidate> search(Model model, CandidateService candidateService, AtomicReference<Pageable> pageableAtomicReference, boolean isAscending, Map.Entry<String, String> entry) {
        if (entry.getKey().equalsIgnoreCase("studentNo")) {
            return getSliceOfCandidates(pageableAtomicReference, candidateService, isAscending, entry, Sort.by("studentNo"))
                    .getContent();
        }
        if (entry.getKey().equalsIgnoreCase("surname")) {
            return getSliceOfCandidates(pageableAtomicReference, candidateService, isAscending, entry, Sort.by("surname"))
                    .getContent();
        }
        if (entry.getKey().equalsIgnoreCase("applicationStatusCode")) {
            return getSliceOfCandidates(pageableAtomicReference, candidateService, isAscending, entry, Sort.by("applicationStatusCode"))
                    .getContent();
        }
        if (entry.getKey().equalsIgnoreCase("offerCondition")) {
            return getSliceOfCandidates(pageableAtomicReference, candidateService, isAscending, entry, Sort.by("offerCondition"))
                    .getContent();
        }
        if (entry.getKey().equalsIgnoreCase("recordFirstCreated")) {
            try {
                return getSliceOfCandidates(pageableAtomicReference, candidateService, isAscending, entry, Sort.by("recordFirstCreated"))
                        .getContent();
            } catch (DateTimeParseException e) {
                model.addAttribute("sortError", new Response(HttpStatus.BAD_REQUEST.value(), "Wrong Date format, please use YYYY-MM-DD", System.currentTimeMillis()));

            }
        }
        if (entry.getKey().equalsIgnoreCase("personalID")) {
            return getSliceOfCandidates(pageableAtomicReference, candidateService, isAscending, entry, Sort.by("personalID"))
                    .getContent();
        }
        if (entry.getKey().equalsIgnoreCase("dateOfBirth")) {
            try {
                return getSliceOfCandidates(pageableAtomicReference, candidateService, isAscending, entry, Sort.by("dateOfBirth"))
                        .getContent();
            } catch (DateTimeParseException e) {
                model.addAttribute("sortError", new Response(HttpStatus.BAD_REQUEST.value(), "Wrong Date format, please use YYYY-MM-DD", System.currentTimeMillis()));
            }
        }
        if (entry.getKey().equalsIgnoreCase("totalPersonalStatementScore")) {
            return getSliceOfCandidates(pageableAtomicReference, candidateService, isAscending, entry, Sort.by("totalPersonalStatementScore"))
                    .getContent();
        }
        if (entry.getKey().equalsIgnoreCase("totalInterviewScore")) {
            return getSliceOfCandidates(pageableAtomicReference, candidateService, isAscending, entry, Sort.by("totalInterviewScore"))
                    .getContent();
        }
        if (entry.getKey().equalsIgnoreCase("offerEmailSent")) {
            return getSliceOfCandidates(pageableAtomicReference, candidateService, isAscending, entry, Sort.by("offerEmailSent"))
                    .getContent();
        }
        if (entry.getKey().equalsIgnoreCase("inviteInterview")) {
            return getSliceOfCandidates(pageableAtomicReference, candidateService, isAscending, entry, Sort.by("inviteInterview"))
                    .getContent();
        } else {
            return List.of();
        }
    }

    private static Slice<Candidate> getSliceOfCandidates(
            AtomicReference<Pageable> pageableAtomicReference,
            CandidateService candidateService,
            boolean isAscending,
            Map.Entry<String, String> entry,
            Sort sort) {
        Slice<Candidate> sliceOfCandidates = new SliceImpl<>(List.of());
        pageableAtomicReference.set(PageRequest.of(0, 10, isAscending ? sort.ascending() : sort.descending()));
        switch (entry.getKey()) {
            case "studentNo": {
                sliceOfCandidates = candidateService.findCandidateByStudentNo(entry.getValue(), pageableAtomicReference.get());
                break;
            }
            case "surname": {
                sliceOfCandidates = candidateService.findCandidateBySurname(entry.getValue(), pageableAtomicReference.get());
                break;
            }
            case "offerCondition": {
                sliceOfCandidates = candidateService.findCandidatesByOfferConditions(entry.getValue(), pageableAtomicReference.get());
                break;
            }
            case "recordFirstCreated": {
                sliceOfCandidates = candidateService.findCandidatesByRecordFirstCreated(LocalDate.parse(entry.getValue()), pageableAtomicReference.get());
                break;
            }
            case "personalID": {
                sliceOfCandidates = candidateService.findCandidatesByPersonalID(entry.getValue(), pageableAtomicReference.get());
                break;
            }
            case "dateOfBirth": {

                sliceOfCandidates = candidateService.findCandidatesByDateOfBirth(LocalDate.parse(entry.getValue()), pageableAtomicReference.get());
                break;
            }
            case "totalPersonalStatementScore": {
                sliceOfCandidates = candidateService.findCandidatesByTotalPersonalStatementScore(Integer.parseInt(entry.getValue()), pageableAtomicReference.get());
                break;
            }
            case "totalInterviewScore": {
                sliceOfCandidates = candidateService.findCandidatesByTotalInterviewScore(Integer.parseInt(entry.getValue()), pageableAtomicReference.get());
                break;
            }
            case "inviteInterview": {
                switch (entry.getValue()) {
                    case "Y": {
                        sliceOfCandidates = candidateService.findCandidatesByInviteInterview(YesOrNoOption.YES, pageableAtomicReference.get());
                        break;
                    }
                    case "N": {
                        sliceOfCandidates = candidateService.findCandidatesByInviteInterview(YesOrNoOption.NO, pageableAtomicReference.get());
                        break;
                    }
                    default: break;
                }
                break;
            }
            case "offerEmailSent": {
                switch (entry.getValue()) {
                    case "Y": {
                        sliceOfCandidates = candidateService.findCandidatesByOfferEmailSent(YesOrNoOption.YES, pageableAtomicReference.get());
                        break;
                    }
                    case "N": {
                        sliceOfCandidates = candidateService.findCandidatesByOfferEmailSent(YesOrNoOption.NO, pageableAtomicReference.get());
                        break;
                    }
                    default: break;
                }
                break;
            }
            case "applicationStatusCode": {
                switch (entry.getValue()) {
                    case "A": {
                        sliceOfCandidates = candidateService.findCandidatesByApplicationStatusCode(ApplicationStatusCode.APPLICATION, pageableAtomicReference.get());
                        break;
                    }
                    case "GF": {
                        sliceOfCandidates = candidateService.findCandidatesByApplicationStatusCode(ApplicationStatusCode.GATHERED_FIELD_ON_HOLD, pageableAtomicReference.get());
                        break;
                    }
                    case "OR": {
                        sliceOfCandidates = candidateService.findCandidatesByApplicationStatusCode(ApplicationStatusCode.OTHER_REASON_ON_HOLD, pageableAtomicReference.get());
                        break;
                    }
                    case "OH": {
                        sliceOfCandidates = candidateService.findCandidatesByApplicationStatusCode(ApplicationStatusCode.AWAITING_INTERVIEW_ON_HOLD, pageableAtomicReference.get());
                        break;
                    }
                    case "R": {
                        sliceOfCandidates = candidateService.findCandidatesByApplicationStatusCode(ApplicationStatusCode.REJECTED, pageableAtomicReference.get());
                        break;
                    }
                    case "C": {
                        sliceOfCandidates = candidateService.findCandidatesByApplicationStatusCode(ApplicationStatusCode.CONDITIONAL_OFFER_MADE, pageableAtomicReference.get());
                        break;
                    }
                    case "CF": {
                        sliceOfCandidates = candidateService.findCandidatesByApplicationStatusCode(ApplicationStatusCode.CONDITIONAL_OFFER_FIRMED, pageableAtomicReference.get());
                        break;
                    }
                    case "CI": {
                        sliceOfCandidates = candidateService.findCandidatesByApplicationStatusCode(ApplicationStatusCode.CONDITIONAL_OFFER_INSURED, pageableAtomicReference.get());
                        break;
                    }
                    case "CD": {
                        sliceOfCandidates = candidateService.findCandidatesByApplicationStatusCode(ApplicationStatusCode.CONDITIONAL_OFFER_DECLINED, pageableAtomicReference.get());
                        break;
                    }
                    case "U": {
                        sliceOfCandidates = candidateService.findCandidatesByApplicationStatusCode(ApplicationStatusCode.UNCONDITIONAL_OFFER_MADE, pageableAtomicReference.get());
                        break;
                    }
                    case "UF": {
                        sliceOfCandidates = candidateService.findCandidatesByApplicationStatusCode(ApplicationStatusCode.UNCONDITIONAL_OFFER_FIRMED, pageableAtomicReference.get());
                        break;
                    }
                    case "UI": {
                        sliceOfCandidates = candidateService.findCandidatesByApplicationStatusCode(ApplicationStatusCode.UNCONDITIONAL_OFFER_INSURED, pageableAtomicReference.get());
                        break;
                    }
                    case "UD": {
                        sliceOfCandidates = candidateService.findCandidatesByApplicationStatusCode(ApplicationStatusCode.UNCONDITIONAL_OFFER_DECLINED, pageableAtomicReference.get());
                        break;
                    }
                    case "W": {
                        sliceOfCandidates = candidateService.findCandidatesByApplicationStatusCode(ApplicationStatusCode.WITHDRAWN, pageableAtomicReference.get());
                        break;
                    }
                    default: break;
                }
                break;
            }
            default: break;
        }
        return sliceOfCandidates;
    }


}
