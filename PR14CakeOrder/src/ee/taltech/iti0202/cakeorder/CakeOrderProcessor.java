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
    private static final int Year = 2019;
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
        List<JsonElement> toRemove = new ArrayList<>();

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
                    element.getAsJsonObject().add("price", new JsonPrimitive(String.format("%.2f",
                            element.getAsJsonObject().get("price").getAsDouble() * multiplier)));
                    element.getAsJsonObject().add("ingredients", jsonArray);
                    break;
                case REMOVE_BEST_BEFORE_DAY_OVER:
                    String[] date = element.getAsJsonObject().get("BBD").getAsString().split("-");
                    if (Integer.parseInt(date[0]) < Year) {
                        toRemove.add(element);
                    } else if (Integer.parseInt(date[0]) == Year) {
                        if (Integer.parseInt(date[1]) < 4) {
                            toRemove.add(element);
                        } else if (Integer.parseInt(date[1]) == 4) {
                            if (Integer.parseInt(date[2]) < 30) {
                                toRemove.add(element);
                            }
                        }
                    }
                    break;
                default:
                    break;
            }
        }

        for (JsonElement rem : toRemove) {
            cakes.remove(rem);
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
        CakeOrderProcessor processor = new CakeOrderProcessor(CakeOrderProcessorType.REMOVE_BEST_BEFORE_DAY_OVER);
        String process = processor.process("{\n" +
                "  \"cakes\": [\n" +
                "    {\n" +
                "      \"name\": \"Sacher\",\n" +
                "      \"BBD\": \"2019-04-29\",\n" +
                "      \"price\": 14.00,\n" +
                "      \"kg\": 2.00,\n" +
                "      \"ingredients\": [\"flour\", \"chocolate\", \"milk\", \"sugar\", \"eggs\"]\n" +
                "    },\n" +
                "    {\n" +
                "      \"name\": \"New York Cheesecake\",\n" +
                "      \"BBD\": \"2019-04-30\",\n" +
                "      \"price\": 10.00,\n" +
                "      \"kg\": 1.50,\n" +
                "      \"ingredients\": [\"flour\", \"cream-cheese\", \"milk\", \"sugar\", \"eggs\"]\n" +
                "\n" +
                "    }\n" +
                "  ]\n" +
                "}");
        System.out.println(process);
    }
}
