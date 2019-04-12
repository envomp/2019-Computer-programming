package ee.taltech.iti0202.birdwatching.filter;

import ee.taltech.iti0202.birdwatching.bird.Bird;

import java.util.List;
import java.util.stream.Collectors;

public class WeightFilter implements BirdFilter {

    private final double minimum;
    private final double maximum;

    public WeightFilter(double minimum, double maximum) {
        this.maximum = maximum;
        this.minimum = minimum;
    }

    @Override
    public List<Bird> getSuitableBirds(List<Bird> birds) {
        return birds.stream()
                .filter(x -> x.getWeight() >= minimum)
                .filter(y -> y.getWeight() <= maximum)
                .collect(Collectors.toList());
    }
}
