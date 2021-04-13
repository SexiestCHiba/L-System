package lsystem.engine;

/**
 * @see Element
 * @see Parser#parse(String)
 */
public enum ElementProperties {
    /**
     * used to draw a 1 unit high cylinder
     */
    DRAW('X'),
    /**
     * used to control the evolution
     */
    NOTHING('Y'),
    ROTATION_X('x', (byte) 0),
    ROTATION_Y('y', (byte) 1);

    // char is a java reserved word, we replace "a" by a "4" to avoid this problem
    private final char ch4r;
    private final byte direction;

    ElementProperties(char x) {
        this(x, (byte) -1);
    }

    ElementProperties(char x, byte direction) {
        this.ch4r = x;
        this.direction = direction;
    }

    public char getChar() {
        return ch4r;
    }

    public byte getDirection() {
        return direction;
    }
}
