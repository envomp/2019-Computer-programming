package ee.taltech.iti0202.herbgarden.plantingstrategy;

import java.util.ArrayList;
import java.util.Map;

public class PlantInRows implements PlantingStrategy {
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
        new ArrayList<>(plants.keySet()).forEach(this::plantHerb);
        return plantedHerbs;
    }

    public void plantHerb(String herb) {
        if (plants.get(herb) == 0) return;
        plants.put(herb, plants.get(herb) - 1);
        plantedHerbs[iteration / width][iteration % (height + 1)] = herb;
        iteration++;
        plantHerb(herb);
    }
}
