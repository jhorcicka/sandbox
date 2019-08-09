package cz.hk.gmc;

public class SubSub extends Sub {
    public static void main(String[] args) {
        Integer x = 8; 
        System.err.println("MYTODO: " + (x.SIZE));
        System.err.println("MYTODO: " + (x.BYTES));
        System.err.println("MYTODO: " + (x.SIZE + x.BYTES));
    }
    public void method() {
        System.err.println("SubSubClass");
    }
}
