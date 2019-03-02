package ee.taltech.iti0202.tk1.cat;

public class Cat {
    private static String cname;
    private static Integer cage;
    private static String ccolor;

    public Cat(String name, int age, String color) {
        cname = name;
        cage = age;
        ccolor = color;
    }

    public Cat(String name) {
        cname = name;
        cage = null;
        ccolor = null;
    }

    public String getName() {
        return cname;
    }

    public Integer getAge() {
        return cage;
    }

    public String getColor() {
        return ccolor;
    }

    public static void main(String[] args) {
        Cat cat = new Cat("Mati");
        System.out.println(cat);
        Cat muri = new Cat("Muri", 3, "White");
        System.out.println(muri);

        Person malle = new Person();
        Person kalle = new Person();
        System.out.println(malle.addCat(cat)); // true
        System.out.println(malle.addCat(cat)); // false

        malle.sellCat(kalle, cat); // true
        System.out.println(malle.getCats()); // []
        System.out.println(kalle.getCats()); // [Mati]
    }
}

