import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import org.apache.poi.ss.util.CellReference;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    @FXML
    TextField name;
    @FXML
    TextField surname;
    @FXML
    TextField middleName;
    @FXML
    TextField profession;
    @FXML
    TextField number;
    @FXML
    Button acceptBtn;
    @FXML
    List<ChoiceBox> days;
    @FXML
    List<TextField> hours;
    @FXML
    private List<TableColumn<String, Person>> columns;
    @FXML
    private DatePicker date;
    @FXML
    private TableView<Person> persons;
    private ExcelService excelService;


    public Controller() {
        try {
            excelService = new ExcelService();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Начальные значения для удобства в тестах
        name.setText("Vladimir");
        surname.setText("Putin");
        middleName.setText("Vladimirovich");
        profession.setText("President");
        number.setText("007");
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

        columns.get(0).setCellValueFactory(new PropertyValueFactory<String, Person>("surname"));
        columns.get(1).setCellValueFactory(new PropertyValueFactory<String, Person>("name"));
        columns.get(2).setCellValueFactory(new PropertyValueFactory<String, Person>("middleName"));
        columns.get(3).setCellValueFactory(new PropertyValueFactory<String, Person>("number"));
        columns.get(4).setCellValueFactory(new PropertyValueFactory<String, Person>("profession"));
//        columns.get(5).setCellFactory(new Callback<TableColumn<String, Person>, TableCell<String, Person>>() {
//            @Override
//            public TableCell<String, Person> call(TableColumn<String, Person> param) {
//                return new TableCell<String, Person>(){
//                    @Override
//                    protected void updateItem(Person item, boolean empty) {
//                        super.updateItem(item, empty);
//                        setText("data");
//                        getTableView().
////                        if (item != null) {
////                            System.out.println(getTableView().getItems().get(getTableRow().getIndex()).toString());
////                            setTooltip(new Tooltip(item.getFio()));
////                        }
//                    }
//                };
//            }
//        });
        persons.setRowFactory(new Callback<TableView<Person>, TableRow<Person>>() {
            @Override
            public TableRow<Person> call(TableView<Person> param) {
                return new TableRow<Person>(){
                    @Override
                    protected void updateItem(Person item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setTooltip(new Tooltip("afkbwfjnlak"));
                            //tooltip.setShowDelay(Duration.seconds(3)) в java >= 9
                            List<String> days = item.getDaysToString();
                            List<String> hours = item.getHoursToString();

                            StringBuilder result = new StringBuilder("");
                            for (String day : days) { result.append(" | ").append(day); }
                            result.append("\n");
                            for (String hour : hours) { result.append(" | ").append(hour); }
                            setTooltip(new Tooltip(result.toString()));
                        }
                    }
                };
            }
        });
//        columns.get(5).setCellValueFactory(new PropertyValueFactory<String, Person>("daysToString") {
//            @Override
//            public ObservableValue<Person> call(TableColumn.CellDataFeatures<String, Person> param) {
//                return super.call(param);
//            }
//        });
//        columns.get(5).setCellFactory(columns -> new TableCell<String, Person>() {
//            @Override
//            protected void updateItem(Person item, boolean empty) {
//                super.updateItem(item, empty);
//                setText("Дата и часы");
//
//                StringBuilder result = new StringBuilder("");
//                if (item != null) {
//                    List<String> days = item.getDaysToString();
//                    List<String> hours = item.getHoursToString();
//                    System.out.println("lawd");
////
//                    result = new StringBuilder();
//                    for (String day : days) {
//                        result.append(" | ").append(day);
//                    }
//                    result.append("\n");
//                    for (String hour : hours) {
//                        result.append(" | ").append(hour);
//                    }
//                    setTooltip(new Tooltip(result.toString()));
//                }
//                setTooltip(new Tooltip(result.toString()));
//            }
//        });

        for (Person prn : excelService.getPersons()) {
            persons.getItems().add(prn);
        }

//        excelService.getPersons().forEach(person -> persons.getItems().add(person));
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
