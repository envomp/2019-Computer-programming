package ee.taltech.iti0202.birdwatching.filter;

import ee.taltech.iti0202.birdwatching.bird.Bird;

import java.util.List;
import java.util.stream.Collectors;

public interface BirdFilter {
    List<Bird> getSuitableBirds(List<Bird> birds);
}

class GenericFilter implements BirdFilter {

    @Override
    public List<Bird> getSuitableBirds(List<Bird> birds) {
        return birds;
    }
}

class SpeciesFilter implements BirdFilter {

    private final String spieces;

    public SpeciesFilter(String spieces) {
        this.spieces = spieces;
    }

    @Override
    public List<Bird> getSuitableBirds(List<Bird> birds) {
        return birds.stream().filter(x -> x.getSpecies().equals(spieces)).collect(Collectors.toList());
    }
}

class WeightFilter implements BirdFilter {

    private final double weight;

    public WeightFilter(double weight) {
        this.weight = weight;
    }

    @Override
    public List<Bird> getSuitableBirds(List<Bird> birds) {
        return birds.stream().filter(x -> x.getWeight() == weight).collect(Collectors.toList());
    }
}


class WingspanFilter implements BirdFilter {

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


class SexFilter implements BirdFilter {

    private final Bird.Sex sex;

    public SexFilter(Bird.Sex sex) {
        this.sex = sex;
    }

    @Override
    public List<Bird> getSuitableBirds(List<Bird> birds) {
        return birds.stream().filter(x -> x.getSex().equals(sex)).collect(Collectors.toList());
    }
}


class AgeFilter implements BirdFilter {

    private final Bird.Age age;

    public AgeFilter(Bird.Age age) {
        this.age = age;
    }

    @Override
    public List<Bird> getSuitableBirds(List<Bird> birds) {
        return birds.stream().filter(x -> x.getAge().equals(age)).collect(Collectors.toList());
    }
}
