package ee.taltech.iti0202.shelter.animalprovider;

import ee.taltech.iti0202.shelter.animal.Animal;

import java.util.ArrayList;
import java.util.List;

public class TestAnimalProvider implements AnimalProvider {

    List<Animal> animals = new ArrayList<>();

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    @Override
    public List<Animal> provide(Animal.Type type) {

        int size = animals.size();

        // construct new list from the returned view by list.subList() method
        List<Animal> first = new ArrayList<>(animals.subList(0, (size + 1) / 2));
        animals = new ArrayList<>(animals.subList((size + 1) / 2, size));

        return first;
    }
}
