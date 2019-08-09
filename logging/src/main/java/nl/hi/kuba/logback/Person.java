package nl.hi.kuba.logback;

public class Person implements IFirst, ISecond {
    static {
        System.out.println("Person-static");
    }

    Person() {
        System.out.println("Person()");
    }

    public void methodA() {
        System.err.println("Person-methodA");
    }
}
