package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.converter.DateConverter;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedList;

public class ExcelReaderUtils {
    static public LinkedList<Candidate> readCandidatesFromExcelSheet(
            InputStream inputStream,
            DataFormatter cellFormatter,
            DateConverter dateConverter) throws IOException {

        LinkedList<Candidate> candidates = new LinkedList<>();
            XSSFWorkbook workBook = new XSSFWorkbook(inputStream);
            XSSFSheet xssfSheet = workBook.getSheet(workBook.getSheetName(0));
            int rowIndex = 0;
            for (Row row : xssfSheet) {
                if (rowIndex == 0) {
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                Candidate candidate = new Candidate();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    ExcelReaderUtils.readACandidatePropertyToACellInCurrentRow(
                            cellIndex,
                            candidate,
                            cell,
                            cellFormatter,
                            dateConverter
                    );
                    cellIndex++;
                }
                candidates.add(candidate);
            }
        return candidates;
    }

    public static LinkedList<HistoricalData> readHistoricalDataFromExcelSheet(InputStream inputStream) throws IOException {
        LinkedList<HistoricalData> historicalDataLinkedList = new LinkedList<>();
            XSSFWorkbook workBook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workBook.getSheet(workBook.getSheetName(0));
            int rowIndex = 0;
            for (Row row : sheet) {
                if (rowIndex == 0) {
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                HistoricalData historicalData = new HistoricalData();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cellIndex) {
                        case 0:
                            historicalData.setAcademicYear((int) cell.getNumericCellValue());
                            break;
                        case 1:
                            historicalData.setFundedPlaces((int) cell.getNumericCellValue());
                            break;
                        case 2:
                            historicalData.setOffersMade((int) cell.getNumericCellValue());
                            break;
                        case 3:
                            historicalData.setRatio((int) cell.getNumericCellValue());
                            break;
                        default: {
                            break;
                        }
                    }
                    cellIndex++;
                }
                historicalDataLinkedList.add(historicalData);
            }
        return historicalDataLinkedList;
    }

    private static void readACandidatePropertyToACellInCurrentRow(
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
                switch (cell.getStringCellValue()) {
                    case "A": {
                        candidate.setApplicationStatusCode(ApplicationStatusCode.APPLICATION);
                        break;
                    }
                    case "GF": {
                        candidate.setApplicationStatusCode(ApplicationStatusCode.GATHERED_FIELD_ON_HOLD);
                        break;
                    }
                    case "OR": {
                        candidate.setApplicationStatusCode(ApplicationStatusCode.OTHER_REASON_ON_HOLD);
                        break;
                    }
                    case "OH": {
                        candidate.setApplicationStatusCode(ApplicationStatusCode.AWAITING_INTERVIEW_ON_HOLD);
                        break;
                    }
                    case "R": {
                        candidate.setApplicationStatusCode(ApplicationStatusCode.REJECTED);
                        break;
                    }
                    case "C": {
                        candidate.setApplicationStatusCode(ApplicationStatusCode.CONDITIONAL_OFFER_MADE);
                        break;
                    }
                    case "CF": {
                        candidate.setApplicationStatusCode(ApplicationStatusCode.CONDITIONAL_OFFER_FIRMED);
                        break;
                    }
                    case "CI": {
                        candidate.setApplicationStatusCode(ApplicationStatusCode.CONDITIONAL_OFFER_INSURED);
                        break;
                    }
                    case "CD": {
                        candidate.setApplicationStatusCode(ApplicationStatusCode.CONDITIONAL_OFFER_DECLINED);
                        break;
                    }
                    case "U": {
                        candidate.setApplicationStatusCode(ApplicationStatusCode.UNCONDITIONAL_OFFER_MADE);
                        break;
                    }
                    case "UF": {
                        candidate.setApplicationStatusCode(ApplicationStatusCode.UNCONDITIONAL_OFFER_FIRMED);
                        break;
                    }
                    case "UI": {
                        candidate.setApplicationStatusCode(ApplicationStatusCode.UNCONDITIONAL_OFFER_INSURED);
                        break;
                    }
                    case "UD": {
                        candidate.setApplicationStatusCode(ApplicationStatusCode.UNCONDITIONAL_OFFER_DECLINED);
                        break;
                    }
                    case "W": {
                        candidate.setApplicationStatusCode(ApplicationStatusCode.WITHDRAWN);
                        break;
                    }
                    default: {
                        break;
                    }
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
