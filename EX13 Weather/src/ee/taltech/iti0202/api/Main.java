package ee.taltech.iti0202.api;

import ee.taltech.iti0202.api.provider.OnlineDataController;
import ee.taltech.iti0202.api.strategies.CityFinderStrategy;
import ee.taltech.iti0202.api.strategies.HatesRainCityFinder;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        OnlineDataController controller = new OnlineDataController();
        try {
            controller.sendHttpRequest("Tallinn");
            controller.sendHttpRequest("Tartu");
            controller.sendHttpRequest("Pärnu");
            controller.sendHttpRequest("London");
            controller.sendHttpRequest("Moscow");
            controller.sendHttpRequest("New zealand");
            controller.sendHttpRequest("hong kong");
            controller.sendHttpRequest("china");

        } catch (IOException e) {
            e.printStackTrace();
        }

        controller.getCityMap().forEach((key, value) -> System.out.println(controller.getCity(key)));

        CityFinderStrategy strategy1 = new HatesRainCityFinder();
    }
}
