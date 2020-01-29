package sample;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;

public class Controller {
    public ComboBox combo1;
    public ComboBox combo2;
    public Label populationInfo;
    public Button findInfo;
    public Label districInfo;
    public Label weatherInfo;
    private List<City> cities;
    List<String> countries;

    public String countryComboValue() {
        return (String) combo1.getValue();
    }

    public String cityComboValue() {
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
        String country = null;
        country = countryComboValue();
       // List<City> cities;
        if (country != null) {
            Databases database = new Databases();
            cities = database.getCitiesIntoObject(country);
            combo2.getItems().clear();
            for (City s : cities) {
                System.out.println(s.getName());
                combo2.getItems().add(s.getName());
            }
            combo2.setDisable(false);
        }
    }
/* old methoed
    public void getCites(Event event) {
        Databases database = new Databases();
        combo2.getItems().setAll(database.getCities(countryComboValue()));

    }

 */

    public void isVisibe(ActionEvent actionEvent) {
        combo2.setVisible(true);
    }

    /* old methoed
    public void findInformation(ActionEvent actionEvent) {
        Databases database = new Databases();
        //cityInfo.setVisible(true);
        populationInfo.setText(database.getCityInfo(cityComboValue()));

    }

     */

    public String convertToString(int number){
        //String txtNumber= String.valueOf(number);
        //.replace("", " ").trim()
        String pattern = "###,###.###";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        String txtNumber = decimalFormat.format(number);



        return txtNumber;
    }


    public void findInformation(ActionEvent actionEvent) {
        String cityName = cityComboValue();
        City city = null;
        for (City c : cities){
            if (c.getName().equals(cityName)){
                city = c;
                break;
            }
        }
        if (city == null)
            return;

        populationInfo.setText("Population: "+convertToString(city.getPopulatin()));
        districInfo.setText("Distric: "+city.getCode2());
    }
}
