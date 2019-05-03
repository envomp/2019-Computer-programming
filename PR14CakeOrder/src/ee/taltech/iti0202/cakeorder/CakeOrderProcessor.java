package ee.taltech.iti0202.cakeorder;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import java.util.ArrayList;
import java.util.List;

public class CakeOrderProcessor {

    public enum CakeOrderProcessorType {
        MAKE_DAIRY_FREE,
        COUNT_TOTAL_SUM,
        REMOVE_BEST_BEFORE_DAY_OVER
    }

    private int order = 0;
    private static final int year = 2019;
    private CakeOrderProcessorType type;
    private final List<String> dairy = new ArrayList<>() {{
        add("milk");
        add("cream-cheese");
        add("yoghurt");
    }};

    public CakeOrderProcessor(CakeOrderProcessorType type) {
        this.type = type;
    }

    public String process(String jsonInput) {

        order += 1;
        double total = 0;
        JsonObject jsonObject = (JsonObject) new JsonParser().parse(jsonInput);
        JsonArray cakes = jsonObject.get("cakes").getAsJsonArray();

        for (JsonElement element : cakes) {
            JsonArray ingredients = element.getAsJsonObject().get("ingredients").getAsJsonArray();

            switch (type) {
                case COUNT_TOTAL_SUM:
                    total += element.getAsJsonObject().get("price").getAsDouble();
                    break;
                case MAKE_DAIRY_FREE:
                    JsonArray jsonArray = new JsonArray();
                    double multiplier = 1d;
                    for (JsonElement ingredient : ingredients) {
                        if (dairy.contains(ingredient.getAsString())) {
                            jsonArray.add(String.format("plant-%s", ingredient.getAsString()));
                            multiplier += 1d / 10d;
                        } else {
                            jsonArray.add(ingredient.getAsString());
                        }
                    }
                    element.getAsJsonObject().add("price", new JsonPrimitive(String.format("%.2f", element.getAsJsonObject().get("price").getAsDouble() * multiplier)));
                    element.getAsJsonObject().add("ingredients", jsonArray);
                    break;
                case REMOVE_BEST_BEFORE_DAY_OVER:
                    String[] date = element.getAsJsonObject().get("BBD").getAsString().split("-");
                    if (Integer.parseInt(date[0]) < year) {
                        cakes.remove(element);
                    } else if (Integer.parseInt(date[0]) == year) {
                        if (Integer.parseInt(date[1]) < 5) {
                            cakes.remove(element);
                        } else if (Integer.parseInt(date[1]) == 5) {
                            if (Integer.parseInt(date[2]) < 3) {
                                cakes.remove(element);
                            }
                        }
                    }
                    break;
            }
        }

        JsonObject answer = new JsonObject();
        answer.add("order_id", new JsonPrimitive(order));
        if (type.equals(CakeOrderProcessorType.COUNT_TOTAL_SUM)) {
            answer.add("total", new JsonPrimitive(String.format("%.2f", total)));
        }
        answer.add("cakes", jsonObject.get("cakes"));
        return answer.toString();
    }

    public static void main(String[] args) {
    }
}
