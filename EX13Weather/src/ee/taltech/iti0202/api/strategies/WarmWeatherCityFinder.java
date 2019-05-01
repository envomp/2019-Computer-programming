package ee.taltech.iti0202.api.strategies;

import ee.taltech.iti0202.api.destinations.City;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class WarmWeatherCityFinder implements CityFinderStrategy {
    @Override
    public Optional<City> findBestCity(List<City> candidateCities) {
        return Optional.of(candidateCities.get(0));
        //return candidateCities.stream().max(Comparator.comparing(City::getAverageTemperature));
    }
}
