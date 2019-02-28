package main.strategy;

import main.Photo;
import main.Slide;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class CombineVerticalsAndHorizontalAndOrder implements Solution {

    @Override
    public List<Slide> execute(List<Photo> photos) {
        List<Photo> verticals = getVerticals(photos);
        ArrayList<Slide> slides = new ArrayList<>();
        for (int i = 0; i < verticals.size(); i++) {
            if (i != verticals.size() - 1) {
                slides.add(new Slide(verticals.get(i), verticals.get(++i)));
            }
        }
        slides.addAll(getHorizontals(photos));

        return orderSlides(slides);
    }

    private static List<Photo> getVerticals(List<Photo> photos) {
        return photos.stream()
                .filter(Photo::isVertical)
                .collect(toList());
    }

    private static List<Slide> getHorizontals(List<Photo> photos) {
        return photos.stream().filter(Photo::isHorizontal).map(Slide::new).collect(toList());
    }

    private static List<Slide> orderSlides(ArrayList<Slide> slides) {
        return slides.stream().sorted((o1, o2) -> {
            return
        }).collect(Collectors.toList());
    }
}
