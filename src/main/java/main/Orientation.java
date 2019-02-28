package main;

public enum Orientation {

    HORIZONTAL, VERTICAL;

    public static Orientation from(String part) {
        if (part.equalsIgnoreCase("V")) {
            return Orientation.VERTICAL;
        } else {
            return Orientation.HORIZONTAL;
        }
    }
}
