package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
public class Candidate {
    private final String UCASCardiffCourseCode = "B720";
    private final String cardiffCourseCode = "UFBWMIDA";
    private String recordFirstCreated;
    private LocalDate EntryYear;
    @Id
    private String studentNo;
    private String personalID;
    private ApplicationStatusCode applicationStatusCode;
    private String latestDecisionCode;
    private String FirstName;
    private String Surname;
    private String dateOfBirth;
    private Gender gender;
    private FeeStatus feeStatus;
    private boolean correspondenceLangWelsh;
    private WelshSpeaker welshSpeaker;
    private String countryOfDomicile; //Potential Enum (too complicated)
    private String nationality;
    private String homeEmail;
    private String dateReceived;
    private boolean contextualFlag;
    private String applicationStatusHCare; //Does this change?
    private String applicationStatusComments;
    private String feeStatusComments;
    private String highestLevelQualification; //Potential Enum if they improve their spreadsheet
    private String gradesAchieved;
    private boolean keepingWarmEmailSent;
    private int totalPersonalStatementScore;
    private boolean inviteInterview;
    private String interviewDate;
    private String interviewInviteComments;
    private String totalInterviewScore;
    private boolean FTPChecked;
    private String offerConditions;
    private boolean nonStandardQualificationsChaserEmail;
    private String gradesAchievedAfter; //Grades achieved after application sent
    private String confirmationComments;
    private boolean offerEmailSent;
    private String issueDate;
    private String DBSCertNumber;
    private String FAStatus;
    private String updateService;
    private String essentialToDos;
    private String enrolmentCriteriaComments;
}
