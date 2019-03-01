package main.strategy;

import main.Photo;
import main.Slide;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class DivideAndConquer implements Solution {

    private static final Integer CHUNK_SIZE = 5000;

    @Override
    public List<Slide> execute(List<Photo> photos) {
        List<Slide> slides = new ArrayList<>();
        List<List<Slide>> chunks = splitInChunks(photos).parallelStream().map(this::generateSlides).collect(toList());
        System.out.println("chunks = " + chunks.size());
        IntStream.range(0, chunks.size())
                .parallel()
                .mapToObj(i -> {
                    List<Slide> chunk = chunks.get(i);
                    return orderChunk(chunk, i);
                })
                .forEach(slides::addAll);
        return slides;
    }

    private List<Slide> orderChunk(List<Slide> slides, int chunkIndex) {
        System.out.println("chunkIndex = " + chunkIndex);
        List<Slide> ordered = new ArrayList<>();
        Slide slide = slides.get(0);
        while (!slides.isEmpty()) {
            slides.remove(slide);
            Slide next = null;
            int bestScore = -1;
            for (Slide candidate : slides) {
                int score = slide.score(candidate);
                if (bestScore < score) {
                    next = candidate;
                    bestScore = score;
                }
            }
            ordered.add(next);
            slides.remove(next);
            slide = next;
        }
        return ordered;
    }

    private List<List<Photo>> splitInChunks(List<Photo> photos) {
        int i = 0;
        List<List<Photo>> chunks = new ArrayList<>();
        while (i * CHUNK_SIZE < photos.size()) {
            if (i == photos.size()) {
                break;
            }
            chunks.add(photos.subList(i * CHUNK_SIZE, Integer.min(photos.size(), ++i * CHUNK_SIZE)));
        }
        return chunks;
    }

    private List<Slide> generateSlides(List<Photo> photos) {
        List<Slide> slides = new ArrayList<>();
        List<Photo> verticals = photos.stream().filter(Photo::isVertical).collect(toList());
        for (int i = 0; i < verticals.size() - 1; i++) {
            slides.add(new Slide(verticals.get(i), verticals.get(++i)));
        }
        slides.addAll(photos.stream().filter(Photo::isHorizontal).map(Slide::new).collect(toList()));
        return slides;
    }
}
