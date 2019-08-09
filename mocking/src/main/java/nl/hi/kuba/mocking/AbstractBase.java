package nl.hi.kuba.mocking;

public class AbstractBase {
    public final String publicFinalMethodInAbstract() {
        System.out.println("Real publicFinalMethod2 was called...");

        return "public-final";
    }
}
