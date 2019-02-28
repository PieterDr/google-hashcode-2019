package main;

import com.google.common.collect.Sets;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import static java.lang.Math.min;
import static java.util.stream.Collectors.toSet;

public class Slide {

    List<Photo> photos;

    public Slide(List<Photo> photos) {
        this.photos = photos;
    }

    int score(Slide o) {
        Sets.SetView<String> intersection = Sets.intersection(getAllTags(), o.getAllTags());
        Set<String> thisTags = getAllTags();
        Set<String> otherTags = o.getAllTags();
        thisTags.removeAll(intersection);
        otherTags.removeAll(intersection);
        return min(intersection.size(), min(thisTags.size(), otherTags.size()));
    }

    private Set<String> getAllTags() {
        return photos.stream()
                .map(photo -> photo.tags)
                .flatMap(Collection::stream)
                .collect(toSet());
    }
}
