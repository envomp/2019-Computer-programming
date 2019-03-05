
package ee.taltech.iti0202.shelter.animal;

public abstract class Animal {

    private String color;

    public Animal(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public abstract Animal.Type getType();

    public enum Type {
        HIROLA, TARANTULA, WOMBAT
    }
}
