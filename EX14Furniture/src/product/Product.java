package product;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import furniture.Furniture;

import java.util.ArrayList;
import java.util.List;

public class Product implements Furniture {

    protected String modelName;
    protected Category category;
    protected Material material;
    protected Dimensions dimentions;
    protected Double priece;
    protected List<TypeSpecialVariables> typeSpecialVariables;

    public Product(String modelName, Category category, Material material, Dimensions dimentions, Double priece) {
        this(modelName, category, material, dimentions, priece, new ArrayList<>());
    }

    public Product(String modelName, Category category, Material material, Dimensions dimentions, Double priece,
                   ArrayList<TypeSpecialVariables> specialVariables) {
        this.modelName = modelName;
        this.category = category;
        this.material = material;
        this.dimentions = dimentions;
        this.priece = priece;
        typeSpecialVariables = specialVariables;
    }

    public void addExtraSpecialVariable(String a, String b) {
        typeSpecialVariables.add(new TypeSpecialVariables(a, b));
    }

    @Override
    public String toString() {
        JsonObject product = new JsonObject();
        product.addProperty("modelName", modelName);
        product.addProperty("category", category.toString());
        product.addProperty("material", material.toString());
        JsonObject jsonDimentsions = (JsonObject) new JsonParser().parse(dimentions.toString());
        product.add("dimensions", jsonDimentsions);
        product.addProperty("price", priece);
        JsonArray jsonArray = new JsonArray();
        for (TypeSpecialVariables variables : typeSpecialVariables) {
            JsonObject property = new JsonObject();
            property.addProperty(variables.toStringName(), variables.toStringSpecial());
            jsonArray.add(property);
        }
        product.add("variables", jsonArray);
        return product.toString();
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Dimensions getDimentions() {
        return dimentions;
    }

    public void setDimentions(Dimensions dimentions) {
        this.dimentions = dimentions;
    }

    public Double getPriece() {
        return priece;
    }

    public void setPriece(Double priece) {
        this.priece = priece;
    }

    public List<TypeSpecialVariables> getTypeSpecialVariables() {
        return typeSpecialVariables;
    }

    public void setTypeSpecialVariables(List<TypeSpecialVariables> typeSpecialVariables) {
        this.typeSpecialVariables = typeSpecialVariables;
    }
}
