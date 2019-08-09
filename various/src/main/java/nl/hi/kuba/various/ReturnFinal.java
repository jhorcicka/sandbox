package nl.hi.kuba.various;

public class ReturnFinal {

    private static final String getFinal() {
        final String f = "final-value";

        return f;
    }

    public static void main(String[] args) {
        String f = getFinal();
        System.out.println("1: " + f);
        f = "hi";
        System.out.println("2: " + f);
    }
}
