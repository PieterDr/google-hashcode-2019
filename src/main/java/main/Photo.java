package main;

import java.util.Set;

public class Photo {

    Orientation orientation;
    Set<String> tags;

    public Photo(Orientation orientation, Set<String> tags) {
        this.orientation = orientation;
        this.tags = tags;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public Set<String> getTags() {
        return tags;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "orientation=" + orientation +
                ", tags=" + tags +
                '}';
    }
}
