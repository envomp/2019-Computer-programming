package ee.taltech.iti0202.api.strategies;

import ee.taltech.iti0202.api.destinations.City;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class HatesRainCityFinder implements CityFinderStrategy {

    private final int rainStartCode = 499;
    private final int rainEndCode = 600;

    @Override
    public Optional<City> findBestCity(List<City> candidateCities) {
        List<City> realCandidates = new ArrayList<>();
        for (City city : candidateCities) {
            int allowed = 1;
            for (int code : city.getWeatherCodes()) {
                if (code < rainEndCode && code > rainStartCode) {
                    allowed--;
                }
            }
            if (allowed >= 0) {
                realCandidates.add(city);
            }
        }

        return realCandidates.stream()
                .filter(x -> x.getHumidity().stream()
                        .max(Comparator.comparing(Double::valueOf)).get() < 100.0d)
                .min(Comparator.comparing(City::getAverageHumidity));

    }
}
