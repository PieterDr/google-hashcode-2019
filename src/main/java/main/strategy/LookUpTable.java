package main.strategy;

import main.Photo;
import main.Slide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class LookUpTable implements Solution {

    @Override
    public List<Slide> execute(List<Photo> photos) {
        ArrayList<Slide> slides = generateSlides(photos);
        Map<String, List<Slide>> tagMap = calculateTagMap(slides);
        System.out.println("tagMap = " + tagMap.size());
        return slides;
    }

    private static Map<String, List<Slide>> calculateTagMap(ArrayList<Slide> slides) {
        Map<String, List<Slide>> slidesByTag = new HashMap<>();
        for (Slide slide : slides) {
            for(String tag: slide.getTags()) {
                slidesByTag.computeIfAbsent(tag, t -> new ArrayList<>()).add(slide);
            }
        }
        return slidesByTag;
    }

    private static ArrayList<Slide> generateSlides(List<Photo> photos) {
        List<Photo> verticals = getVerticals(photos);
        ArrayList<Slide> slides = new ArrayList<>();
        for (int i = 0; i < verticals.size(); i++) {
            if (i != verticals.size() - 1) {
                slides.add(new Slide(verticals.get(i), verticals.get(++i)));
            }
        }
        slides.addAll(getHorizontals(photos));
        return slides;
    }

    private static List<Photo> getVerticals(List<Photo> photos) {
        return photos.stream()
                .filter(Photo::isVertical)
                .collect(toList());
    }

    private static List<Slide> getHorizontals(List<Photo> photos) {
        return photos.stream().filter(Photo::isHorizontal).map(Slide::new).collect(toList());
    }
}
