package lsystem.engine;

import java.util.LinkedList;

public class Element {

    public final ElementProperties property;
    public final Element parent;
    public final float[] rotation;
    public final LinkedList<Element> children = new LinkedList<>();

    public Element(ElementProperties property, Element parent) {
        this(property, parent, new float[]{0f, 0f, 0f});
    }

    public Element(ElementProperties property, Element parent, float[] rotation) {
        this.property = property;
        this.parent = parent;
        this.rotation = rotation;
    }

}
