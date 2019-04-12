package ee.taltech.iti0202.birdwatching.filter;

import ee.taltech.iti0202.birdwatching.bird.Bird;

import java.util.List;
import java.util.stream.Collectors;

public class SexFilter implements BirdFilter {

    private final Bird.Sex sex;

    public SexFilter(Bird.Sex sex) {
        this.sex = sex;
    }

    @Override
    public List<Bird> getSuitableBirds(List<Bird> birds) {
        return birds.stream().filter(x -> x.getSex().equals(sex)).collect(Collectors.toList());
    }
}
