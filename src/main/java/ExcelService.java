import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;

import java.io.*;

public class ExcelService {
    private Workbook workbook;
    private Sheet currentSheet;

    public ExcelService() throws IOException {
        workbook = WorkbookFactory.create(new FileInputStream((getClass().getClassLoader().getResource("tabel.xls").getFile())));
        currentSheet = workbook.getSheetAt(0);
    }

    public void addPersons(Person person) throws IOException {
        CellReference cR = new CellReference("A15");
        int i = 15;
        int count = 0;
        for (i = 15; currentSheet.getRow(cR.getRow()).getCell(cR.getCol()) != null; i += 2) {
            cR = new CellReference("A" + i);
            count++;
        }
        Row row =  currentSheet.createRow(cR.getRow());
        Row row1 = currentSheet.createRow(cR.getRow() + 1);
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
        i = 7;
        for (ChoiceBox day : person.getDays()) {
            if (i == 22) {
                row.createCell(i);
                row1.createCell(i);
                row.createCell(i + 1);
                row1.createCell(i + 1);
                row.createCell(i + 2);
                row1.createCell(i + 2);
                currentSheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), cR.getRow(), i, i + 2));
                row1.getCell(i).setCellValue(person.getFirstPartSumHours());
                i += 3;
            }
            row.createCell(i).setCellValue(day.getValue().toString());
            row1.createCell(i);
            i++;
        }
        row.createCell(i);
        row1.createCell(i);
        row.createCell(i + 1);
        row1.createCell(i + 1);
        row.createCell(i + 2);
        row1.createCell(i + 2);
        currentSheet.addMergedRegion(new CellRangeAddress(cR.getRow(), cR.getRow(), i, i + 2));

        //Часы
        i = 7;
        for (TextField hours : person.getHours()) {
            if (i == 22) {
                currentSheet.addMergedRegion(new CellRangeAddress(row1.getRowNum(), row1.getRowNum(), i, i + 2));
                row.getCell(i).setCellValue(person.getFirstPartSumDays());
                row1.getCell(i).setCellValue(person.getFirstPartSumHours());
                i += 3;
            }
            row1.getCell(i).setCellValue(hours.getText());
            i++;
        }
        currentSheet.addMergedRegion(new CellRangeAddress(row1.getRowNum(), row1.getRowNum(), i, i + 2));
        row.getCell(i).setCellValue(person.getSumDays());
        row1.getCell(i).setCellValue(person.getSumHours());

        try(FileOutputStream file = new FileOutputStream("result.xls")) {
            workbook.write(file);
        } catch (Exception e) {
            throw new IOException(e);
        }
        workbook.close();
    }
}
