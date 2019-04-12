package ee.taltech.iti0202.birdwatching.bird;

public class Bird {

    private String species;
    private double weight;
    private double wingspan;
    private Age age;
    private Sex sex;


    public Bird(String species, double weight, double wingspan, Age age, Sex sex) {
        this.age = age;
        this.sex = sex;
        this.species = species;
        this.weight = weight;
        this.wingspan = wingspan;
    }

    public String getSpecies() {
        return species;
    }

    public double getWeight() {
        return weight;
    }

    public double getWingspan() {
        return wingspan;
    }

    public Age getAge() {
        return age;
    }

    public Sex getSex() {
        return sex;
    }

    public enum Age {
        ADULT,
        YOUNG
    }

    public enum Sex {
        MALE,
        FEMALE,
        UNKNOWN
    }
}
