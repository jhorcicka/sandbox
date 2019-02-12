package cz.hk.gmc.logback;

public class Person implements IFirst, ISecond {
    Person() {
        System.out.println("Person()");
    }
    static {
        System.out.println("Person-static");
    }
    
    public void methodA() {
        System.err.println("Person-methodA");
    }
}
