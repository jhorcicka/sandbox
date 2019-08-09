package nl.hi.kuba.logback;

public class Manager extends Person {
    {
        System.out.println("Manager-static");
    }

    Manager() {
        System.out.println("Manager()");
    }

    void method() {
    }
}
