package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.converter.DateConverter;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.*;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.LinkedList;

public interface ExcelReaderService {

    LinkedList<Candidate> readCandidatesFromExcelSheet(InputStream inputStream);

    default void readFromCurrentCell(
            int cellIndex,
            Candidate candidate,
            Cell cell,
            DataFormatter cellFormatter,
            DateConverter dateConverter) {

        switch (cellIndex) {
            case 0:
                candidate.setUCASCardiffCourseCode(cellFormatter.formatCellValue(cell));
                break;
            case 1:
                candidate.setCardiffCourseCode(cellFormatter.formatCellValue(cell));
                break;
            case 2: {
                candidate.setRecordFirstCreated(dateConverter.convertToLocalDateViaInstant(cell.getDateCellValue()));
                break;
            }
            case 3: {
                candidate.setEntryYear(cellFormatter.formatCellValue(cell));
                break;
            }
            case 4: {
                candidate.setStudentNo(cellFormatter.formatCellValue(cell));
                break;
            }
            case 5: {
                candidate.setPersonalID(cellFormatter.formatCellValue(cell));
                break;
            }
            case 6: {
                if (cell.getStringCellValue().equals("A")) {
                    candidate.setApplicationStatusCode(ApplicationStatusCode.APPLICATION);
                }
                break;
            }
            case 7: {
                candidate.setLatestDecisionCode(cellFormatter.formatCellValue(cell));
                break;
            }
            case 8: {
                candidate.setFirstName(cellFormatter.formatCellValue(cell));
                break;
            }
            case 9: {
                candidate.setSurname(cellFormatter.formatCellValue(cell));
                break;
            }
            case 10: {
                candidate.setDateOfBirth(dateConverter.convertToLocalDateViaInstant(cell.getDateCellValue()));
                break;
            }
            case 11: {
                if (cell.getStringCellValue().equals(Gender.MALE.getGender())) {
                    candidate.setGender(Gender.MALE);
                }
                if (cell.getStringCellValue().equals(Gender.FEMALE.getGender())) {
                    candidate.setGender(Gender.FEMALE);
                }
                break;
            }
            case 12: {
                if (cell.getStringCellValue().equals(FeeStatus.HOME.getFeeStatus())) {
                    candidate.setFeeStatus(FeeStatus.HOME);
                }
                if (cell.getStringCellValue().equals(FeeStatus.INTERNATIONAL.getFeeStatus())) {
                    candidate.setFeeStatus(FeeStatus.INTERNATIONAL);
                }
                break;
            }
            case 13: {
                if (cell.getStringCellValue().equals(YesOrNoOption.YES.getOption())) {
                    candidate.setCorrespondenceLangWelsh(YesOrNoOption.YES);
                }
                if (cell.getStringCellValue().equals(YesOrNoOption.NO.getOption())) {
                    candidate.setCorrespondenceLangWelsh(YesOrNoOption.NO);
                }
                break;

            }
            case 14: {
                if (cell.getStringCellValue().equals(WelshSpeaker.YES.getResponse())) {
                    candidate.setWelshSpeaker(WelshSpeaker.YES);
                }
                if (cell.getStringCellValue().equals(WelshSpeaker.NO.getResponse())) {
                    candidate.setWelshSpeaker(WelshSpeaker.NO);
                }
                if (cell.getStringCellValue().equals(WelshSpeaker.NA.getResponse())) {
                    candidate.setWelshSpeaker(WelshSpeaker.NA);
                }
                break;
            }
            case 15: {
                candidate.setCountryOfDomicile(cellFormatter.formatCellValue(cell));
                break;
            }
            case 16: {
                candidate.setNationality(cellFormatter.formatCellValue(cell));
                break;
            }
            case 17: {
                candidate.setHomeEmail(cellFormatter.formatCellValue(cell));
                break;
            }
            case 18: {
                candidate.setDateReceived(cellFormatter.formatCellValue(cell));
                break;
            }
            case 19: {
                if (cell.getStringCellValue().equals(YesOrNoOption.NO.getOption())) {
                    candidate.setContextualFlag(YesOrNoOption.NO);
                }
                if (cell.getStringCellValue().equals(YesOrNoOption.YES.getOption())) {
                    candidate.setContextualFlag(YesOrNoOption.YES);
                }
                break;
            }
            case 20: {
                candidate.setApplicationStatusHCare(cellFormatter.formatCellValue(cell));
                break;
            }
            case 21: {
                candidate.setApplicationStatusComments(cellFormatter.formatCellValue(cell));
                break;
            }
            case 22: {
                candidate.setFeeStatusComments(cellFormatter.formatCellValue(cell));
                break;
            }
            case 23: {
                candidate.setHighestLevelQualification(cellFormatter.formatCellValue(cell));
                break;
            }
            case 24: {
                candidate.setGradesAchieved(cellFormatter.formatCellValue(cell));
                break;
            }
            case 25: {
                if (cellFormatter.formatCellValue(cell).equals(YesOrNoOption.YES.getOption())) {
                    candidate.setKeepingWarmEmailSent(YesOrNoOption.YES);
                }
                if (cellFormatter.formatCellValue(cell).equals(YesOrNoOption.NO.getOption())) {
                    candidate.setKeepingWarmEmailSent(YesOrNoOption.NO);
                }
                break;
            }
            case 26: {
                candidate.setTotalPersonalStatementScore(Integer.parseInt(cellFormatter.formatCellValue(cell)));
                break;
            }
            case 27: {
                if (cellFormatter.formatCellValue(cell).equals(YesOrNoOption.YES.getOption())) {
                    candidate.setInviteInterview(YesOrNoOption.YES);
                }
                if (cellFormatter.formatCellValue(cell).equals(YesOrNoOption.NO.getOption())) {
                    candidate.setInviteInterview(YesOrNoOption.NO);
                }
                break;
            }
            case 28: {
                candidate.setInterviewDate(dateConverter.convertToLocalDateViaInstant(cell.getDateCellValue()));
                break;
            }
            case 29: {
                candidate.setInterviewInviteComments(cellFormatter.formatCellValue(cell));
                break;
            }
            case 30: {
                candidate.setTotalInterviewScore(Integer.parseInt(cellFormatter.formatCellValue(cell)));
                break;
            }
            case 31: {
                if (cellFormatter.formatCellValue(cell).equals(YesOrNoOption.YES.getOption())) {
                    candidate.setFTPChecked(YesOrNoOption.YES);
                }
                if (cellFormatter.formatCellValue(cell).equals(YesOrNoOption.NO.getOption())) {
                    candidate.setFTPChecked(YesOrNoOption.NO);
                }
                break;
            }
            case 32: {
                candidate.setOfferConditions(cellFormatter.formatCellValue(cell));
                break;
            }
            case 33: {
                if (cellFormatter.formatCellValue(cell).equals(YesOrNoOption.YES.getOption())) {
                    candidate.setNonStandardQualificationsChaserEmail(YesOrNoOption.YES);
                }
                if (cellFormatter.formatCellValue(cell).equals(YesOrNoOption.NO.getOption())) {
                    candidate.setNonStandardQualificationsChaserEmail(YesOrNoOption.NO);
                }
                break;
            }
            case 34: {
                candidate.setGradesAchievedAfter(cellFormatter.formatCellValue(cell));
                break;
            }
            case 35:
                candidate.setConfirmationComments(cellFormatter.formatCellValue(cell));
                break;
            case 36: {
                if (cellFormatter.formatCellValue(cell).equals(YesOrNoOption.YES.getOption())) {
                    candidate.setOfferEmailSent(YesOrNoOption.YES);
                }
                if (cellFormatter.formatCellValue(cell).equals(YesOrNoOption.NO.getOption())) {
                    candidate.setOfferEmailSent(YesOrNoOption.NO);
                }
                break;
            }
            case 37: {
                candidate.setIssueDate(cellFormatter.formatCellValue(cell));
                break;
            }
            case 38: {
                candidate.setDBSCertNumber(cellFormatter.formatCellValue(cell));
                break;
            }
            case 39: {
                candidate.setFAStatus(cellFormatter.formatCellValue(cell));
                break;
            }
            case 40: {
                candidate.setUpdateService(cellFormatter.formatCellValue(cell));
                break;
            }
            case 41: {
                candidate.setEssentialToDos(cellFormatter.formatCellValue(cell));
                break;
            }
            case 42: {
                candidate.setEnrolmentCriteriaComments(cellFormatter.formatCellValue(cell));
                break;
            }
            default: {
                break;
            }
        }
    }
}
