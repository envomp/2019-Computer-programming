package ee.taltech.iti0202.birdwatching.filter;

import ee.taltech.iti0202.birdwatching.bird.Bird;

import java.util.List;
import java.util.stream.Collectors;

public class WeightFilter implements BirdFilter {

    private final double weight;

    public WeightFilter(double weight) {
        this.weight = weight;
    }

    @Override
    public List<Bird> getSuitableBirds(List<Bird> birds) {
        return birds.stream().filter(x -> x.getWeight() == weight).collect(Collectors.toList());
    }
}
