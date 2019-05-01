package ee.taltech.iti0202.api.agency;

import ee.taltech.iti0202.api.destinations.City;
import ee.taltech.iti0202.api.strategies.CityFinderStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Client {
    private String name;
    private String startingCity;
    private CityFinderStrategy choosingStrategy;
    private List<String> wantsToVisitCities;

    public Client(String name, String startingCity, CityFinderStrategy choosingStrategy) {
        this(name, startingCity, choosingStrategy, new ArrayList<>());
    }

    public Client(String name, String startingCity, CityFinderStrategy strategy, List<String> wantsToVisitCities) {
        this.name = name;
        this.startingCity = startingCity;
        this.choosingStrategy = strategy;
        this.wantsToVisitCities = wantsToVisitCities;
    }

    public String getName() {
        return name;
    }

    public String getStartingCity() {
        return startingCity;
    }

    public CityFinderStrategy getChoosingStrategy() {
        return choosingStrategy;
    }

    public List<String> getCitiesThatWantsToVisit() {
        return wantsToVisitCities;
    }

    public Optional<City> chooseBestCity(List<City> possibleCities) {
        return choosingStrategy.findBestCity(possibleCities);
    }
}
