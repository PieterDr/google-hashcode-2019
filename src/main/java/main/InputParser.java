package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

public class InputParser {

    public List<Photo> parse(String filename) {
        AtomicInteger id = new AtomicInteger();
        return new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(filename))).lines()
                .skip(1)
                .map(line -> {
                    Orientation orientation = getOrientation(line);
                    String[] split = line.split(" ");

                    Set<String> tags = Arrays.stream(split)
                            .skip(2)
                            .collect(toSet());
                    return new Photo(orientation, id.getAndIncrement(), tags);
                })
                .collect(Collectors.toList());
    }

    private Orientation getOrientation(String line) {
        switch (line.charAt(0)) {
            case 'H':
                return Orientation.HORIZONTAL;
            case 'V':
                return Orientation.VERTICAL;
        }
        throw new IllegalArgumentException(line);
    }

    public static void main(String[] args) {
        System.out.println("new InputParser().parse() = " + new InputParser().parse("a_example.txt"));
    }

}
