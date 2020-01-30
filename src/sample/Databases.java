package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class Databases {
    public List getCitiesIntoObject(String countryName) {
        try {
            Connection con = getConnection();
            PreparedStatement pstm = con.prepareStatement(" SELECT city.Name, city.CountryCode, country.Code2, json_extract(Info, '$.Population') AS Info, country.Name FROM city JOIN country ON country.Code = city.CountryCode WHERE country.Name LIKE ?");
            pstm.setString(1, countryName);
            ResultSet rs = pstm.executeQuery();


            List<City> list = new ArrayList<>();
            while (rs.next()) {
                String name = rs.getString("city.Name");
                String code = rs.getString("city.CountryCode");
                String code2 = rs.getString("country.Code2");
                int population = rs.getInt("Info");
                String country = rs.getString("country.Name");
                City newCity = new City(name, population, code2, code, country);
                list.add(newCity);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public String getCityInfo(String cityName) {
        try {
            Connection con = getConnection();
            PreparedStatement pstm = con.prepareStatement(" select District, json_extract(Info ,'$.Population') AS Info from city where city.Name LIKE ?");
            pstm.setString(1, cityName);
            ResultSet rs = pstm.executeQuery();
            String city = null;
            String pop = null;
            //List <String> list= new ArrayList<>();
            while (rs.next()){
                city=rs.getString("District");
                pop=rs.getString("Info");
                System.out.println(pop);
                // System.out.println(country);
                //list.add(city);
            }

            pstm.close();   
            rs.close();
            return pop;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public List getCities(String countryName) {
        try {
            Connection con = getConnection();
            PreparedStatement pstm = con.prepareStatement(" SELECT city.Name FROM city join country on country.Code=city.CountryCode WHERE country.name LIKE ?");
            pstm.setString(1, countryName);
            ResultSet rs = pstm.executeQuery();
            String city;
            List <String> list= new ArrayList<>();
            while (rs.next()){
                city=rs.getString("name");
                // System.out.println(country);
                list.add(city);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public List getCountries() {
        try {
            Connection con = getConnection();
            PreparedStatement pstm = con.prepareStatement("SELECT Name FROM country");
        ResultSet rs = pstm.executeQuery();
            String country;
            List <String> list= new ArrayList<>();
        while (rs.next()){
            country=rs.getString("name");
           // System.out.println(country);
            list.add(country);
        }
        return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://itsovy.sk:3306/world_x?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "student", "kosice2019");
        return con;
    }
}


