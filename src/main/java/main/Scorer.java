package main;

import java.util.List;

public class Scorer {

    public static long score(List<Slide> slides) {
        long score = 0;
        for (int i = 0; i < slides.size() - 1; i++) {
            score += slides.get(i).score(slides.get(i + 1));
        }
        return score;
    }
}
