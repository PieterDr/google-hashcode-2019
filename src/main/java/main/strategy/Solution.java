package main.strategy;

import main.Photo;
import main.Slide;

import java.util.List;

public interface Solution {

    List<Slide> execute(List<Photo> photos);
}
