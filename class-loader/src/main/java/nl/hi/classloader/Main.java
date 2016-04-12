package nl.hi.classloader;

public class Main {
    public static void main(String[] args) {
        try {
            CustomClassLoader customClassLoader = new CustomClassLoader();

            for (int i = 1; i < 4; i++) {
                Object instance;
                Class clazz;
                clazz = customClassLoader.loadClass("MyLoaded" + i);
                instance = clazz.newInstance();
                ((Loaded) instance).doSomething();
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
