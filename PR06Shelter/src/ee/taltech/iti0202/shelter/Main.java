package ee.taltech.iti0202.shelter;

import ee.taltech.iti0202.shelter.animal.Animal;
import ee.taltech.iti0202.shelter.animal.Wombat;
import ee.taltech.iti0202.shelter.animalprovider.TestAnimalProvider;
import ee.taltech.iti0202.shelter.shelter.AnimalShelter;

public class Main {
    public static void main(String[] args) {
        TestAnimalProvider testAnimalProvider = new TestAnimalProvider();
        AnimalShelter shelter = new AnimalShelter(testAnimalProvider);
        Animal w0 = new Wombat("red");
        Animal w1 = new Wombat("blue");
        Animal w2 = new Wombat("blue");
        Animal w3 = new Wombat("blue");
        Animal w4 = new Wombat("blue");

        testAnimalProvider.addAnimal(w0);
        testAnimalProvider.addAnimal(w1);
        testAnimalProvider.addAnimal(w0);
        testAnimalProvider.addAnimal(w0);
        testAnimalProvider.addAnimal(w0);
        testAnimalProvider.addAnimal(w1);
        testAnimalProvider.addAnimal(w0);
        testAnimalProvider.addAnimal(w0);
        testAnimalProvider.addAnimal(w1);
        testAnimalProvider.addAnimal(w0);
        testAnimalProvider.addAnimal(w0);
        testAnimalProvider.addAnimal(w0);
        testAnimalProvider.addAnimal(w0);
        testAnimalProvider.addAnimal(w0);
        testAnimalProvider.addAnimal(w2);
        testAnimalProvider.addAnimal(w3);
        testAnimalProvider.addAnimal(w4);


        System.out.println(shelter.getAnimals(Animal.Type.WOMBAT, "blue", 4));

    }
}

