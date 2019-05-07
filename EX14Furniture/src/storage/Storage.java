package storage;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import furniture.Furniture;
import product.Product;

import java.math.BigInteger;
import java.util.*;

public class Storage {
    private Set<Product> products = new HashSet<>();
    private Map<Furniture.Material, Double> arbitraryUnitsOfMaterial = new HashMap<>();

    public void addProducts(Product product, int i) {
        if (arbitraryUnitsOfMaterial.get(product.getMaterial()) >= product.getCategory().getMaterialCost() * i) {
            arbitraryUnitsOfMaterial.put(product.getMaterial(), arbitraryUnitsOfMaterial
                    .get(product.getMaterial()) - product.getCategory().getMaterialCost() * i);
            products.add(product);
        } else {
            throw new StorageOutOFMaterialExeption("There is insufficient amount of: "
                    + product.getMaterial().toString() + " - Needed:"
                    + product.getCategory().getMaterialCost() * i
                    + " actual:" + arbitraryUnitsOfMaterial.get(product.getMaterial()));
        }
    }

    public void addMaterial(Furniture.Material material, Double d) {
        arbitraryUnitsOfMaterial.putIfAbsent(material, 0d);
        arbitraryUnitsOfMaterial.put(material, arbitraryUnitsOfMaterial.get(material) + d);
    }

    @Override
    public String toString() {
        JsonParser parser = new JsonParser();
        JsonObject shop = new JsonObject();
        JsonArray jsonArrayProducts = new JsonArray();
        primary:
        for (Product product : products) {
            JsonObject property = new JsonObject();
            for (JsonElement jsonElement : jsonArrayProducts) {
                if (jsonElement.getAsJsonObject()
                        .get("item").getAsJsonObject().equals(parser.parse(product.toString()))) {
                    jsonElement.getAsJsonObject().addProperty("amount", jsonElement.getAsJsonObject()
                            .get("amount").getAsBigInteger().add(BigInteger.ONE));
                    continue primary;
                }
            }
            property.addProperty("amount", 1);
            property.add("item", parser.parse(product.toString()));
            jsonArrayProducts.add(property);
        }
        shop.add("products", jsonArrayProducts);
        JsonArray jsonArrayMaterials = generateJsonArrayOfMaterials();
        shop.add("materials", jsonArrayMaterials);

        return shop.toString();
    }

    public String getModelFurniture(String model) {
        JsonParser parser = new JsonParser();
        JsonArray shop = new JsonArray();

        for (Product product : products) {
            if (product.getModelName().equals(model)) {
                shop.add(parser.parse(product.toString()));
            }
        }
        return shop.toString();
    }

    public String getMaterialFurniture(Furniture.Material material) {
        JsonParser parser = new JsonParser();
        JsonArray shop = new JsonArray();

        for (Product product : products) {
            if (product.getMaterial().equals(material)) {
                shop.add(parser.parse(product.toString()));
            }
        }
        return shop.toString();
    }

    public String getCategoryFurniture(Furniture.Category category) {
        JsonParser parser = new JsonParser();
        JsonArray shop = new JsonArray();

        for (Product product : products) {
            if (product.getCategory().equals(category)) {
                shop.add(parser.parse(product.toString()));
            }
        }
        return shop.toString();
    }

    public String getSpecialFurniture(String special) {
        JsonParser parser = new JsonParser();
        JsonArray shop = new JsonArray();

        primary:
        for (Product product : products) {
            for (Furniture.TypeSpecialVariables specialVariables : product.getTypeSpecialVariables()) {
                if (specialVariables.toStringName().equals(special)) {
                    shop.add(parser.parse(product.toString()));
                    continue primary;
                }
            }
        }
        return shop.toString();
    }

    public String getAllMaterial() {
        return generateJsonArrayOfMaterials().toString();
    }

    private JsonArray generateJsonArrayOfMaterials() {
        JsonArray jsonArrayMaterials = new JsonArray();
        for (Furniture.Material material : arbitraryUnitsOfMaterial.keySet()) {
            JsonObject property = new JsonObject();
            property.addProperty(material.toString(), arbitraryUnitsOfMaterial.get(material));
            jsonArrayMaterials.add(property);
        }
        return jsonArrayMaterials;
    }
}
