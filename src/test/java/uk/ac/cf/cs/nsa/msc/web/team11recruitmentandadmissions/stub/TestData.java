package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.stub;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.method.annotation.RequestParamMapMethodArgumentResolver;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.*;

import java.lang.reflect.Field;
import java.net.http.HttpRequest;
import java.time.LocalDate;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public interface TestData {

    static Candidate createCandidate() {
        Candidate candidate = new Candidate();
        candidate.setUCASCardiffCourseCode("B720");
        candidate.setCardiffCourseCode("UFBWMIDA");
        candidate.setRecordFirstCreated(LocalDate.of(2017, 10, 20));
        candidate.setEntryYear("2018/9");
        candidate.setStudentNo("23232323");
        candidate.setPersonalID("1345678902");
        candidate.setApplicationStatusCode(ApplicationStatusCode.APPLICATION);
        candidate.setLatestDecisionCode("code");
        candidate.setFirstName("Marry");
        candidate.setSurname("Chaplin");
        candidate.setDateOfBirth(LocalDate.of(1991, 1, 1));
        candidate.setGender(Gender.FEMALE);
        candidate.setFeeStatus(FeeStatus.HOME);
        candidate.setCorrespondenceLangWelsh(YesOrNoOption.NO);
        candidate.setWelshSpeaker(WelshSpeaker.NO);
        candidate.setCountryOfDomicile("Wales");
        candidate.setNationality("United Kingdom");
        candidate.setHomeEmail("N/A");
        candidate.setDateReceived("24.10.17");
        candidate.setContextualFlag(YesOrNoOption.NO);
        candidate.setApplicationStatusHCare("CFUF");
        candidate.setApplicationStatusComments("granted");
        candidate.setFeeStatusComments("");
        candidate.setHighestLevelQualification("A Level");
        candidate.setGradesAchieved("Pending");
        candidate.setKeepingWarmEmailSent(YesOrNoOption.YES);
        candidate.setTotalPersonalStatementScore(59);
        candidate.setInviteInterview(YesOrNoOption.NO);
        candidate.setInterviewDate(LocalDate.of(2018, 2, 14));
        candidate.setInterviewInviteComments("None");
        candidate.setTotalInterviewScore(46);
        candidate.setFTPChecked(YesOrNoOption.YES);
        candidate.setOfferConditions("None");
        candidate.setNonStandardQualificationsChaserEmail(YesOrNoOption.NO);
        candidate.setGradesAchievedAfter("Pending");
        candidate.setConfirmationComments("None");
        candidate.setConfirmationComments("None");
        candidate.setOfferEmailSent(YesOrNoOption.YES);
        candidate.setIssueDate("None");
        candidate.setDBSCertNumber("183483973475");
        candidate.setFAStatus("None");
        candidate.setUpdateService("None");
        candidate.setEssentialToDos("None");
        candidate.setEnrolmentCriteriaComments("None");
        return candidate;
    }

    static MultiValueMap<String, String> createCandidateMap(Candidate candidate) {

        MultiValueMap<String, String> candidateMap = new LinkedMultiValueMap<>();
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        for (Field field : candidate.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            String typeName = field.getType().getTypeName();
            String fieldName = field.getName();
            try {
                Object value = field.get(candidate);
                switch (typeName) {
                    case "Gender":
                        params.add(fieldName, ((Gender) value).getGender());
                        break;
                    case "ApplicationStatusCode":
                        params.add(fieldName, ((ApplicationStatusCode) value).getCode());
                        break;
                    case "FeeStatus":
                        params.add(fieldName, ((FeeStatus) value).getFeeStatus());
                        break;
                    case "WelshSpeaker":
                        params.add(typeName, ((WelshSpeaker) value).getResponse());
                        break;
                    case "YesOrNoOption":
                        params.add(fieldName, ((YesOrNoOption) value).getOption());
                        break;
                    case "LocalDate":
                        LocalDate date = (LocalDate) value;
                        String dateString = String.format("%d-%d-%d",
                                date.getYear(),
                                date.getMonthValue(),
                                date.getDayOfMonth()
                        );
                        params.add(fieldName, dateString);
                        break;
                    default:
                        params.add(fieldName, String.valueOf(value));
                        break;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            field.setAccessible(false);
        }
        return params;
    }

}
