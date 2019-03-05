package ee.taltech.iti0202.shelter.animal;

/**
 * https://en.wikipedia.org/wiki/Tarantulas
 */
public class Tarantula extends Animal {
    public Tarantula(String color) {
        super(color);
    }

    @Override
    public Type getType() {
        return Type.TARANTULA;
    }

}
