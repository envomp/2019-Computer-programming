package ee.taltech.iti0202.shelter.animalprovider;

import ee.taltech.iti0202.shelter.animal.Animal;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestAnimalProvider implements AnimalProvider {

    List<Animal> animals = new ArrayList<>();

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    @Override
    public List<Animal> provide(Animal.Type type) {
        return animals.stream().filter(x -> x.getType() == type).collect(Collectors.toList());
    }
}
