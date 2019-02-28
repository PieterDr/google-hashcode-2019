package main;

import java.util.Set;

public class Photo {

    Orientation orientation;
    Set<String> tags;

    public Photo(Orientation orientation, Set<String> tags) {
        this.orientation = orientation;
        this.tags = tags;
    }

}
