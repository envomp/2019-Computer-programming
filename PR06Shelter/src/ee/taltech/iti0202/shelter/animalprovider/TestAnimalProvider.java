package ee.taltech.iti0202.shelter.animalprovider;

import ee.taltech.iti0202.shelter.animal.Animal;

import java.util.ArrayList;
import java.util.List;

public class TestAnimalProvider implements AnimalProvider {

    List<Animal> animals = new ArrayList<>();
    List<Animal> animalBuffer = new ArrayList<>();

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    @Override
    public List<Animal> provide(Animal.Type type) {

        System.out.println("Called");
        if (animals.isEmpty()) {
            return new ArrayList<>();
        }

        animalBuffer = List.of(animals.get(animals.size() - 1));
        animals.remove(animals.size() - 1);

        return animalBuffer;
    }
}
