package nl.hi.kuba.patterns.builder;

public class ComplexClass {
    private int intValue;
    private String stringValue;
    private double doubleValue;
    private char charValue;

    public ComplexClass(final int intValue, final String stringValue, final double doubleValue, final char charValue) {
        this.intValue = intValue;
        this.stringValue = stringValue;
        this.doubleValue = doubleValue;
        this.charValue = charValue;
    }

    @Override
    public String toString() {
        return "ComplexClass{" +
                "intValue=" + intValue +
                ", stringValue='" + stringValue + '\'' +
                ", doubleValue=" + doubleValue +
                ", charValue=" + charValue +
                '}';
    }
}
