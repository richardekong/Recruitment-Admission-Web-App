package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.temporal.Temporal;

@Data
@NoArgsConstructor
@Entity
public class Candidate {
    private final String UCASCardiffCourseCode = "B720";
    private final String CardiffCourseCode = "UFBWMIDA";
    private String RecordFirstCreated;
    private LocalDate EntryYear;
    @Id
    private String studentNo;
    private String PersonalID;
    private ApplicationStatusCode applicationStatusCode;
    private String LatestDecisionCode;
    private String FirstName;
    private String Surname;
    private String DateOfBirth;
    private Gender gender;
    private FeeStatus feeStatus;
    private boolean CorrespondenceLangWelsh;
    private WelshSpeaker welshSpeaker;
    private String CountryOfDomicile; //Potential Enum (too complicated)
    private String Nationality;
    private String HomeEmail;
    private String DateReceived;
    private boolean ContextualFlag;
    private String ApplicationStatusHCare; //Does this change?
    private String ApplicationStatusComments;
    private String FeeStatusComments;
    private String HighestLevelQualification; //Potential Enum if they improve their spreadsheet
    private String GradesAchieved;
    private boolean KeepingWarmEmailSent;
    private int TotalPersonalStatementScore;
    private boolean InviteInterview;
    private String InterviewDate;
    private String InterviewInviteComments;
    private String TotalInterviewScore;
    private boolean FTPChecked;
    private String OfferConditions;
    private boolean NonStandardQualificationsChaserEmail;
    private String GradesAchievedAfter; //Grades achieved after application sent
    private String ConfirmationComments;
    private boolean OfferEmailSent;
    private String IssueDate;
    private String DBSCertNumber;
    private String FAStatus;
    private String UpdateService;
    private String EssentialToDos;
    private String EnrolmentCriteriaComments;
}
