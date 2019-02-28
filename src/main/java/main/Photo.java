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

<<<<<<< Updated upstream
    public Orientation getOrientation() {
        return orientation;
    }

    public Set<String> getTags() {
        return tags;
    }

=======
>>>>>>> Stashed changes
    @Override
    public String toString() {
        return "Photo{" +
                "orientation=" + orientation +
<<<<<<< Updated upstream
                ", tags=" + tags +
                '}';
    }
=======
                ", id='" + id + '\'' +
                ", tags=" + tags +
                '}';
    }

>>>>>>> Stashed changes
}
