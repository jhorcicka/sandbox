package nl.hi.kuba.sandbox.jtree;

import java.util.Vector;

/**
 * @since 27. 11. 2015
 */
public class TreeNodeVector<E> extends Vector<E> {
    String name;

    TreeNodeVector(String name) {
        this.name = name;
    }

    TreeNodeVector(String name, E[] elements) {
        this.name = name;

        for (int i = 0, n = elements.length; i < n; i++) {
            add(elements[i]);
        }
    }

    public String toString() {
        return "[" + name + "]";
    }
}
