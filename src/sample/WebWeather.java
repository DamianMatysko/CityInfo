package sample;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
            URL url = new URL("https://samples.openweathermap.org/data/2.5/weather?q="+city+",uk&appid=b6907d289e10d714a6e88b30761fae22");
            HttpURLConnection connection=(HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            if (connection.getResponseCode() == 200){
                BufferedReader br =new BufferedReader(new InputStreamReader((connection.getInputStream())));

                String output=br.readLine();
                System.out.println(output);

                JSONParser parser = new JSONParser();
                JSONObject json = (JSONObject) parser.parse(output);

                JSONObject main = (JSONObject) json.get("main");
                double temp = (double) main.get("temp");
                //Integer humidity = (Integer) main.get("humidity");
                //weather.setTemp(temp);

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
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return weather;
    }
}
