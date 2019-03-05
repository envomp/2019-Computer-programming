package ee.taltech.iti0202.shelter.animalprovider;

import ee.taltech.iti0202.shelter.animal.Animal;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestAnimalProvider implements AnimalProvider {

    List<Animal> animals = new ArrayList<>();
    boolean swap = false;

    public void addAnimal(Animal animal) {
        if (!animals.contains(animal)) {
            animals.add(animal);
        }
    }

    @Override
    public List<Animal> provide(Animal.Type type) {
        if (swap) {
            swap = false;
            return animals.stream().filter(x -> x.getType() == type).collect(Collectors.toList()).subList(0, animals.size() / 2);
        } else {
            swap = true;
            return animals.stream().filter(x -> x.getType() == type).collect(Collectors.toList()).subList(animals.size() / 2 - 1, animals.size());
        }
    }
}
