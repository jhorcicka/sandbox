package nl.hi.kuba.ocajp;

public class SubSub extends Sub {
    public void method() {
        System.err.println("SubSubClass");
    }

    public static void main(String[] args) {
        Integer x = 8;
        System.err.println("MYTODO: " + (Integer.SIZE));
        System.err.println("MYTODO: " + (Integer.BYTES));
        System.err.println("MYTODO: " + (Integer.SIZE + Integer.BYTES));
    }
}
