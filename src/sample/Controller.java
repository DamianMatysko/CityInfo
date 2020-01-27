package sample;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.ComboBox;

import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class Controller {
    public ComboBox combo1;
    public ComboBox combo2;
    List<String> countries;
    public Controller() throws SQLException, ClassNotFoundException {
        //Databases database = new Databases();
       // countries = database.getCountries();

    }


    public void getCountries(Event event) {
        Databases database = new Databases();
        combo1.getItems().setAll(database.getCountries());
    }
}
