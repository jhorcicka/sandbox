package nl.hi.kuba.variation;

/**
 * @since 18. 09. 2015
 */
public class Main {
    public static void main(String[] args) {
        int spots = 20;
        String[] elements = {"A", "B", "C",};

        VariationWithRepetition variationWithRepetition = new VariationWithRepetition(elements, spots);
        long i = 0;

        while (variationWithRepetition.hasNext()) {
            i++;
            //System.out.println(variationWithRepetition.getNext());
        }

        System.out.println("i = " + i);
    }
}
