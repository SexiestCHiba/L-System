package lsystem.engine;

public enum ElementProperties {

    DRAW('X'),
    NOTHING('Y'),
    ROTATION_X('x', (byte) 0),
    ROTATION_Y('y', (byte) 1),
    ROTATION_Z('z', (byte) 2);


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
