package uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.service;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import uk.ac.cf.cs.nsa.msc.web.team11recruitmentandadmissions.model.Candidate;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelExporterServiceImpl implements ExcelExporterService {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    private List<Candidate> candidateList;



    public  ExcelExporterServiceImpl(List<Candidate> candidateList) {
        this.candidateList = candidateList;
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Candidates");
    }
    @Override
    public void writeHeaderRow(){
        // Header Row
        Row row = sheet.createRow(0);


        // First cell in the header
        Cell cell = row.createCell(0);
        cell.setCellValue("UCASCardiffCourseCode");

        // Second cell in the header
        cell = row.createCell(1);
        cell.setCellValue("cardiffCourseCode");

        // Third cell in the header
        cell = row.createCell(2);
        cell.setCellValue("recordFirstCreated");

        // Fourth cell in the header
        cell = row.createCell(3);
        cell.setCellValue("entryYear");

        // Fifth cell in the header
        cell = row.createCell(4);
        cell.setCellValue("studentNo");

        // Sixth cell in the header
        cell = row.createCell(5);
        cell.setCellValue("personalID");

        // Seventh cell in the header
        cell = row.createCell(6);
        cell.setCellValue("applicationStatusCode");

        // Eighth cell in the header
        cell = row.createCell(7);
        cell.setCellValue("latestDecisionCode");

        // Ninth cell in the header
        cell = row.createCell(8);
        cell.setCellValue("firstName");

        // Tenth cell in the header
        cell = row.createCell(9);
        cell.setCellValue("Surname");

        // Eleventh cell in the header
        cell = row.createCell(10);
        cell.setCellValue("dateOfBirth");

        // Twelfth cell in the header
        cell = row.createCell(11);
        cell.setCellValue("gender");

        // Thirteenth cell in the header
        cell = row.createCell(12);
        cell.setCellValue("feeStatus");

        // Fourteenth cell in the header
        cell = row.createCell(13);
        cell.setCellValue("correspondenceLangWelsh");

        // Fifteenth cell in the header
        cell = row.createCell(14);
        cell.setCellValue("welshSpeaker");

        // Sixteenth cell in the header
        cell = row.createCell(15);
        cell.setCellValue("countryOfDomicile");

        // Seventeenth cell in the header
        cell = row.createCell(16);
        cell.setCellValue("nationality");

        // Eighteenth cell in the header
        cell = row.createCell(17);
        cell.setCellValue("homeEmail");

        // Nineteenth cell in the header
        cell = row.createCell(18);
        cell.setCellValue("dateReceived");

        // Twentieth cell in the header
        cell = row.createCell(19);
        cell.setCellValue("contextualFlag");

        // cell number 21 in the header
        cell = row.createCell(20);
        cell.setCellValue("applicationStatusHCare");

        // cell number 22 in the header
        cell = row.createCell(21);
        cell.setCellValue("applicationStatusComments");

        // cell number 23 in the header
        cell = row.createCell(22);
        cell.setCellValue("feeStatusComments");

        // cell number 24 in the header
        cell = row.createCell(23);
        cell.setCellValue("highestLevelQualification");

        // cell number 25 in the header
        cell = row.createCell(24);
        cell.setCellValue("gradesAchieved");

        // cell number 26 in the header
        cell = row.createCell(25);
        cell.setCellValue("keepingWarmEmailSent");

        // cell number 27 in the header
        cell = row.createCell(26);
        cell.setCellValue("totalPersonalStatementScore");

        // cell number 28 in the header
        cell = row.createCell(27);
        cell.setCellValue("inviteInterview");

        // cell number 29 in the header
        cell = row.createCell(28);
        cell.setCellValue("interviewDate");

        // cell number 30 in the header
        cell = row.createCell(29);
        cell.setCellValue("interviewInviteComments");

        // cell number 31 in the header
        cell = row.createCell(30);
        cell.setCellValue("totalInterviewScore");

        // cell number 32 in the header
        cell = row.createCell(31);
        cell.setCellValue("FTPChecked");

        // cell number 33 in the header
        cell = row.createCell(32);
        cell.setCellValue("offerConditions");

        // cell number 34 in the header
        cell = row.createCell(33);
        cell.setCellValue("nonStandardQualificationsChaserEmail");

        // cell number 35 in the header
        cell = row.createCell(34);
        cell.setCellValue("gradesAchievedAfter");

        // cell number 36 in the header
        cell = row.createCell(35);
        cell.setCellValue("confirmationComments");

        // cell number 37 in the header
        cell = row.createCell(36);
        cell.setCellValue("offerEmailSent");

        // cell number 38 in the header
        cell = row.createCell(37);
        cell.setCellValue("issueDate");

        // cell number 39 in the header
        cell = row.createCell(38);
        cell.setCellValue("DBSCertNumber");

        // cell number 40 in the header
        cell = row.createCell(39);
        cell.setCellValue("FAStatus");

        // cell number 41 in the header
        cell = row.createCell(40);
        cell.setCellValue("updateService");

        // cell number 42 in the header
        cell = row.createCell(41);
        cell.setCellValue("essentialToDos");

        // cell number 43 in the header
        cell = row.createCell(42);
        cell.setCellValue("enrolmentCriteriaComments");

    }
    @Override
    public void writeDataRows(){
        // counter for rows
        int rowCount = 1;

        // loop throw candidateList and creating cell for each candidate attribute then
        for (Candidate candidate : candidateList){
            // create new row for separate candidate
            Row row = sheet.createRow(rowCount);

            // Here the candidate attribute values will be assigned to new cells in order to make Row for that candidate
            // In that row each column-number of column 43- represent candidate attribute

            // cell number 1 in the row
            Cell cell = row.createCell(0);
            cell.setCellValue(candidate.getUCASCardiffCourseCode());

            // cell number 2 in the row
            cell = row.createCell(1);
            cell.setCellValue(candidate.getCardiffCourseCode());

            // cell number 3 in the row
            cell = row.createCell(2);
            cell.setCellValue(String.valueOf(candidate.getRecordFirstCreated()));

            // cell number 4 in the row
            cell = row.createCell(3);
            cell.setCellValue(candidate.getEntryYear());

            // cell number 5 in the row
            cell = row.createCell(4);
            cell.setCellValue(candidate.getStudentNo());

            // cell number 6 in the row
            cell = row.createCell(5);
            cell.setCellValue(candidate.getPersonalID());

            // cell number 7 in the row
            cell = row.createCell(6);
            cell.setCellValue(candidate.getApplicationStatusCode().getCode());

            // cell number 8 in the row
            cell = row.createCell(7);
            cell.setCellValue(candidate.getLatestDecisionCode());

            // cell number 9 in the row
            cell = row.createCell(8);
            cell.setCellValue(candidate.getFirstName());

            // cell number 10 in the row
            cell = row.createCell(9);
            cell.setCellValue(candidate.getSurname());

            // cell number 11 in the row
            cell = row.createCell(10);
            cell.setCellValue(String.valueOf(candidate.getDateOfBirth()));

            // cell number 12 in the row
            cell = row.createCell(11);
            cell.setCellValue(candidate.getGender().getGender());

            // cell number 13 in the row
            cell = row.createCell(12);
            cell.setCellValue(candidate.getFeeStatus().getFeeStatus());

            // cell number 14 in the row
            cell = row.createCell(13);
            cell.setCellValue(candidate.getCorrespondenceLangWelsh().getOption());

            // cell number 15 in the row
            cell = row.createCell(14);
            cell.setCellValue(candidate.getWelshSpeaker().getResponse());

            // cell number 16 in the row
            cell = row.createCell(15);
            cell.setCellValue(candidate.getCountryOfDomicile());

            // cell number 17 in the row
            cell = row.createCell(16);
            cell.setCellValue(candidate.getNationality());

            // cell number 18 in the row
            cell = row.createCell(17);
            cell.setCellValue(candidate.getHomeEmail());

            // cell number 19 in the row
            cell = row.createCell(18);
            cell.setCellValue(String.valueOf(candidate.getDateReceived()));

            // cell number 20 in the row
            cell = row.createCell(19);
            cell.setCellValue(candidate.getContextualFlag().getOption());

            // cell number 21 in the row
            cell = row.createCell(20);
            cell.setCellValue(candidate.getApplicationStatusHCare());

            // cell number 22 in the row
            cell = row.createCell(21);
            cell.setCellValue(candidate.getApplicationStatusComments());

            // cell number 23 in the row
            cell = row.createCell(22);
            cell.setCellValue(candidate.getFeeStatusComments());

            // cell number 24 in the row
            cell = row.createCell(23);
            cell.setCellValue(candidate.getHighestLevelQualification());

            // cell number 25 in the row
            cell = row.createCell(24);
            cell.setCellValue(candidate.getGradesAchieved());

            // cell number 26 in the row
            cell = row.createCell(25);
            cell.setCellValue(candidate.getKeepingWarmEmailSent().getOption());

            // cell number 27 in the row
            cell = row.createCell(26);
            cell.setCellValue(candidate.getTotalPersonalStatementScore());

            // cell number 28 in the row
            cell = row.createCell(27);
            cell.setCellValue(candidate.getInviteInterview().getOption());

            // cell number 29 in the row
            cell = row.createCell(28);
            cell.setCellValue(String.valueOf(candidate.getInterviewDate()));

            // cell number 30 in the row
            cell = row.createCell(29);
            cell.setCellValue(candidate.getInterviewInviteComments());

            // cell number 31 in the row
            cell = row.createCell(30);
            cell.setCellValue(candidate.getTotalInterviewScore());

            // cell number 32 in the row
            cell = row.createCell(31);
            cell.setCellValue(String.valueOf(candidate.getFTPChecked().getOption()));

            // cell number 33 in the row
            cell = row.createCell(32);
            cell.setCellValue(candidate.getOfferConditions());

            // cell number 34 in the row
            cell = row.createCell(32);
            cell.setCellValue(String.valueOf(candidate.getNonStandardQualificationsChaserEmail()));

            // cell number 35 in the row
            cell = row.createCell(34);
            cell.setCellValue(candidate.getGradesAchievedAfter());

            // cell number 36 in the row
            cell = row.createCell(35);
            cell.setCellValue(candidate.getConfirmationComments());

            // cell number 37 in the row
            cell = row.createCell(36);
            cell.setCellValue(candidate.getOfferEmailSent().getOption());

            // cell number 38 in the row
            cell = row.createCell(37);
            cell.setCellValue(String.valueOf(candidate.getIssueDate()));

            // cell number 39 in the row
            cell = row.createCell(38);
            cell.setCellValue(candidate.getDBSCertNumber());

            // cell number 40 in the row
            cell = row.createCell(39);
            cell.setCellValue(candidate.getFAStatus());

            // cell number 41 in the row
            cell = row.createCell(40);
            cell.setCellValue(candidate.getUpdateService());

            // cell number 42 in the row
            cell = row.createCell(41);
            cell.setCellValue(candidate.getEssentialToDos());

            // cell number 43 in the row
            cell = row.createCell(42);
            cell.setCellValue(candidate.getEnrolmentCriteriaComments());

            rowCount++;

        }


    }
    @Override
    public void export(HttpServletResponse response) throws IOException {
        writeHeaderRow();
        writeDataRows();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();

    }

}
