package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class Candidate {
    private String UCASCardiffCourseCode;
    private String cardiffCourseCode;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate recordFirstCreated;
    private String entryYear;
    @Id
    private String studentNo;
    private String personalID;
    private ApplicationStatusCode applicationStatusCode;
    private String latestDecisionCode;
    private String firstName;
    private String Surname;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    private Gender gender;
    private FeeStatus feeStatus;
    private YesOrNoOption correspondenceLangWelsh;
    private WelshSpeaker welshSpeaker;
    private String countryOfDomicile; //Potential Enum (too complicated)
    private String nationality;
    private String homeEmail;
    private String dateReceived;
    private YesOrNoOption contextualFlag;
    private String applicationStatusHCare; //Does this change?
    private String applicationStatusComments;
    private String feeStatusComments;
    private String highestLevelQualification; //Potential Enum if they improve their spreadsheet
    private String gradesAchieved;
    private YesOrNoOption keepingWarmEmailSent;
    private int totalPersonalStatementScore;
    private YesOrNoOption inviteInterview;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate interviewDate;
    private String interviewInviteComments;
    private int totalInterviewScore;
    private YesOrNoOption FTPChecked;
    private String offerConditions;
    private YesOrNoOption nonStandardQualificationsChaserEmail;
    private String gradesAchievedAfter; //Grades achieved after application sent
    private String confirmationComments;
    private YesOrNoOption offerEmailSent;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String issueDate;
    private String DBSCertNumber;
    private String FAStatus;
    private String updateService;
    private String essentialToDos;
    private String enrolmentCriteriaComments;
}
