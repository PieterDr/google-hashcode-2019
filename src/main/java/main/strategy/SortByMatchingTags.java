package main.strategy;

import main.Photo;
import main.Slide;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class SortByMatchingTags implements Solution {

    @Override
    public List<Slide> execute(List<Photo> photos) {
        List<Slide> horizontals = getHorizontals(photos);
        List<Slide> verticals = getVerticals(photos);
        horizontals.addAll(verticals);
        return horizontals;
    }

    private static List<Slide> getVerticals(List<Photo> photos) {
        List<Photo> verticals = photos.stream()
                .filter(Photo::isVertical)
                .collect(toList());
        List<Photo> sorted = sortByTags(verticals);
        int i = 0;
        ArrayList<Slide> result = new ArrayList<>();
        while (i < sorted.size()) {
            result.add(new Slide(sorted.get(i), sorted.get(i + 1)));
            i += 2;
        }
        return result;
    }

    private static List<Slide> getHorizontals(List<Photo> photos) {
        List<Photo> horizontals = photos.stream().filter(Photo::isHorizontal).collect(toList());
        return sortByTags(horizontals).stream().map(Slide::new).collect(toList());
    }

    private static List<Photo> sortByTags(List<Photo> photos) {
        ArrayList<Photo> result = new ArrayList<>();
        while (photos.size() > 0) {
            Photo photo = photos.get(0);
            result.add(photo);
            photos.removeIf(p -> p.equals(photo));
            Photo highestMatch = getHighestMatch(photo, photos);
            result.add(highestMatch);
            photos.removeIf(p -> p.equals(highestMatch));
        }
        return result;
    }

    private static Photo getHighestMatch(Photo p, List<Photo> photos) {
        Photo highest = photos.get(0);
        int highestMatch = 0;
        for (Photo photo : photos) {
            int match = p.compareTags(photo);
            if (match > highestMatch) {
                highest = photo;
            }
        }
        return highest;
    }
}
