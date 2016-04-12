package nl.hi.classloader;

public class MyLoaded1 implements  Loaded {
    @Override
    public void doSomething() {
        System.out.println("This is MyLoaded1.doSomething()...");
    }
}
