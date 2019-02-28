package main.strategy;

import main.Photo;
import main.Slide;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static main.strategy.SortByMatchingTags.*;

public class SortVerticalPhotosThenMakeSlidesThenSortSlides implements Solution {

    @Override
    public List<Slide> execute(List<Photo> photos) {
        List<Slide> horizontals = getHorizontals(photos);
        List<Slide> verticals = getVerticals(photos);
        List<Slide> slides = Stream.concat(horizontals.stream(), verticals.stream()).collect(Collectors.toList());
        ArrayList<Slide> result = new ArrayList<>();
        result.add(slides.get(0));
        result.add(slides.get(1));
        slides.remove(0);
        slides.remove(1);
        for (int i = 0; i < slides.size(); i++) {
            Slide slide = slides.get(i);
            int bestMatchingSpot = findBestMatchingSpot(slide, result);
            result.add(bestMatchingSpot, slide);
            slides.remove(i);
        }
        return result;
    }

    private int findBestMatchingSpot(Slide s, List<Slide> slides) {
        int highest = 0;
        int position = 0;
        for (int i = 0; i < slides.size(); i++) {
            int leftMatch = 0;
            int rightMatch = 0;
            if (i > 0) {
                leftMatch = s.compareTags(slides.get(i - 1));
            }
            if (i <= slides.size() + 1) {
                rightMatch = s.compareTags(slides.get(i));
            }
            if (leftMatch + rightMatch > highest) {
                highest = leftMatch + rightMatch;
                position = i;
            }
        }
        return position;
    }

}
