package lsystem.engine;

import java.util.LinkedList;

public class Element {

    public final ElementProperties property;
    public final Element parent;
    public final float[] values;
    public final LinkedList<Element> children = new LinkedList<>();

    public Element(ElementProperties property, Element parent) {
        this(property, parent, new float[]{0f, 0f, 0f});
    }

    public Element(ElementProperties property, Element parent, float[] values) {
        this.property = property;
        this.parent = parent;
        this.values = values;
    }

}
