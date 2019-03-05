package ee.taltech.iti0202.shelter;

import ee.taltech.iti0202.shelter.animal.Animal;
import ee.taltech.iti0202.shelter.animal.Wombat;
import ee.taltech.iti0202.shelter.animalprovider.TestAnimalProvider;
import ee.taltech.iti0202.shelter.shelter.AnimalShelter;

public class Main {
    public static void main(String[] args) {
        TestAnimalProvider testAnimalProvider = new TestAnimalProvider();
        AnimalShelter shelter = new AnimalShelter(testAnimalProvider);
        Animal wo = new Wombat("blue");
        testAnimalProvider.addAnimal(wo);
        testAnimalProvider.addAnimal(wo);
        testAnimalProvider.addAnimal(wo);

        System.out.println(shelter.getAnimals(Animal.Type.WOMBAT, "blue", 10));

    }
}
