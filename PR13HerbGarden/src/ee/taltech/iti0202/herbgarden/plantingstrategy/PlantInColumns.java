package ee.taltech.iti0202.herbgarden.plantingstrategy;

import java.util.ArrayList;
import java.util.Map;

public class PlantInColumns implements PlantingStrategy {
    int iteration;
    Map<String, Integer> plants;
    String[][] plantedHerbs;
    int height;
    int width;

    @Override
    public String[][] plantHerbs(int height, int width, Map<String, Integer> plants) {
        this.iteration = 0;
        this.plants = plants;
        this.height = height;
        this.width = width;
        this.plantedHerbs = new String[height][width];
        plants.entrySet().stream()
                .sorted((k1, k2) -> -k1.getValue().compareTo(k2.getValue()))
                .forEach(y -> plantHerb(y.getKey()));
        return plantedHerbs;
    }

    public void plantHerb(String herb) {
        if (plants.get(herb) == 0) return;
        plants.put(herb, plants.get(herb) - 1);
        plantedHerbs[iteration % height][iteration / height] = herb;
        iteration++;
        plantHerb(herb);
    }
}
