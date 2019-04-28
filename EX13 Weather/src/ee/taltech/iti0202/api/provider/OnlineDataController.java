package ee.taltech.iti0202.api.provider;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class OnlineDataController {

    private Map<String, JsonObject> cityMap;

    public OnlineDataController() {
        cityMap = new HashMap<>();
    }

    /**
     * Tries to get forecast data for the cityName. If there is no data or cityName doesn't exist, return an empty string.
     *
     * @param cityName Name of the city
     * @return String in the form of a json-string
     */
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

    /**
     * Sends a http request and saves the result to cityMap
     *
     * @param cityName Name of the city
     * @return String in the form of a json-string
     */
    public String sendHttpRequest(String cityName) throws IOException {
        URL url = new URL("http://api.openweathermap.org/data/2.5/forecast?q=" +    // base url
                cityName.trim().replace(" ", "+") +                    // city
                "&APPID=3889fefc250cb2a5b4c9b123c2ca3106&units=metric");                 // key and metric

        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        url.openConnection().getInputStream()));
        String inputLine;

        if ((inputLine = in.readLine()) != null) {
            JsonObject jsonObject = (JsonObject) new JsonParser().parse(inputLine);
            cityMap.put(cityName, jsonObject);
            in.close();
            return String.valueOf(jsonObject);
        }

        in.close();
        return "";
    }

    public Map<String, JsonObject> getCityMap() {
        return cityMap;
    }
}
