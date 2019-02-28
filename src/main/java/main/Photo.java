package main;

import java.util.Set;

public class Photo {

    Orientation orientation;
    int id;
    Set<String> tags;

    public Photo(Orientation orientation, int id, Set<String> tags) {
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

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "orientation=" + orientation +
                ", id='" + id + '\'' +
                ", tags=" + tags +
                '}';
    }

    public boolean isHorizontal() {
        return orientation == Orientation.HORIZONTAL;
    }
}
