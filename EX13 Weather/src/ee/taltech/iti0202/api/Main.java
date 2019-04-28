package ee.taltech.iti0202.api;

import ee.taltech.iti0202.api.provider.OnlineDataController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        OnlineDataController controller = new OnlineDataController();

        List<String> cityNames = new ArrayList<>(Arrays.asList("Tallinn", "Tartu", "Pärnu", "London", "Moscow", "New Zealand", "hong Kong", "China"));

        try {
            for (String cityName : cityNames) {
                controller.sendHttpRequest(cityName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        controller.getCityMap().forEach((key, value) -> System.out.println(controller.getCity(key)));

    }
}
