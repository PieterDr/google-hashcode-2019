package main.strategy;

import com.google.common.collect.Sets;
import main.Photo;
import main.Slide;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class LookUpTable implements Solution {

    @Override
    public List<Slide> execute(List<Photo> photos) {
        List<Slide> output = new ArrayList<>();
        ArrayList<Slide> slides = new ArrayList<>(generateSlides(photos));
        Map<String, Set<Slide>> tagMap = calculateTagMap(slides);

        slides.sort(Comparator.comparingInt(slide -> slide.getTags().size()));
        Slide slide = slides.get(slides.size() - 1);
        while (!slides.isEmpty()) {
            if (slides.size() % 1_000 == 0)
                System.out.println("slides.size() = " + slides.size());
            slides.remove(slide);
            for (String s : slide.getTags()) {
                tagMap.get(s).remove(slide);
            }
            output.add(slide);

            List<String> tags = new ArrayList<>(slide.getTags());
            String tag = tags.get(0);
            Set<Slide> overlappingSlides = new HashSet<>(tagMap.get(tag));
            if (!overlappingSlides.isEmpty()) {
                int halfCount = slide.getTags().size() / 2;
                for (int i = 1; i < halfCount; i++) {
                    overlappingSlides = Sets.union(overlappingSlides, tagMap.get(tags.get(i)));
                }
                Optional<Slide> optionalSlide = overlappingSlides.stream()
                        .filter(os -> Sets.intersection(new HashSet<>(tags), os.getTags()).size() <= halfCount)
                        .findFirst();
                if (optionalSlide.isPresent()) {
                    slide = optionalSlide.get();
                } else {
                    slide = overlappingSlides.iterator().next();
                }

            } else {
                if (!slides.isEmpty()) {
                    slide = slides.get(slides.size() - 1);
                }
            }
        }

        return output;
    }

    private static Map<String, Set<Slide>> calculateTagMap(ArrayList<Slide> slides) {
        Map<String, Set<Slide>> slidesByTag = new HashMap<>();
        for (Slide slide : slides) {
            for (String tag : slide.getTags()) {
                slidesByTag.computeIfAbsent(tag, t -> new HashSet<>()).add(slide);
            }
        }
        return slidesByTag;
    }

    private static ArrayList<Slide> generateSlides(List<Photo> photos) {
        List<Photo> verticals = getVerticals(photos);
        verticals.sort(Comparator.comparingInt(photo -> ((Photo) photo).getTags().size()).reversed());
        ArrayList<Slide> slides = new ArrayList<>();

        outer:
        for (int i = 0; i < verticals.size(); i++) {
            Photo photo = verticals.get(i);
            Set<String> currentTags = photo.getTags();
            for (int j = i + 1; j < verticals.size(); j++) {
                Photo otherPhoto = verticals.get(j);
                if (Collections.disjoint(otherPhoto.getTags(), currentTags)) {
                    slides.add(new Slide(photo, otherPhoto));
                    verticals.remove(otherPhoto);
                    continue outer;
                }
            }
        }
        slides.addAll(getHorizontals(photos));
        return slides;
    }

    private static List<Photo> getVerticals(List<Photo> photos) {
        List<Photo> verticals = photos.stream()
                .filter(Photo::isVertical)
                .collect(toList());

        return verticals;
    }

    private static List<Slide> getHorizontals(List<Photo> photos) {
        return photos.stream().filter(Photo::isHorizontal).map(Slide::new).collect(toList());
    }

}
