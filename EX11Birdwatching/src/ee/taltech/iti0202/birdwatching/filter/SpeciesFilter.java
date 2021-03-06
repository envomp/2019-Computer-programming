package ee.taltech.iti0202.birdwatching.filter;

import ee.taltech.iti0202.birdwatching.bird.Bird;

import java.util.List;
import java.util.stream.Collectors;

public class SpeciesFilter implements BirdFilter {

    private final String spieces;

    public SpeciesFilter(String spieces) {
        this.spieces = spieces;
    }

    @Override
    public List<Bird> getSuitableBirds(List<Bird> birds) {
        return birds.stream().filter(x -> x.getSpecies().equals(spieces)).collect(Collectors.toList());
    }
}
