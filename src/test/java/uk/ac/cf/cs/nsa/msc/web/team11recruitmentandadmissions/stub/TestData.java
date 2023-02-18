package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.stub;

import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.*;

import java.time.LocalDate;

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
}
