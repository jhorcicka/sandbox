package nl.hi.kuba.mocking;

public class Service extends Base {

    public static String staticMethod() {
        System.out.println("real staticMethod was called...");
        return "42";
    }

    public void voidMethod() {
        System.out.println("real voidMethod was called...");
    }

    public final String finalMethod() {
        System.out.println("real finalMethod was called...");
        return "1000";
    }

    public final String finalMethodWithArguments(int value) {
        System.out.println("real finalMethodWithArguments was called...");
        return "x=" + value;
    }

    public String publicMethod() {
        System.out.println("real publicMethod was called...");
        return "10";
        //return privateMethod() + 10;
    }

    public String StringerfaceMethod() {
        System.out.println("real StringerfaceMethod was called...");
        return finalMethod() + 1;
        //return publicMethod() + 1;
    }

    private String privateMethod() {
        System.out.println("real privateMethod was called...");
        return "100";
    }
}
