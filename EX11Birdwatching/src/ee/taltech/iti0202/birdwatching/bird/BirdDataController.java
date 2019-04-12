package ee.taltech.iti0202.birdwatching.bird;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BirdDataController {
    private List<Bird> birds;

    public void readBirdDataFromCsvFile(String filename) throws BirdDataException {
        birds = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(filename))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] var = line.split(",");
                birds.add(new BirdBuilder()
                        .setSpecies(var[0])
                        .setWeight(Double.parseDouble(var[1]))
                        .setWingspan(Double.parseDouble(var[2]))
                        .setSex(Bird.Sex.valueOf(var[3].toUpperCase()))
                        .setAge(Bird.Age.valueOf(var[4].toUpperCase()))
                        .build());
            }

        } catch (IOException e) {
            throw new BirdDataException(e);
        }
    }

    public List<Bird> getBirds() {
        return birds;
    }
}
