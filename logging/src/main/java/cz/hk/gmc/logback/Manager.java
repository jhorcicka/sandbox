package cz.hk.gmc.logback;

public class Manager extends Person {
    Manager() {
        System.out.println("Manager()");
    }
    {
        System.out.println("Manager-static");
    }
    
    void method() {}
}
