package ee.taltech.iti0202.api.strategies;

import ee.taltech.iti0202.api.destinations.City;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public class LovesChangeCityFinder implements CityFinderStrategy {
    @Override
    public Optional<City> findBestCity(List<City> candidateCities) {
        if (candidateCities.size() == 0) return Optional.empty();
        return Optional.of(candidateCities.get(new Random().nextInt(candidateCities.size())));
    }
}
