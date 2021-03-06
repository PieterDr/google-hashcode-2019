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

    public boolean isVertical() {
        return orientation == Orientation.VERTICAL;
    }

    public int compareTags(Photo p) {
        int result = 0;
        for (String tag : tags) {
            if (p.getTags().contains(tag)) {
                result++;
            }
        }
        return result;
    }

    public long tagMatchCount(Photo candidate) {
        return tags.stream().filter(tag -> candidate.tags.contains(tag)).count();
    }
}
