package main.strategy;

import main.Photo;
import main.Slide;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.util.stream.Collectors.toList;

public class RandomizingSolution implements Solution {

    private Random random = new Random();

    @Override
    public List<Slide> execute(List<Photo> photos) {
        List<Photo> verticals = extractVerticals(photos);
        List<Slide> slides = randomVerticalSlides(verticals);
        slides.addAll(extractHorizontals(photos));

        List<Slide> result = new ArrayList<>();
        while(!slides.isEmpty()) {
            result.add(slides.remove(random.nextInt(slides.size())));
        }
        return result;
    }

    private List<Slide> randomVerticalSlides(List<Photo> verticals) {
        if (verticals.isEmpty()) {
            return new ArrayList<>();
        }
        if (verticals.size() % 2 != 0) {
            verticals.remove(random.nextInt(verticals.size()));
        }
        List<Slide> slides = new ArrayList<>();
        while (!verticals.isEmpty()) {
            slides.add(new Slide(
                    verticals.remove(random.nextInt(verticals.size())),
                    verticals.remove(random.nextInt(verticals.size()))
            ));
        }
        return slides;
    }

    private List<Photo> extractVerticals(List<Photo> photos) {
        return photos.stream().filter(Photo::isVertical).collect(toList());
    }

    private List<Slide> extractHorizontals(List<Photo> photos) {
        return photos.stream().filter(Photo::isHorizontal).map(Slide::new).collect(toList());
    }
}
