package ee.taltech.iti0202.api.strategies;

import ee.taltech.iti0202.api.destinations.City;

import java.util.*;

public class LovesChangeCityFinder implements CityFinderStrategy {
    @Override
    public Optional<City> findBestCity(List<City> candidateCities) {

        HashMap<Integer, City> bestCity = new HashMap<>();
        for (City city : candidateCities) {
            String pervious = "000";
            int total = 0;
            for (int weatherQuota : city.getWeatherCodes()) {
                String weather = Integer.toString(weatherQuota);
                if (weather.substring(0, 1).equals(pervious.substring(0, 1))) {
                    total += 100;
                } else if (weather.substring(1, 3).equals(pervious.substring(1, 3))) {
                    total -= 10;
                } else {
                    total += 20;
                }
            }
            bestCity.put(total, city);
        }
        Optional<City> cityOptional = Optional.ofNullable(Collections.max(bestCity.entrySet(),
                Comparator.comparing(Map.Entry::getKey)).getValue());
        if (cityOptional.isPresent()) {
            return cityOptional;
        }
        return Optional.ofNullable(candidateCities.get(new Random().nextInt(candidateCities.size())));
    }
}
