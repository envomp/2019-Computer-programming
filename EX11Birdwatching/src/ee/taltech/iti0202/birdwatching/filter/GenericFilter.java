package ee.taltech.iti0202.birdwatching.filter;

import ee.taltech.iti0202.birdwatching.bird.Bird;

import java.util.List;

public class GenericFilter implements BirdFilter {

    @Override
    public List<Bird> getSuitableBirds(List<Bird> birds) {
        return birds;
    }
}
