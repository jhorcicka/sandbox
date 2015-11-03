package cz.hk.gmc.variation;

import java.util.HashMap;
import java.util.Map;

/**
 * @since 18. 09. 2015
 */
public class VariationWithRepetition {
    private int spots = 0;
    private String[] elements = null;
    private Map<Integer, Integer> spotIndexCounter = new HashMap<>();
    private boolean finished = false;

    public VariationWithRepetition(String[] elements, int spots) {
        this.elements = elements;
        this.spots = spots;

        for (int i = 0; i < spots; i++) {
            spotIndexCounter.put(i, 0);
        }
    }

    public String getNext() {
        if (finished) {
            return "";
        }

        String variation = "";
        boolean incremented = false;

        for (int i = 0; i < spots; i++) {
            int spotIndex = spotIndexCounter.get(i);
            variation += elements[spotIndex];

            if (! incremented) {
                if (spotIndex + 1 >= elements.length) {
                    spotIndex = 0;

                    if (i == spots - 1) {
                        finished = true;
                    }
                }
                else {
                    spotIndex++;
                    incremented = true;
                }

                spotIndexCounter.put(i, spotIndex);
            }
        }

        return variation;
    }

    public boolean hasNext() {
        return ! finished;
    }
}
