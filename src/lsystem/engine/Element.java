package lsystem.engine;

import java.util.ArrayList;

/**
 * Object created by {@link Parser#parse(String)}, represent a tree with {@link Element#children branches}
 * @see ElementProperties
 * @see Parser#parse(String)
 */
public class Element {

    /**
     * stored value can be {@link ElementProperties#NOTHING} or {@link ElementProperties#DRAW}<br>
     * {@linkplain ElementProperties#NOTHING NOTHING} is used to control the evolution<br>
     * and {@linkplain ElementProperties#DRAW DRAW} is used to draw a 1 unit high cylinder
     */
    public final ElementProperties property;
    public final Element parent;
    /**
     * Rotation applied to this element, 3-dim tab which represent yaw, pitch and roll rotation
     */
    public final float[] rotation;
    /**
     * Branches of the tree
     */
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
