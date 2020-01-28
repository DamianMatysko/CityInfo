package sample;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class Controller {
    public ComboBox combo1;
    public ComboBox combo2;
    public Label cityInfo;
    public Button findInfo;
    List<String> countries;

    public String countryComboValue(){
        return (String) combo1.getValue();
    }
    public String cityComboValue(){
        return (String) combo2.getValue();
    }
    public Controller() throws SQLException, ClassNotFoundException {
        //Databases database = new Databases();
       // countries = database.getCountries();

    }


    public void getCountries(Event event) {
        Databases database = new Databases();
        combo1.getItems().setAll(database.getCountries());
    }

    public void getCites(Event event) {
        Databases database = new Databases();
     combo2.getItems().setAll(database.getCities(countryComboValue()));

    }

    public void isVisibe(ActionEvent actionEvent) {
        combo2.setVisible(true);
    }

    public void findInformation(ActionEvent actionEvent) {
        Databases database = new Databases();
        //cityInfo.setVisible(true);
        cityInfo.setText(database.getCityInfo(cityComboValue()));
    }
}
