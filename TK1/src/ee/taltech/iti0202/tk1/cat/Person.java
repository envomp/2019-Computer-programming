package ee.taltech.iti0202.tk1.cat;


import java.util.ArrayList;
import java.util.List;

public class Person {

    private List<Cat> cats;

    public Person() {
        cats = new ArrayList<>();
    }

    public boolean addCat(Cat cat) {
        if (!cats.contains(cat)) {
            cats.add(cat);
            return false;
        }
        return true;
    }

    public List<Cat> getCats() {
        return cats;
    }

    public boolean sellCat(Person sellTo, Cat cat) {
        if (cats.contains(cat)) {
            cats.remove(cat);
            return sellTo.addCat(cat);
        }
        return false;
    }

}
