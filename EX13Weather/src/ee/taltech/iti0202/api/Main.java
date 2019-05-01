package ee.taltech.iti0202.api;

import ee.taltech.iti0202.api.agency.ChoosingClient;
import ee.taltech.iti0202.api.agency.Client;
import ee.taltech.iti0202.api.agency.TravelAgency;
import ee.taltech.iti0202.api.provider.OnlineDataController;
import ee.taltech.iti0202.api.strategies.HatesRainCityFinder;
import ee.taltech.iti0202.api.strategies.LovesChangeCityFinder;
import ee.taltech.iti0202.api.strategies.StudentMadeCityFinder;
import ee.taltech.iti0202.api.strategies.WarmWeatherCityFinder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        OnlineDataController controller = new OnlineDataController();

        List<String> cityNames = new ArrayList<>(Arrays.asList(
                "Tallinn",
                "New Zealand"
                /*"Pärnu",
                "London",
                "Moscow",
                "Egipt",
                "hong Kong",
                "Aoulef",
                "Africa",
                "Mexico",
                "Libya"*/
        ));

        try {
            for (String cityName : cityNames) {
                controller.sendHttpRequest(cityName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //controller.getCityMap().forEach((key, value) -> System.out.println(controller.getJsonMap().get(value)));
        WarmWeatherCityFinder warmWeatherCityFinder = new WarmWeatherCityFinder();
        HatesRainCityFinder hatesRainCityFinder = new HatesRainCityFinder();
        LovesChangeCityFinder lovesChangeCityFinder = new LovesChangeCityFinder();
        StudentMadeCityFinder studentMadeCityFinder = new StudentMadeCityFinder();

        ChoosingClient ago = new ChoosingClient("Ago", "Tallinn", warmWeatherCityFinder/*, new ArrayList<>(Arrays.asList(
                "Hong Kong",
                "Aoulef",
                "Africa",
                "Mexico",
                "Libya"
        ))*/);

        TravelAgency travelAgency = new TravelAgency(cityNames, controller);
        System.out.println(travelAgency.findSuitableCitiesForClient(ago).get().getName());

    }
}
