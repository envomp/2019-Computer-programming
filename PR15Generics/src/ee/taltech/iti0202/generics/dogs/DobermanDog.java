package ee.taltech.iti0202.generics.dogs;

import ee.taltech.iti0202.generics.Animal;

public class DobermanDog extends Animal {
    public DobermanDog(String name) {
        super(name);
    }

    @Override
    public void sound() {
        System.out.println("Woof!");
    }
}
