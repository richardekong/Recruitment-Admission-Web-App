package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service;

import org.apache.poi.ss.usermodel.Cell;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.*;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.LinkedList;

public interface ExcelReaderService {
    LinkedList<Candidate> readCandidatesFromExcelSheet(InputStream inputStream);
    default void readFromCurrentCell(int cellIndex, Candidate candidate, Cell cell) {
        switch (cellIndex) {
            case 2:
                candidate.setRecordFirstCreated((cell.getStringCellValue()));
                break;
            case 3:
                candidate.setEntryYear(LocalDate.parse(cell.getStringCellValue()));
                break;
            case 4:
                candidate.setStudentNo(cell.getStringCellValue());
                break;
            case 5:
                candidate.setPersonalID(cell.getStringCellValue());
                break;
            case 6:
                candidate.setApplicationStatusCode(ApplicationStatusCode.valueOf(cell.getStringCellValue()));
                break;
            case 7:
                candidate.setLatestDecisionCode(cell.getStringCellValue());
                break;
            case 8:
                candidate.setFirstName(cell.getStringCellValue());
                break;
            case 9:
                candidate.setSurname(cell.getStringCellValue());
                break;
            case 10:
                candidate.setDateOfBirth(cell.getStringCellValue());
                break;
            case 11:
                candidate.setGender(Gender.valueOf(cell.getStringCellValue()));
                break;
            case 12:
                candidate.setFeeStatus(FeeStatus.valueOf(cell.getStringCellValue()));
                break;
            case 13:
                candidate.setCorrespondenceLangWelsh(cell.getBooleanCellValue());
                break;
            case 14:
                candidate.setWelshSpeaker(WelshSpeaker.valueOf(cell.getStringCellValue()));
                break;
            case 15:
                candidate.setCountryOfDomicile(cell.getStringCellValue());
                break;
            case 16:
                candidate.setNationality(cell.getStringCellValue());
                break;
            case 17:
                candidate.setHomeEmail(cell.getStringCellValue());
                break;
            case 18:
                candidate.setDateReceived(cell.getStringCellValue());
                break;
            case 19:
                candidate.setContextualFlag(cell.getBooleanCellValue());
                break;
            case 20:
                candidate.setApplicationStatusHCare(cell.getStringCellValue());
                break;
            case 21:
                candidate.setApplicationStatusComments(cell.getStringCellValue());
                break;
            case 22:
                candidate.setFeeStatusComments(cell.getStringCellValue());
                break;
            case 23:
                candidate.setHighestLevelQualification(cell.getStringCellValue());
                break;
            case 24:
                candidate.setGradesAchieved(cell.getStringCellValue());
                break;
            case 25:
                candidate.setKeepingWarmEmailSent(cell.getBooleanCellValue());
                break;
            case 26:
                candidate.setTotalPersonalStatementScore((int) cell.getNumericCellValue());
                break;
            case 27:
                candidate.setInviteInterview(cell.getBooleanCellValue());
                break;
            case 28:
                candidate.setInterviewDate(cell.getStringCellValue());
                break;
            case 29:
                candidate.setInterviewInviteComments(cell.getStringCellValue());
                break;
            case 30:
                candidate.setTotalInterviewScore(cell.getStringCellValue());
                break;
            case 31:
                candidate.setFTPChecked(cell.getBooleanCellValue());
                break;
            case 32:
                candidate.setOfferConditions(cell.getStringCellValue());
                break;
            case 33:
                candidate.setNonStandardQualificationsChaserEmail(cell.getBooleanCellValue());
                break;
            case 34:
                candidate.setGradesAchievedAfter(cell.getStringCellValue());
                break;
            case 35:
                candidate.setConfirmationComments(cell.getStringCellValue());
                break;
            case 36:
                candidate.setOfferEmailSent(cell.getBooleanCellValue());
                break;
            case 37:
                candidate.setIssueDate(cell.getStringCellValue());
                break;
            case 38:
                candidate.setDBSCertNumber(cell.getStringCellValue());
                break;
            case 39:
                candidate.setFAStatus(cell.getStringCellValue());
                break;
            case 40:
                candidate.setUpdateService(cell.getStringCellValue());
                break;
            case 41:
                candidate.setEssentialToDos(cell.getStringCellValue());
                break;
            case 42:
                candidate.setEnrolmentCriteriaComments(cell.getStringCellValue());
                break;
            default:
                break;
        }
    }
}
