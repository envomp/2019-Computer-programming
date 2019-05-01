package ee.taltech.iti0202.api.strategies;

import ee.taltech.iti0202.api.destinations.City;

import java.util.*;

public class HatesRainCityFinder implements CityFinderStrategy {

    private final int RAINSTARTCODE = 499;
    private final int RAINTENDCODE = 600;

    @Override
    public Optional<City> findBestCity(List<City> candidateCities) {

        if (candidateCities.size() == 0) {
            return Optional.empty();
        }

        List<City> realCandidates = new ArrayList<>();
        for (City city : candidateCities) {
            int allowed = 1;
            for (int code : city.getWeatherCodes()) {
                if (code < RAINTENDCODE && code > RAINSTARTCODE) {
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
