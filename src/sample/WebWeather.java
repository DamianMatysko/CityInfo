package sample;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class WebWeather {
    public static Map<String, Object> jsonToMap(String str) {
        return null;
    }

    public Weather getData(String city, String code2) {
        Weather weather = null;
        try {
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + city + "," + code2 + "&units=metric&appid=db07310c3c4774c0844ad3c090be3be3");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            if (connection.getResponseCode() == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));

                String output = br.readLine();
                System.out.println(output);

                JSONObject jsonObject = new JSONObject(output);
                String name = jsonObject.getString("name");
                String country = jsonObject.getJSONObject("sys").getString("country");
                double temp = jsonObject.getJSONObject("main").getDouble("temp");
                int humidity = jsonObject.getJSONObject("main").getInt("humidity");
                double lon = jsonObject.getJSONObject("coord").getDouble("lon");
                double lat = jsonObject.getJSONObject("coord").getDouble("lat");

                //SimpleDateFormat sdf= new SimpleDateFormat("HH:mm");
                long sunrise = jsonObject.getJSONObject("sys").getLong("sunrise");
                long sunset = jsonObject.getJSONObject("sys").getLong("sunset");
                Date dateSunrise = new Date(sunset);
                Date dateSunset = new Date(sunrise);
                double visibility = jsonObject.getDouble("visibility");


                weather = new Weather(name, country, temp, humidity, lon, lat, dateSunrise, dateSunset, visibility);
                //System.out.println(sdf.format(sunrise) + " " + sunset + " " + visibility);
                /*
                Date now = new Date(unix1000);
                Date now_x = new Date(unix_11000);
                String riseTime = sdf.format(now_x);
                String setTime = sdf.format(now);
                System.out.println(riseTime);
                System.out.println(setTime);
                 */


                return weather;
            }


        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return weather;
    }
}