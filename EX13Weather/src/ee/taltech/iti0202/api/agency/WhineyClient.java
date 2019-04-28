package ee.taltech.iti0202.api.agency;

import ee.taltech.iti0202.api.destinations.City;
import ee.taltech.iti0202.api.strategies.CityFinderStrategy;

import java.util.List;
import java.util.Optional;

public class WhineyClient extends Client {
    public WhineyClient(String name, String startingCity, CityFinderStrategy choosingStrategy, List<String> wantsToVisitCities) {
        super(name, startingCity, choosingStrategy);
    }

    @Override
    public Optional<City> chooseBestCity(List<City> possibleCities) {
        return getChoosingStrategy().findBestCity(possibleCities); // ?
    }
}
