import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Person {
    private String name;
    private String middleName;
    private String surname;
    private String profession;
    private String number;
    private List<ChoiceBox> days;
    private List<TextField> hours;
    private List<String> daysToString;
    private List<String> hoursToString;

    private static List<String> list = Arrays.asList("Л", "Г", "С", "К", "ЗН", "ЗП", "ЗС", "РП", "Ф", "Я");

    public String getFio() {
        return surname +  " " + name + " " + middleName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<ChoiceBox> getDays() {
        return days;
    }

    public void setDays(List<ChoiceBox> days) {
        this.days = days;
    }

    public List<TextField> getHours() {
        return hours;
    }

    public void setHours(List<TextField> hours) {
        this.hours = hours;
    }

    public List<String> getDaysToString() {
        List<String> daysToString = new ArrayList<>();
        days.forEach(choiceBox -> daysToString.add(choiceBox.getValue().toString()));
        return daysToString;
    }

    public void setDaysToString(List<String> daysToString) {
        this.daysToString = daysToString;
    }

    public List<String> getHoursToString() {
        List<String> hoursToString = new ArrayList<>();
        hours.forEach(textField -> hoursToString.add(textField.getText()));
        return hoursToString;
    }

    public void setHoursToString(List<String> hoursToString) {
        this.hoursToString = hoursToString;
    }

    public Double getSumHours() {
        Double count = 0.0;
        for (TextField hour : hours) {
            count += Double.parseDouble(hour.getText());
        }
        return count;
    }

    public Double getFirstPartSumHours() {
        Double count = 0.0;
        for (int i = 0; i < 15; i++) {
            count += Double.parseDouble(hours.get(i).getText());
        }
        return count;
    }

    public Integer getSumDays() {
        Integer count = 0;
        for (ChoiceBox day : days) {
            if (list.contains(day.getValue().toString())) {
                count++;
            }
        }
        return count;
    }

    public Integer getFirstPartSumDays() {
        Integer count = 0;
        for (int i = 0; i < 15; i++) {
            if (list.contains(days.get(i).getValue().toString())) {
                count++;
            }
        }
        return count;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", middleName='" + middleName + '\'' +
                ", surname='" + surname + '\'' +
                ", profession='" + profession + '\'' +
                ", number='" + number + '\'' +
                ", days=" + daysToString +
                ", hours=" + hoursToString +
                '}';
    }
}
