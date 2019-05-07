package product;

import furniture.Furniture;

import java.util.ArrayList;

public class ProductBuilder {
    private String modelName;
    private Furniture.Category category;
    private Furniture.Material material;
    private Furniture.Dimensions dimentions;
    private Double priece;
    private ArrayList<Furniture.TypeSpecialVariables> specialVariables = new ArrayList<>();

    public ProductBuilder setModelName(String modelName) {
        this.modelName = modelName;
        return this;
    }

    public ProductBuilder setCategory(Furniture.Category category) {
        this.category = category;
        return this;
    }

    public ProductBuilder setMaterial(Furniture.Material material) {
        this.material = material;
        return this;
    }

    public ProductBuilder setDimentions(Furniture.Dimensions dimentions) {
        this.dimentions = dimentions;
        return this;
    }

    public ProductBuilder setPriece(Double priece) {
        this.priece = priece;
        return this;
    }

    public ProductBuilder setSpecialVariables(ArrayList<Furniture.TypeSpecialVariables> specialVariables) {
        this.specialVariables = specialVariables;
        return this;
    }

    public Product createProduct() {
        return new Product(modelName, category, material, dimentions, priece, specialVariables);
    }
}