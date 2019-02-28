package main;

import com.google.common.collect.Sets;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import static java.lang.Math.min;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toSet;

public class Slide {

    List<Photo> photos;

    public Slide(List<Photo> photos) {
        if (this.isLegitness(photos)) {
            this.photos = photos;
        } else {
            throw new RuntimeException("Slide not legit");
        }
    }

    public Slide(Photo... photos ) {
        this(asList(photos));
    }

    int score(Slide o) {
        Sets.SetView<String> intersection = Sets.intersection(getAllTags(), o.getAllTags());
        Set<String> thisTags = getAllTags();
        Set<String> otherTags = o.getAllTags();
        thisTags.removeAll(intersection);
        otherTags.removeAll(intersection);
        return min(intersection.size(), min(thisTags.size(), otherTags.size()));
    }

    public String getPrintString() {
        return photos.stream()
                .map(photo -> photo.id)
                .map(String::valueOf)
                .collect(joining(" "));
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    private Set<String> getAllTags() {
        return photos.stream()
                .map(photo -> photo.tags)
                .flatMap(Collection::stream)
                .collect(toSet());
    }

    // Max 1 Horizontal or 2 Vertical
    private boolean isLegitness(List<Photo> photos) {
        switch (photos.size()) {
            case 1:
                return photos.get(0).getOrientation() == Orientation.HORIZONTAL;
            case 2:
                return photos.stream().allMatch(x -> x.getOrientation() == Orientation.VERTICAL);
            default:
                return false;
        }
    }
}
