package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Main {

    static final String INPUT_A = "a_example.txt";
    static final String INPUT_B = "b_lovely_landscapes.txt";
    static final String INPUT_C = "c_memorable_moments.txt";
    static final String INPUT_D = "d_pet_pictures.txt";
    static final String INPUT_E = "e_shiny_selfies.txt";

    public static void main(String[] args) {
        InputParser parser = new InputParser();
        Stream.of(INPUT_A, INPUT_B, INPUT_C, INPUT_D, INPUT_E)
                .forEach(input -> {
                    try {
                        List<Photo> photos = parser.parse(input);
                        List<Slide> slides = run(photos);
                        write(input, slides);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    private static List<Slide> run(List<Photo> photos) {
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

    private static void write(String inputFile, List<Slide> slides) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile.replace("txt", "out")));
        writer.write(slides.size() + "\n");
        for (Slide slide : slides) {
            writer.write(slide.getPrintString() + "\n");
        }
        writer.close();
    }
}
