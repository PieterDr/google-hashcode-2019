import java.util.List;

public class Photo {

    private String orientation;
    private List<String> tags;

    public Photo(String orientation, List<String> tags) {
        this.orientation = orientation;
        this.tags = tags;
    }

    public String getOrientation() {
        return orientation;
    }

    public List<String> getTags() {
        return tags;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "orientation='" + orientation + '\'' +
                ", tags=" + tags +
                '}';
    }
}
