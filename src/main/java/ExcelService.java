import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

import java.io.*;

public class ExcelService {
    private Workbook workbook;
    private Sheet currentSheet;
    private static int count = 0;
    private static int i = 0;

    public ExcelService() throws IOException {
//        workbook = WorkbookFactory.create(new FileInputStream((getClass().getClassLoader().getResource("tabel.xls").getFile())));
        workbook = WorkbookFactory.create(new FileInputStream("C:\\tabel.xls"));
        currentSheet = workbook.getSheetAt(0);
    }

    public void addPersons(Person person) throws IOException {
        CellReference cR = getCellReference();

        Row firstRow =  currentSheet.createRow(cR.getRow());
        Row secondRow = currentSheet.createRow(cR.getRow() + 1);

        setCellFromPerson(person, firstRow, secondRow);

        setDaysToCells(person, firstRow);

        setHoursToCells(person, secondRow);

        setCellsWithFullDaysAndHours(person, firstRow, secondRow);

        saveBookAndClose();
    }

    private void setDaysToCells(Person person, Row row) {
        i = 7;
        for (ChoiceBox day : person.getDays()) {
            if (i == 22) {
                setCellWithFirstPartOfDays(person, row);
            }
            row.createCell(i).setCellValue(day.getValue().toString());
            i++;
        }
    }

    private void setHoursToCells(Person person, Row row) {
        i = 7;
        for (TextField hours : person.getHours()) {
            if (i == 22) {
                setCellWithFirstPartOfHours(person, row);
            }
            row.createCell(i).setCellValue(hours.getText());
            i++;
        }
    }

    private CellReference getCellReference() {
        CellReference cR = new CellReference("A15");
        for (i = 15; currentSheet.getRow(cR.getRow()).getCell(cR.getCol()) != null; i += 2) {
            cR = new CellReference("A" + i);
            count++;
        }
        return cR;
    }

    private void setCellFromPerson(Person person, Row row, Row row1) {

        row.createCell(0).setCellValue(count);
        row1.createCell(0);
        currentSheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row1.getRowNum(), 0, 0));
        row.createCell(1).setCellValue(".");
        row1.createCell(1);
        currentSheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row1.getRowNum(), 1, 1));
        row.createCell(2).setCellValue(person.getFio());
        row1.createCell(2);
        currentSheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row1.getRowNum(),  2,  2));
        row.createCell(3).setCellValue(person.getNumber());
        row1.createCell(3);
        row.createCell(4);
        row1.createCell(4);
        currentSheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row1.getRowNum(),  3, 4));
        row.createCell(5).setCellValue(person.getProfession());
        row1.createCell(5);
        row.createCell(6);
        row1.createCell(6);
        currentSheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row1.getRowNum(), 5, 6));
    }

    private void setCellWithFirstPartOfDays(Person person, Row row) {
        for (int k = 0; k < 2; k++) {
            row.createCell(i + k);
        }
        currentSheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum(), i, i + 2));
        row.getCell(i).setCellValue(person.getFirstPartSumDays());
        i += 3;
    }

    private void setCellWithFirstPartOfHours(Person person, Row row) {
        for (int k = 0; k < 2; k++) {
            row.createCell(i + k);
        }
        currentSheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum(), i, i + 2));
        row.getCell(i).setCellValue(person.getFirstPartSumHours());
        i += 3;
    }

    private void setCellsWithFullDaysAndHours(Person person, Row firstRow, Row secondRow) {
        for (int k = 0; k < 2; k++) {
            firstRow.createCell(i + k);
            secondRow.createCell(i + k);
        }
        currentSheet.addMergedRegion(new CellRangeAddress(firstRow.getRowNum(), firstRow.getRowNum(), i, i + 2));
        currentSheet.addMergedRegion(new CellRangeAddress(secondRow.getRowNum(), secondRow.getRowNum(), i, i + 2));
        firstRow.getCell(i).setCellValue(person.getSumDays());
        secondRow.getCell(i).setCellValue(person.getSumHours());
    }

    private void saveBookAndClose() throws IOException {
        try (FileOutputStream file = new FileOutputStream("result.xls")) {
            workbook.write(file);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException(e);
        }
        workbook.close();
    }
}
