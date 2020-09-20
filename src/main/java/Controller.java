import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    @FXML TextField name;
    @FXML TextField surname;
    @FXML TextField middleName;
    @FXML TextField profession;
    @FXML TextField number;

    @FXML Button acceptBtn;

    @FXML ChoiceBox day1;
    @FXML ChoiceBox day2;
    @FXML ChoiceBox day3;
    @FXML ChoiceBox day4;
    @FXML ChoiceBox day5;
    @FXML ChoiceBox day6;
    @FXML ChoiceBox day7;
    @FXML ChoiceBox day8;
    @FXML ChoiceBox day9;
    @FXML ChoiceBox day10;
    @FXML ChoiceBox day11;
    @FXML ChoiceBox day12;
    @FXML ChoiceBox day13;
    @FXML ChoiceBox day14;
    @FXML ChoiceBox day15;
    @FXML ChoiceBox day16;
    @FXML ChoiceBox day17;
    @FXML ChoiceBox day18;
    @FXML ChoiceBox day19;
    @FXML ChoiceBox day20;
    @FXML ChoiceBox day21;
    @FXML ChoiceBox day22;
    @FXML ChoiceBox day23;
    @FXML ChoiceBox day24;
    @FXML ChoiceBox day25;
    @FXML ChoiceBox day26;
    @FXML ChoiceBox day27;
    @FXML ChoiceBox day28;
    @FXML ChoiceBox day29;
    @FXML ChoiceBox day30;
    @FXML ChoiceBox day31;

    @FXML TextField hours1;
    @FXML TextField hours2;
    @FXML TextField hours3;
    @FXML TextField hours4;
    @FXML TextField hours5;
    @FXML TextField hours6;
    @FXML TextField hours7;
    @FXML TextField hours8;
    @FXML TextField hours9;
    @FXML TextField hours10;
    @FXML TextField hours11;
    @FXML TextField hours12;
    @FXML TextField hours13;
    @FXML TextField hours14;
    @FXML TextField hours15;
    @FXML TextField hours16;
    @FXML TextField hours17;
    @FXML TextField hours18;
    @FXML TextField hours19;
    @FXML TextField hours20;
    @FXML TextField hours21;
    @FXML TextField hours22;
    @FXML TextField hours23;
    @FXML TextField hours24;
    @FXML TextField hours25;
    @FXML TextField hours26;
    @FXML TextField hours27;
    @FXML TextField hours28;
    @FXML TextField hours29;
    @FXML TextField hours30;
    @FXML TextField hours31;

    private List<ChoiceBox> days;
    private List<TextField> hours;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getDays().forEach(choiceBox -> {
            choiceBox.setValue("В");
            choiceBox.getItems().add("В");
            choiceBox.getItems().add("Н");
            choiceBox.getItems().add("Г");
            choiceBox.getItems().add("О");
            choiceBox.getItems().add("Б");
            choiceBox.getItems().add("Р");
            choiceBox.getItems().add("С");
            choiceBox.getItems().add("П");
            choiceBox.getItems().add("К");
            choiceBox.getItems().add("А");
            choiceBox.getItems().add("ВУ");
            choiceBox.getItems().add("ОУ");
            choiceBox.getItems().add("ЗН");
            choiceBox.getItems().add("ЗП");
            choiceBox.getItems().add("ЗС");
            choiceBox.getItems().add("РП");
            choiceBox.getItems().add("Ф");
            choiceBox.getItems().add("Я");
        });
        getHours().forEach(textField -> {
            textField.setText("2");
        });
    }

    @FXML
    public void handleAction() throws IOException {
        Person person = new Person();
        person.setName(name.getText());
        person.setMiddleName(middleName.getText());
        person.setSurname(surname.getText());
        person.setProfession(profession.getText());
        person.setNumber(number.getText());
        person.setDays(getDays());
        person.setHours(getHours());
        ExcelService excelService = new ExcelService();
        excelService.addPersons(person);
    }

    public List<ChoiceBox> getDays() {
        if (days == null) {
            days = new ArrayList<>();
            days.add(day1);
            days.add(day2);
            days.add(day3);
            days.add(day4);
            days.add(day5);
            days.add(day6);
            days.add(day7);
            days.add(day8);
            days.add(day9);
            days.add(day10);
            days.add(day11);
            days.add(day12);
            days.add(day13);
            days.add(day14);
            days.add(day15);
            days.add(day16);
            days.add(day17);
            days.add(day18);
            days.add(day19);
            days.add(day20);
            days.add(day21);
            days.add(day22);
            days.add(day23);
            days.add(day24);
            days.add(day25);
            days.add(day26);
            days.add(day27);
            days.add(day28);
            days.add(day29);
            days.add(day30);
            days.add(day31);
        }
        return days;
    }

    public List<TextField> getHours() {
        if (hours == null) {
            hours = new ArrayList<>();
            hours.add(hours1);
            hours.add(hours2);
            hours.add(hours3);
            hours.add(hours4);
            hours.add(hours5);
            hours.add(hours6);
            hours.add(hours7);
            hours.add(hours8);
            hours.add(hours9);
            hours.add(hours10);
            hours.add(hours11);
            hours.add(hours12);
            hours.add(hours13);
            hours.add(hours14);
            hours.add(hours15);
            hours.add(hours16);
            hours.add(hours17);
            hours.add(hours18);
            hours.add(hours19);
            hours.add(hours20);
            hours.add(hours21);
            hours.add(hours22);
            hours.add(hours23);
            hours.add(hours24);
            hours.add(hours25);
            hours.add(hours26);
            hours.add(hours27);
            hours.add(hours28);
            hours.add(hours29);
            hours.add(hours30);
            hours.add(hours31);
        }
        return hours;
    }
}
