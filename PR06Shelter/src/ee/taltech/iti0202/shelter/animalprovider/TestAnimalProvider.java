package ee.taltech.iti0202.shelter.animalprovider;

import ee.taltech.iti0202.shelter.animal.Animal;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestAnimalProvider implements AnimalProvider {

    List<Animal> animals = new ArrayList<>();
    List<Animal> sentAnimals = new ArrayList<>();
    List<Animal> animalBuffer = new ArrayList<>();

    public void addAnimal(Animal animal) {
        if (!animals.contains(animal)) {
            animals.add(animal);
        }
    }

    @Override
    public List<Animal> provide(Animal.Type type) {
        int i = 0;
        animalBuffer = new ArrayList<>();

        for (Animal animal : animals) {

            if (animal.getType() == type && !sentAnimals.contains(animal)) {
                i++;
                if (i == 4) {
                    break;
                }
                sentAnimals.add(animal);
                animalBuffer.add(animal);
            }

        }
        return animalBuffer;
    }
}
