package ee.taltech.iti0202.api.agency;

import ee.taltech.iti0202.api.destinations.City;
import ee.taltech.iti0202.api.provider.OnlineDataController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TravelAgency {

    private List<String> cityNames;
    private OnlineDataController dataController;

    public TravelAgency(List<String> cityNames, OnlineDataController dataController) {
        this.cityNames = cityNames;
        this.dataController = dataController;
    }

    /**
     * Ise agency doesn't have a destination city yet, adds it to the list.
     *
     * @param city city name.
     */
    public void addCity(String city) {
        if (!cityNames.contains(city)) {
            cityNames.add(city);
        }
    }

    /**
     * This method tries to find a suitable city for the client to visit.
     * <p>
     * It uses OnlineDataController, to get data for the cities.
     * After getting data about a city, SAVE IT for the duration of the cycle. OpenWeather API updates data every 10 minutes.
     * Create a City object using the CityBuilder here.
     *
     * @param client a client who wants to go somewhere.
     * @return Optional city if the client was happy with it.
     */
    public Optional<City> findSuitableCitiesForClient(Client client) {
        List<City> cities = new ArrayList<>();
        for (String name : cityNames) {
            dataController.getCity(name);
            cities.add(dataController.getCityMap().get(name));
        }
        return client.chooseBestCity(
                cities.stream()
                        .filter(x -> !x.getName().equals(client.getStartingCity()))
                        .collect(Collectors.toList()));
    }

    public List<String> getCityList() {
        return cityNames;
    }
}
