package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class Databases {
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


