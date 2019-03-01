package main;

import main.strategy.DivideAndConquer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class Main {

    static final String INPUT_A = "a_example.txt";
    static final String INPUT_B = "b_lovely_landscapes.txt";
    static final String INPUT_C = "c_memorable_moments.txt";
    static final String INPUT_D = "d_pet_pictures.txt";
    static final String INPUT_E = "e_shiny_selfies.txt";

    public static void main(String[] args) {
        InputParser parser = new InputParser();
        Arrays.stream(new String[]{
                        INPUT_C,
                        INPUT_D,
                        INPUT_E,
                })
                .forEach(input -> {
                    System.out.println(input);
                    try {
                        List<Photo> photos = parser.parse(input);
                        List<Slide> slides = run(photos);
                        System.out.println("SCORE: " + Scorer.score(slides));
                        write(input, slides);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println();
                });
    }

    private static List<Slide> run(List<Photo> photos) {
        return new DivideAndConquer().execute(photos);
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
