package lsystem.engine;

public enum ElementProperties {

    DRAW('X'),
    NOTHING('Y'),
    ROTATION_X('x'),
    ROTATION_Y('y'),
    ROTATION_Z('z');

    private final char c;

    ElementProperties(char c) {
        this.c = c;
    }

    public char getChar() {
        return c;
    }
}
