import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExcelService {
    private Workbook workbook;
    private Sheet currentSheet;
    private static int count = 0;
    private static int i = 0;
    private CellStyle fullBorderStyle;

    public ExcelService() throws IOException {
        workbook = WorkbookFactory.create(new FileInputStream((getClass().getClassLoader().getResource("tabel.xls").getFile())));
//        workbook = WorkbookFactory.create(new FileInputStream("C:\\tabel.xls"));
        currentSheet = workbook.getSheetAt(0);
        makeFullBorderStyle();
    }

    public void addPersons(Person person) throws IOException {
        CellReference cR = getCellReference();

        Row firstRow = currentSheet.createRow(cR.getRow());
        Row secondRow = currentSheet.createRow(cR.getRow() + 1);

        setCellFromPerson(person, firstRow, secondRow);

        setDaysToCells(person, firstRow);

        setHoursToCells(person, secondRow);

        setCellsWithFullDaysAndHours(person, firstRow, secondRow);

        saveBookAndClose();
    }

    private CellReference getCellReference() {
        CellReference cR = new CellReference("A15");
        for (i = 15; currentSheet.getRow(cR.getRow()).getCell(cR.getCol()) != null; i += 2) {
            addPersonToList(cR);
            cR = new CellReference("A" + i);
            count++;
        }
        return cR;
    }

    public List<Person> getPersons() {
        List<Person> persons = new ArrayList<>();

        CellReference cR = new CellReference("A15");
        for (int i = 17; currentSheet.getRow(cR.getRow()).getCell(cR.getCol()) != null; i += 2) {
            double test = currentSheet.getRow(cR.getRow()).getCell(cR.getCol()).getNumericCellValue();

            Person p = addPersonToList(cR);
            persons.add(p);
            cR = new CellReference("A" + i);
        }
        return persons;
    }

    public Person addPersonToList(CellReference cR) {
        String fio = currentSheet.getRow(cR.getRow()).getCell(2).getStringCellValue();
        List<String> list = Arrays.asList(fio.split(" "));

        List<String> days = new ArrayList<>();
        List<String> hours = new ArrayList<>();
        Person person = new Person();
        person.setSurname(list.get(0));
        person.setName(list.get(1));
        person.setMiddleName(list.get(2));
        person.setNumber(currentSheet.getRow(cR.getRow()).getCell(3).getStringCellValue());
        person.setProfession(currentSheet.getRow(cR.getRow()).getCell(5).getStringCellValue());
        for (int k = 7; k < 38; k++) {
            if (k != 22) {
                days.add(currentSheet.getRow(cR.getRow()).getCell(k).getStringCellValue());
                hours.add(currentSheet.getRow(cR.getRow() + 1).getCell(k).toString());
            } else {
                k += 2;
            }
        }
        person.setDaysToString(days);
        person.setHoursToString(hours);
        return person;
    }

    private void setCellFromPerson(Person person, Row firstRow, Row secondRow) {
        CellStyle fioStyle = workbook.createCellStyle();
        fioStyle.setBorderBottom(BorderStyle.THIN);
        fioStyle.setBorderTop(BorderStyle.THIN);
        fioStyle.setAlignment(HorizontalAlignment.RIGHT);

        firstRow.createCell(0).setCellStyle(fioStyle);
        secondRow.createCell(0).setCellStyle(fioStyle);
        firstRow.getCell(0).setCellValue(count);
        currentSheet.addMergedRegion(new CellRangeAddress(firstRow.getRowNum(), secondRow.getRowNum(), 0, 0));

        firstRow.createCell(1).setCellStyle(fioStyle);
        secondRow.createCell(1).setCellStyle(fioStyle);
        firstRow.getCell(1).setCellValue(".");
        currentSheet.addMergedRegion(new CellRangeAddress(firstRow.getRowNum(), secondRow.getRowNum(), 1, 1));

        fioStyle.setWrapText(true);
        fioStyle.setAlignment(HorizontalAlignment.LEFT);
        firstRow.createCell(2).setCellStyle(fioStyle);
        secondRow.createCell(2).setCellStyle(fioStyle);
        firstRow.getCell(2).setCellValue(person.getFio());
        currentSheet.addMergedRegion(new CellRangeAddress(firstRow.getRowNum(), secondRow.getRowNum(), 2, 2));

        for (int k = 3; k < 5; k++) {
            firstRow.createCell(k).setCellStyle(fullBorderStyle);
            secondRow.createCell(k).setCellStyle(fullBorderStyle);
        }
        firstRow.getCell(3).setCellValue(person.getNumber());
        currentSheet.addMergedRegion(new CellRangeAddress(firstRow.getRowNum(), secondRow.getRowNum(), 3, 4));

        for (int k = 5; k < 7; k++) {
            firstRow.createCell(k).setCellStyle(fullBorderStyle);
            secondRow.createCell(k).setCellStyle(fullBorderStyle);
        }
        firstRow.getCell(5).setCellValue(person.getProfession());
        currentSheet.addMergedRegion(new CellRangeAddress(firstRow.getRowNum(), secondRow.getRowNum(), 5, 6));
    }

    private void setCellWithFirstPartOfDays(Person person, Row row) {
        for (int k = 0; k < 3; k++) {
            row.createCell(i + k).setCellStyle(fullBorderStyle);
        }
        currentSheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum(), i, i + 2));
        row.getCell(i).setCellValue(person.getFirstPartSumDays());
        i += 3;
    }

    private void setDaysToCells(Person person, Row row) {
        i = 7;
        for (ChoiceBox day : person.getDays()) {
            if (i == 22) {
                setCellWithFirstPartOfDays(person, row);
            }
            row.createCell(i).setCellStyle(fullBorderStyle);
            row.getCell(i).setCellValue(day.getValue().toString());
            i++;
        }
    }

    private void setHoursToCells(Person person, Row row) {
        i = 7;
        for (TextField hours : person.getHours()) {
            if (i == 22) {
                setCellWithFirstPartOfHours(person, row);
            }
            row.createCell(i).setCellStyle(fullBorderStyle);
            row.getCell(i).setCellValue(hours.getText());
            i++;
        }
    }

    private void setCellWithFirstPartOfHours(Person person, Row row) {
        for (int k = 0; k < 3; k++) {
            row.createCell(i + k).setCellStyle(fullBorderStyle);
        }
        currentSheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum(), i, i + 2));
        row.getCell(i).setCellValue(person.getFirstPartSumHours());
        i += 3;
    }

    private void setCellsWithFullDaysAndHours(Person person, Row firstRow, Row secondRow) {
        for (int k = 0; k < 3; k++) {
            firstRow.createCell(i + k).setCellStyle(fullBorderStyle);
            secondRow.createCell(i + k).setCellStyle(fullBorderStyle);
        }
        currentSheet.addMergedRegion(new CellRangeAddress(firstRow.getRowNum(), firstRow.getRowNum(), i, i + 2));
        currentSheet.addMergedRegion(new CellRangeAddress(secondRow.getRowNum(), secondRow.getRowNum(), i, i + 2));

        firstRow.getCell(i).setCellValue(person.getSumDays());
        secondRow.getCell(i).setCellValue(person.getSumHours());
    }

    private void makeFullBorderStyle() {
        fullBorderStyle = workbook.createCellStyle();
        fullBorderStyle.setBorderLeft(BorderStyle.THIN);
        fullBorderStyle.setBorderTop(BorderStyle.THIN);
        fullBorderStyle.setBorderRight(BorderStyle.THIN);
        fullBorderStyle.setBorderBottom(BorderStyle.THIN);
        fullBorderStyle.setAlignment(HorizontalAlignment.CENTER);
        fullBorderStyle.setVerticalAlignment(VerticalAlignment.CENTER);
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
