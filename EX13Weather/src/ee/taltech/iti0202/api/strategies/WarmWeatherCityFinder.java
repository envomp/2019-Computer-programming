package ee.taltech.iti0202.api.strategies;

import ee.taltech.iti0202.api.destinations.City;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class WarmWeatherCityFinder implements CityFinderStrategy {
    @Override
    public Optional<City> findBestCity(List<City> candidateCities) {
        Optional<City> cityOptional = candidateCities.stream().max(Comparator.comparing(City::getAverageTemperature));
        if (cityOptional.isPresent()) {
            return cityOptional;
        }
        return Optional.ofNullable(candidateCities.get(new Random().nextInt(candidateCities.size())));
    }
}
