package lsystem.engine;

import java.util.LinkedList;

public class Element {

    public final ElementProperties properties;
    public final LinkedList<Element> children = new LinkedList<>();
    public final Element parent;
    public final float value;

    public Element(ElementProperties properties, Element parent) {
        this(properties, 0, parent);
    }

    public Element(ElementProperties properties, float value, Element parent) {
        this.properties = properties;
        this.parent = parent;
        this.value = value;
    }

}
