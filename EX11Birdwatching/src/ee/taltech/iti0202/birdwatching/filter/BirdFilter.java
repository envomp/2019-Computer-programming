package ee.taltech.iti0202.birdwatching.filter;

import ee.taltech.iti0202.birdwatching.bird.Bird;

import java.util.List;

public interface BirdFilter {
    List<Bird> getSuitableBirds(List<Bird> birds);
}
