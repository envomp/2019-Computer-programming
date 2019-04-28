package ee.taltech.iti0202.api.provider;

import com.google.gson.JsonObject;
import ee.taltech.iti0202.api.destinations.City;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class OnlineDataController {

    private Map<String, City> cityMap = new HashMap<>();
    private Map<City, JsonObject> jsonMap = new HashMap<>();

    public String getCity(String cityName) {
        try {
            if (cityMap.containsKey(cityName)) {
                return String.valueOf(cityMap.get(cityName));
            } else {
                return sendHttpRequest(cityName);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public String sendHttpRequest(String cityName) throws IOException {
        URL url = new URL("http://api.openweathermap.org/data/2.5/forecast?q=" +    // base url
                cityName.trim().replace(" ", "+") +                    // city
                "&APPID=3889fefc250cb2a5b4c9b123c2ca3106&units=metric");                 // key and metric

        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        url.openConnection().getInputStream()));

        String inputLine = in.readLine();
        if (inputLine != null) {
            CityJsonParser cityJsonParser = new CityJsonParser(cityName, inputLine).invoke();
            JsonObject jsonObject = cityJsonParser.getJsonObject();
            City city = cityJsonParser.getCity();
            cityMap.put(cityName, city);
            jsonMap.put(city, jsonObject);
            in.close();
            return String.valueOf(jsonObject);
        }

        in.close();
        return "";
    }

    public Map<String, City> getCityMap() {
        return cityMap;
    }

    public Map<City, JsonObject> getJsonMap() {
        return jsonMap;
    }
}
