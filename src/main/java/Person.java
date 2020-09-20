import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.util.List;

public class Person {
    private String name;
    private String middleName;
    private String surname;
    private String profession;
    private String number;
    private List<ChoiceBox> days;
    private List<TextField> hours;



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
}
