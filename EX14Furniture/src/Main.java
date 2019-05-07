import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import furniture.Furniture;
import product.Product;
import product.ProductBuilder;
import storage.Storage;
import storage.StorageOutOFMaterialExeption;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {
    private static final Random random = new Random();

    public static void main(String[] args) {
        Storage storage = new Storage();

        /// First load material
        storage.addMaterial(Furniture.Material.WOOD, 5d);

        /// Then create Furniture
        for (int i = 0; i < 3; i++) {
            Product sameChair = new ProductBuilder()
                    .setCategory(Furniture.Category.CHAIR)
                    .setMaterial(Furniture.Material.WOOD)
                    .setDimentions(new Furniture.Dimensions(10, 20, 30))
                    .setModelName("Grandfathers Chair")
                    .setPriece(20.99d)
                    .createProduct();

            storage.addProducts(sameChair, 1);
        }

        Product chair = new ProductBuilder()
                .setCategory(Furniture.Category.CHAIR)
                .setMaterial(Furniture.Material.WOOD)
                .setDimentions(new Furniture.Dimensions(10, 20, 30))
                .setModelName("Grandfathers Chair")
                .setPriece(20.99d)
                .createProduct();
        chair.addExtraSpecialVariable("EXTRA LEG", "From defect to the latest fashion model");
        chair.addExtraSpecialVariable("SUPER SCREECHY", "It is definitely not broken!");

        try {
            storage.addProducts(chair, 1);
        } catch (StorageOutOFMaterialExeption e) {
            e.printStackTrace();
        }

        // added more wood
        storage.addMaterial(Furniture.Material.WOOD, 2d);

        storage.addProducts(chair, 1);

        runTests(storage);

        // time to go on a shopping spree
        storage.addMaterial(Furniture.Material.WOOD, 1000d);
        storage.addMaterial(Furniture.Material.METAL, 1000d);
        storage.addMaterial(Furniture.Material.PLASTIC, 1000d);

        var x = List.of("Moms ", "Dads ", "Grandfathers ", "Super ", "Cheap ", "Big ", "Decorative ", "Small ");
        for (int i = 0; i < 1000; i++) {
            Loader.progressPercentage(i, 999);
            try {
                Furniture.Category name = Arrays.asList(Furniture.Category.values())
                        .get(random.nextInt(Furniture.Category.values().length));
                Product next = new ProductBuilder()
                        .setCategory(name)
                        .setMaterial(Arrays.asList(Furniture.Material.values())
                                .get(random.nextInt(Furniture.Material.values().length)))
                        .setDimentions(new Furniture.Dimensions(20, 20, 20))
                        .setModelName(x.get(random.nextInt(x.size())) + name.toString())
                        .setPriece((double) random.nextInt(10) * 10 + 9)
                        .createProduct();

                if (random.nextDouble() > 0.99d) {
                    next.addExtraSpecialVariable("EXTRA LEG", "From defect to the latest fashion model!");
                }
                if (random.nextDouble() > 0.99d) {
                    next.addExtraSpecialVariable("SUPER SCREECHY", "It is definitely not broken!");
                }
                if (random.nextDouble() > 0.95d) {
                    next.addExtraSpecialVariable("INSIDE OUT", "Fashion is in the eyes of the viewer!");
                }

                storage.addProducts(next, 3);
            } catch (StorageOutOFMaterialExeption e) {
                System.out.println(e.getMessage());

                // add some more
                storage.addMaterial(Furniture.Material.WOOD, 10d);
                storage.addMaterial(Furniture.Material.METAL, 10d);
                storage.addMaterial(Furniture.Material.PLASTIC, 10d);
            }

        }
        runTests(storage);

    }

    private static void runTests(Storage storage) {
        // get all furniture
        System.out.println("\n/////////////////////////////////// furniture ///////////////////////////////////////"
                + "///////////////////////////////////////////////////////////////");
        pretty(storage.toString());

        // get all material
        System.out.println("/////////////////////////////////// material //////////////////////////////////////////"
                + "////////////////////////////////////////////////////////////");
        pretty(storage.getAllMaterial());

        // get model furniture
        System.out.println("/////////////////////////////////// model ////////////////////////////////////"
                + "//////////////////////////////////////////////////////////////////");
        pretty(storage.getModelFurniture("Grandfathers Chair"));

        // get category furniture
        System.out.println("/////////////////////////////////// category ///////////////////////////////////"
                + "///////////////////////////////////////////////////////////////////");
        pretty(storage.getCategoryFurniture(Furniture.Category.CHAIR));

        // get material furniture
        System.out.println("/////////////////////////////////// material ////////////////////////////////////"
                + "//////////////////////////////////////////////////////////////////");
        pretty(storage.getMaterialFurniture(Furniture.Material.WOOD));

        // get special furniture
        System.out.println("/////////////////////////////////// special /////////////////////////////////////////"
                + "/////////////////////////////////////////////////////////////");
        pretty(storage.getSpecialFurniture("EXTRA LEG"));
    }

    private static void pretty(String x) {
        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(x)));
    }
}
