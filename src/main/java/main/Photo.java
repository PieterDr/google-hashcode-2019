package main;

import java.util.Set;

public class Photo {

    Orientation orientation;
    String id;
    Set<String> tags;

    public Photo(Orientation orientation, String id, Set<String> tags) {
        this.orientation = orientation;
        this.id = id;
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
                ", id='" + id + '\'' +
                ", tags=" + tags +
                '}';
    }
}
