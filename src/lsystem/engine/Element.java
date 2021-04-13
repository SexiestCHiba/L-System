package lsystem.engine;

import java.util.ArrayList;

public class Element {

    public final ElementProperties property;
    public final Element parent;
    public final float[] rotation;
    public final ArrayList<Element> children = new ArrayList<>();

    public Element(ElementProperties property, Element parent) {
        this(property, parent, new float[]{0f, 0f, 0f});
    }

    public Element(ElementProperties property, Element parent, float[] rotation) {
        this.property = property;
        this.parent = parent;
        this.rotation = rotation;
    }

}
