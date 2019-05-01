package ee.taltech.iti0202.api.strategies;

import ee.taltech.iti0202.api.destinations.City;

import java.util.*;

public class HatesRainCityFinder implements CityFinderStrategy {

    private final int RAIN_START_CODE = 499;
    private final int RAINT_END_CODE = 600;

    @Override
    public Optional<City> findBestCity(List<City> candidateCities) {

        List<City> realCandidates = new ArrayList<>();
        for (City city : candidateCities) {
            int allowed = 1;
            for (int code : city.getWeatherCodes()) {
                if (code < RAINT_END_CODE && code > RAIN_START_CODE) {
                    allowed--;
                }
            }
            if (allowed >= 0) {
                realCandidates.add(city);
            }
        }
        Optional<City> cityOptional = realCandidates.stream()
                .filter(x -> x.getHumidity().stream()
                        .max(Comparator.comparing(Double::valueOf)).get() < 100.0d)
                .min(Comparator.comparing(City::getAverageHumidity));

        if (cityOptional.isPresent()) {
            return cityOptional;
        }
        return Optional.ofNullable(candidateCities.get(new Random().nextInt(candidateCities.size())));


    }
}
