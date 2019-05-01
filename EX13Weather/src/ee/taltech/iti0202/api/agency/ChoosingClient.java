package ee.taltech.iti0202.api.agency;

import ee.taltech.iti0202.api.destinations.City;
import ee.taltech.iti0202.api.strategies.CityFinderStrategy;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ChoosingClient extends Client {

    public ChoosingClient(String name, String startingCity, CityFinderStrategy choosingStrategy, List<String> wantsToVisitCities) {
        super(name, startingCity, choosingStrategy, wantsToVisitCities);
    }

    public ChoosingClient(String name, String startingCity, CityFinderStrategy choosingStrategy) {
        super(name, startingCity, choosingStrategy);
    }

    @Override
    public Optional<City> chooseBestCity(List<City> possibleCities) {
        return getChoosingStrategy().findBestCity(
                possibleCities.stream()
                        .filter(x -> getCitiesThatWantsToVisit().contains(x.getName()))
                        .collect(Collectors.toList()));
    }
}
