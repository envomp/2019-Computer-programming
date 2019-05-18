package ee.taltech.iti0202.generics;

import ee.taltech.iti0202.generics.cats.PersianCat;
import ee.taltech.iti0202.generics.dogs.DobermanDog;
import ee.taltech.iti0202.generics.foods.Food;
import ee.taltech.iti0202.generics.foods.Meat;

import java.util.Optional;

public class AnimalBox<E> {

    private E animal;

    public AnimalBox() {

    }

    public static void main(String[] args) {
        // Cat
        AnimalBox<PersianCat> persianCatAnimalBox = new AnimalBox<>();
        PersianCat persianCat = new PersianCat("Elf");
        persianCatAnimalBox.put(persianCat);

        Optional<PersianCat> catFromBox = persianCatAnimalBox.getAnimal();
        System.out.println(catFromBox.isPresent()); // true
        System.out.println(persianCat.equals(catFromBox.get())); // true

        persianCatAnimalBox.sound(); // Prrr-prrr

        // Dog
        AnimalBox<DobermanDog> dogAnimalBox = new AnimalBox<>();

        DobermanDog dobby = new DobermanDog("Dobby");
        dogAnimalBox.put(dobby);

        dogAnimalBox.sound(); // Woof!

        // Food
        Food meat = new Meat();
        persianCatAnimalBox.feed(meat); // Elf was fed with MEAT
    }

    public void feed(Food food) {
        if (animal != null) {
            System.out.println(String.format("%s was fed with %s", ((Animal) animal).name, food.toString()));
        }
    }

    public void sound() {
        try {
            ((Animal) animal).sound();
        } catch (NullPointerException e) {
            e.fillInStackTrace();
        }
    }

    public void put(E animal) {
        this.animal = animal;
    }

    public Optional<E> getAnimal() {
        return Optional.of(animal);
    }
}
