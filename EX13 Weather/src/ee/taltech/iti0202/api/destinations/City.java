package ee.taltech.iti0202.api.destinations;

import java.util.List;

public class City {

    private String name;
    private double lon;
    private double lat;
    private List<Double> temperatures; List<Double> humidity;
    private List<Integer> weatherCodes;

    City(String name, double lon, double lat, List<Double> temperatures, List<Double> humidity, List<Integer> weatherCodes) {
        this.name = name;
        this.lon = lon;
        this.lat = lat;
        this.temperatures = temperatures;
        this.humidity = humidity;
        this.weatherCodes = weatherCodes;
    }

    public String getName() {
        return this.name;
    }

    public double getLon() {
        return this.lon;
    }

    public double getLat() {
        return this.lat;
    }

    public List<Double> getTemperatures() {
        return this.temperatures;
    }

    public List<Double> getHumidity() {
        return this.humidity;
    }

    public List<Integer> getWeatherCodes() {
        return this.weatherCodes;
    }

    public double getAverageTemperature() {
        return this.temperatures.stream().mapToDouble(Double::doubleValue).average().getAsDouble(); //why not optional ...
    }

    public double getAverageHumidity() {
        return this.humidity.stream().mapToDouble(Double::doubleValue).average().getAsDouble();
    }

}
