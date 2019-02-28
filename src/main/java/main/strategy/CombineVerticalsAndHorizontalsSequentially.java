package main.strategy;

import main.Photo;
import main.Slide;

import java.util.*;

import static java.util.stream.Collectors.toList;

@SuppressWarnings("Duplicates")
public class CombineVerticalsAndHorizontalsSequentially implements Solution {

    @Override
    public List<Slide> execute(List<Photo> photos) {
        List<Photo> verticals = getVerticals(photos);
        ArrayList<Slide> slides = new ArrayList<>();
        for (int i = 0; i < verticals.size() - 1; i++) {
            slides.add(new Slide(verticals.get(i), verticals.get(++i)));
        }
        slides.addAll(getHorizontals(photos));
        slides.sort(Comparator.comparingInt(slide -> ((Slide) slide).getTags().size()).reversed());

        Map<String, List<Slide>> tagMap = calculateTagMap(slides);

        List<Slide> result = new LinkedList<>();
        result.add(slides.remove(0));

        System.out.println(tagMap.size());
        int i = 0;
        while (!slides.isEmpty()) {
            Slide reference = result.get(result.size() - 1);
            int maxScore = 0;
            Slide best = slides.get(0);

            removeFromTagMap(tagMap, reference);
            List<Slide> matchingSlides = reference.getTags().stream().map(tagMap::get).flatMap(Collection::stream).collect(toList());

            for (int j = 0; j < matchingSlides.size(); j++) {
                Slide slide = matchingSlides.get(j);
                int score = reference.score(slide);
                if (score > maxScore) {
                    best = slide;
                }
            }

            result.add(best);
            slides.remove(best);

            i++;
            if (i % 1000 == 0)
                System.out.println(i);
        }
        return result;
    }

    private void removeFromTagMap(Map<String, List<Slide>> tagMap, Slide best) {
        for (String s : best.getTags()) {
            List<Slide> list = tagMap.get(s);
            list.remove(best);
        }
    }

    private static Map<String, List<Slide>> calculateTagMap(ArrayList<Slide> slides) {
        Map<String, List<Slide>> slidesByTag = new HashMap<>();
        for (Slide slide : slides) {
            for (String tag : slide.getTags()) {
                slidesByTag.computeIfAbsent(tag, t -> new ArrayList<>()).add(slide);
            }
        }
        return slidesByTag;
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
