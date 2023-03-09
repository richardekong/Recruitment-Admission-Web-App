package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.stub.TestData;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * True Unit Test:
 * Simple unit tests on the
 * Candidate object.
 */
class CandidateTest {
    private Candidate candidateStub;

    @BeforeEach
    void setCandidateStub() {
        candidateStub = TestData.createCandidate();
    }

    @DisplayName("Verify getter methods")
    @Test
    void checkGetters() {
        assertEquals(candidateStub.getUCASCardiffCourseCode(), "B720");
        assertNotEquals(candidateStub.getUCASCardiffCourseCode(), "B72O");
        assertEquals(candidateStub.getCardiffCourseCode(), "UFBWMIDA");
        assertNotEquals(candidateStub.getCardiffCourseCode(), "UF8WMIDA");
        assertEquals(candidateStub.getRecordFirstCreated(), LocalDate.of(2017, 10, 20));
        assertEquals(candidateStub.getRecordFirstCreated().getYear(), 2017);
        assertEquals(candidateStub.getRecordFirstCreated().getMonthValue(), 10);
        assertEquals(candidateStub.getRecordFirstCreated().getDayOfMonth(), 20);
        assertEquals(candidateStub.getEntryYear(), "2018/9");
        assertNotEquals(candidateStub.getEntryYear(), "2O18/9");
        assertEquals(candidateStub.getStudentNo(), "23232323");
        assertNotEquals(candidateStub.getStudentNo(), "23252323");
        assertEquals(candidateStub.getPersonalID(), "1345678902");
        assertNotEquals(candidateStub.getPersonalID(), "13456789O2");
        assertEquals(candidateStub.getApplicationStatusCode().getCode(), "A");
        assertEquals(candidateStub.getLatestDecisionCode(), "code");
        assertNotEquals(candidateStub.getLatestDecisionCode(), "c0de");
        assertEquals(candidateStub.getFirstName(), "Marry");
        assertEquals(candidateStub.getSurname(), "Chaplin");
        assertEquals(candidateStub.getDateOfBirth(), LocalDate.of(1991, 1, 1));
        assertEquals(candidateStub.getDateOfBirth().getYear(), 1991);
        assertEquals(candidateStub.getDateOfBirth().getMonthValue(), 1);
        assertEquals(candidateStub.getDateOfBirth().getDayOfMonth(), 1);
        assertEquals(candidateStub.getGender().getGender(), "F");
        assertEquals(candidateStub.getFeeStatus().getFeeStatus(), "H");
        assertEquals(candidateStub.getCorrespondenceLangWelsh().getOption(), "N");
        assertEquals(candidateStub.getWelshSpeaker().getResponse(), "N");
        assertEquals(candidateStub.getCountryOfDomicile(), "Wales");
        assertEquals(candidateStub.getNationality(), "United Kingdom");
        assertEquals(candidateStub.getHomeEmail(), "N/A");
        assertEquals(candidateStub.getDateReceived(), "24.10.17");
        assertEquals(candidateStub.getContextualFlag().getOption(), "N");
        assertEquals(candidateStub.getApplicationStatusHCare(), "CFUF");
        assertEquals(candidateStub.getApplicationStatusComments(), "granted");
        assertEquals(candidateStub.getFeeStatusComments(), "");
        assertEquals(candidateStub.getHighestLevelQualification(), "A Level");
        assertEquals(candidateStub.getGradesAchieved(), "Pending");
        assertEquals(candidateStub.getKeepingWarmEmailSent().getOption(), "Y");
        assertEquals(candidateStub.getTotalPersonalStatementScore(), 59);
        assertEquals(candidateStub.getInviteInterview().getOption(), "N");
        assertEquals(candidateStub.getInterviewDate(), LocalDate.of(2018, 2, 14));
        assertEquals(candidateStub.getInterviewDate().getYear(), 2018);
        assertEquals(candidateStub.getInterviewDate().getMonthValue(), 2);
        assertEquals(candidateStub.getInterviewDate().getDayOfMonth(), 14);
        assertEquals(candidateStub.getInterviewInviteComments(), "None");
        assertEquals(candidateStub.getTotalInterviewScore(), 46);
        assertEquals(candidateStub.getFTPChecked().getOption(), "Y");
        assertEquals(candidateStub.getOfferConditions(), "None");
        assertEquals(candidateStub.getNonStandardQualificationsChaserEmail().getOption(), "N");
        assertEquals(candidateStub.getGradesAchieved(),"Pending");
        assertEquals(candidateStub.getGradesAchievedAfter(), "Pending");
        assertEquals(candidateStub.getConfirmationComments(), "None");
    }

}