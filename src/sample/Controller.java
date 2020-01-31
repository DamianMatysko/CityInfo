package sample;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class Controller {
    public ComboBox combo1;
    public ComboBox combo2;
    public Label populationInfo;
    public Button findInfo;
    public Label districInfo;

    public Label temperature;
    public Label humidity;
    public Label name;
    public Label country;
    public Label lon;
    public Label lat;
    public CheckBox moreInfo;
    public Label visibility;
    public Label sunset;
    public Label sunrize;
    private List<City> cities;
    List<String> countries;
    //URL url= null;
    String url= null;

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


    public void findInformation(ActionEvent actionEvent) throws MalformedURLException {
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
       // WebWeather webWeather = new WebWeather();
        //webWeather.getData("Bratislava","SK");

        //Weather weather = new WebWeather().getData("Košice", "SK");
        Weather weather = new WebWeather().getData(city.getName(), city.getCode2());
        name.setText("Name: "+String.valueOf(weather.getName()));
        country.setText("Country: "+String.valueOf(weather.getCountry()));
        temperature.setText("Temperature: "+ String.valueOf(weather.getTemp())+"°C");
        humidity.setText("Humidity: "+convertToString(weather.getHumidity())+"%");
        lon.setText("Lon: "+String.valueOf(weather.getLon()));
        lat.setText("Lat: "+String.valueOf(weather.getLat()));


        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        visibility.setText("Visibility: "+String.valueOf(weather.getVisibility()));
        sunset.setText("Sunset: "+String.valueOf(sdf.format(weather.getSunset())));
        sunrize.setText("Sunrize: "+String.valueOf(sdf.format(weather.getSunrise())));

        url="http://www.google.com/maps/place/"+String.valueOf(weather.getLat())+","+String.valueOf(weather.getLon());


    }

    public void showMoreInfo(ActionEvent actionEvent) {
        if (moreInfo.isSelected()){
            visibility.setVisible(true);
            sunrize.setVisible(true);
            sunset.setVisible(true);
        }else {
            visibility.setVisible(false);
            sunset.setVisible(false);
            sunrize.setVisible(false);
        }
    }

    public void locateCity(ActionEvent actionEvent) throws IOException {
        //String pokus="http://www.google.com/maps/place/0,0";
        //String out = new Scanner(url.openStream(), "UTF-8").useDelimiter("\\A").next();

        java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
    }
}
