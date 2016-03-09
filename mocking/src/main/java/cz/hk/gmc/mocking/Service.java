package cz.hk.gmc.mocking;

public class Service {

    public void voidMethod() {
        System.out.println("real voidMethod was called...");
    }

    public final String finalMethod() {
        System.out.println("real finalMethod was called...");
        return "1000";
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

    public static String staticMethod() {
        return "42";
    }
}
