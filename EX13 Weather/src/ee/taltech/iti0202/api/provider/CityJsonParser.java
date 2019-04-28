package ee.taltech.iti0202.api.provider;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ee.taltech.iti0202.api.destinations.City;
import ee.taltech.iti0202.api.destinations.CityBuilder;

import java.util.ArrayList;
import java.util.List;

public class CityJsonParser {
    private String cityName;
    private String inputLine;
    private JsonObject jsonObject;
    private City city;

    public CityJsonParser(String cityName, String inputLine) {
        this.cityName = cityName;
        this.inputLine = inputLine;
    }

    public JsonObject getJsonObject() {
        return jsonObject;
    }

    public City getCity() {
        return city;
    }

    public CityJsonParser invoke() {
        jsonObject = (JsonObject) new JsonParser().parse(inputLine);
        JsonArray data = jsonObject.get("list").getAsJsonArray();
        List<Double> temperatures = new ArrayList<>();
        List<Double> humidity = new ArrayList<>();
        List<Integer> weatherCodes = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            temperatures.add(data.get(i).getAsJsonObject().get("main").getAsJsonObject().get("temp").getAsDouble());
            humidity.add(data.get(i).getAsJsonObject().get("main").getAsJsonObject().get("humidity").getAsDouble());
            weatherCodes.add(data.get(i).getAsJsonObject().get("weather").getAsJsonArray().get(0).getAsJsonObject().get("id").getAsInt());
        }

        JsonObject coords = jsonObject.get("city").getAsJsonObject().get("coord").getAsJsonObject();
        city = new CityBuilder()
                .setLat(coords.get("lat").getAsDouble())
                .setLon(coords.get("lon").getAsDouble())
                .setName(cityName)
                .setHumidity(humidity)
                .setTemperatures(temperatures)
                .setWeatherCodes(weatherCodes)
                .createCity();
        return this;
    }
}
