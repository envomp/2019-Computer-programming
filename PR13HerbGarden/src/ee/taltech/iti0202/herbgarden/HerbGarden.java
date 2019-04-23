package ee.taltech.iti0202.herbgarden;
import ee.taltech.iti0202.herbgarden.gardener.Gardener;
import ee.taltech.iti0202.herbgarden.plantingstrategy.PlantInColumns;
import ee.taltech.iti0202.herbgarden.plantingstrategy.PlantInRows;
import ee.taltech.iti0202.herbgarden.plantingstrategy.PlantInTurns;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class HerbGarden {

    public static void main(String[] args) {
        PlantInTurns strategy1 = new PlantInTurns();
        PlantInRows strategy2 = new PlantInRows();
        PlantInColumns strategy3 = new PlantInColumns();

        Gardener gardener = new Gardener("Els", strategy1);

        Map<String, Integer> herbs = new HashMap<>();
        herbs.put("thyme", 7);
        herbs.put("parsley", 3);
        herbs.put("rosemary", 2);

        String[][] plantedHerbs = gardener.plantHerbs(3, 4, herbs);

        System.out.println(Arrays.stream(plantedHerbs).
                map(x -> String.join(",", x))
                .collect(Collectors.joining("\n"))
        );
    }
}