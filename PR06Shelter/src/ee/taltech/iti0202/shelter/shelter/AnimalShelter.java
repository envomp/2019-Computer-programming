package ee.taltech.iti0202.shelter.shelter;

import ee.taltech.iti0202.shelter.animal.Animal;
import ee.taltech.iti0202.shelter.animalprovider.AnimalProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AnimalShelter {
    AnimalProvider ap;
    List<Animal> all_requests = new ArrayList<>();
    List<Animal> animals = new ArrayList<>();

    public AnimalShelter(AnimalProvider animalProvider) {
        this.ap = animalProvider;
    }

    /**
     * Gets a list of up to {@code count} animals with the given {@code type} and {@code color}.
     * This method should use animal provider which is set in constructor.
     * As the provider only can filter by type, you have to filter by color yourself.
     * Also, the number of results from provider is not defined, you have to call the provider
     * multiple time to get all the result (but not more than required).
     * The result should come in the order provided by the provider.
     * Also, provider can return duplicate animals. You should return only unique animals.
     * See {@link AnimalProvider#provide(Animal.Type)}.
     *
     * @param animalType Type, see {@link Animal.Type}.
     * @param color      Color used when filtering.
     * @param count      Maximum number of result to return.
     * @return Maximum {@code count} number of animals with the given type and color.
     */
    public List<Animal> getAnimals(Animal.Type animalType, String color, int count) {

        animals = ap.provide(animalType).stream().filter(x -> x.getType() == animalType)
                .filter(y -> y.getColor().equals(color)).collect(Collectors.toList());

        for (Animal animal : animals) if (!all_requests.contains(animal)) all_requests.add(animal);

        if (animals.size() >= count) return animals.subList(0, count);
        else if (animals.isEmpty() || all_requests.containsAll(animals)) return all_requests;
        return getAnimals(animalType, color, count);

    }
}