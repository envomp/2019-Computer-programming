package ee.taltech.iti0202.api.strategies;

import ee.taltech.iti0202.api.destinations.City;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public class StudentMadeCityFinder implements CityFinderStrategy {
    @Override
    public Optional<City> findBestCity(List<City> candidateCities) {
        return Optional.ofNullable(candidateCities.get(new Random().nextInt(candidateCities.size())));
    }
}
