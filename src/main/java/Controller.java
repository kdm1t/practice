import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.*;


public class Controller implements Initializable {
    @FXML TextField name;
    @FXML TextField surname;
    @FXML TextField middleName;
    @FXML TextField profession;
    @FXML TextField number;

    @FXML Button acceptBtn;

    @FXML List<ChoiceBox> days;
    @FXML List<TextField> hours;

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
        return days;
    }

    public List<TextField> getHours() {
        return hours;
    }
}
