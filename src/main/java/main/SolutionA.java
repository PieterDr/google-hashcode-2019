package main;

import java.util.List;

public class SolutionA {

    public static void main(String[] args) {
        List<Photo> photos = InputReader.read("a_example.txt");
        photos.forEach(System.out::println);
    }
}
