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
import java.util.Map;

public class WebWeather {
    public static Map<String, Object> jsonToMap(String str){
        return null;
    }
    public Weather getData(String city,String code2){
        Weather weather=null;
        try {
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q="+city+","+code2+"&units=metric&appid=db07310c3c4774c0844ad3c090be3be3");
            HttpURLConnection connection=(HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            if (connection.getResponseCode() == 200){
                BufferedReader br =new BufferedReader(new InputStreamReader((connection.getInputStream())));

                String output=br.readLine();
                System.out.println(output);

                JSONObject jsonObject = new JSONObject(output);
                String name=jsonObject.getString("name");
                String country = jsonObject.getJSONObject("sys").getString("country");
                double temp=jsonObject.getJSONObject("main").getDouble("temp");
                int humidity=jsonObject.getJSONObject("main").getInt("humidity");
                double lon=jsonObject.getJSONObject("coord").getDouble("lon");
                double lat= jsonObject.getJSONObject("coord").getDouble("lat");

                weather =new Weather(name, country, temp, humidity, lon, lat);
                //weather.setTemp(temp);
               // weather.setHumidity(humidity);

                //System.out.println(weather.getTemp());
                System.out.println(temp);
                System.out.println(humidity);
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