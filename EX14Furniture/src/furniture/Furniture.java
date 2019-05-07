package furniture;

import com.google.gson.JsonObject;

public interface Furniture {

    enum Category {
        TABLE("Table", 2d),
        CHAIR("Chair", 1.5d),
        SOFA("Sofa", 4d),
        ARMCHAIR("Armchair", 4d),
        BED("Bed", 6d),
        LAMP("Lamp", 1d),
        DRAWER("Drawer", 2d),
        BOOKCASE("Bookcase", 8d);

        private final String name;
        private final Double materialCost;

        Category(String s, Double d) {
            name = s;
            materialCost = d;
        }

        public String toString() {
            return this.name;
        }

        public Double getMaterialCost() {
            return materialCost;
        }
    }

    enum Material {
        WOOD("Wood"),
        PLASTIC("Plastic"),
        METAL("Metal");

        private final String name;

        Material(String s) {
            name = s;
        }

        public String toString() {
            return this.name;
        }
    }

    class Dimensions {
        private final double x;
        private final double y;
        private final double z;

        public Dimensions(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public String toString() {
            JsonObject product = new JsonObject();
            product.addProperty("x", String.format("%.2f", x));
            product.addProperty("y", String.format("%.2f", y));
            product.addProperty("z", String.format("%.2f", z));
            return product.toString();
        }
    }

    class TypeSpecialVariables {
        private final String name;
        private String stringResult;
        private Double doubleResult;

        public TypeSpecialVariables(String s, String r) {
            name = s;
            stringResult = r;
        }

        TypeSpecialVariables(String s, Double r) {
            name = s;
            doubleResult = r;
        }

        public String toStringName() {
            return name;
        }

        public String toStringSpecial() {
            return stringResult == null ? String.format("%.2f", doubleResult) : stringResult;
        }
    }
}
