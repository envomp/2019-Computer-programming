package ee.taltech.iti0202.birdwatching.filter;

import ee.taltech.iti0202.birdwatching.bird.Bird;

import java.util.List;
import java.util.stream.Collectors;

public class WingspanFilter implements BirdFilter {

    private final double minimum;
    private final double maximum;

    public WingspanFilter(double minimum, double maximum) {
        this.maximum = maximum;
        this.minimum = minimum;
    }

    @Override
    public List<Bird> getSuitableBirds(List<Bird> birds) {
        return birds.stream()
                .filter(x -> x.getWingspan() >= minimum)
                .filter(y -> y.getWingspan() <= maximum)
                .collect(Collectors.toList());
    }
}
