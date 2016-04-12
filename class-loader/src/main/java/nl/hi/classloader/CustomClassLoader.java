package nl.hi.classloader;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class CustomClassLoader {
    public Class loadClass(String name) {
        try {
            String path = System.getProperty("user.dir") +
                    "/class-loader/target/classes/nl/hi/classloader/" + name + ".class";
            URL[] urls = new URL[] { new URL("file://" + path) };
            ClassLoader classLoader = new URLClassLoader(urls);

            return classLoader.loadClass("nl.hi.classloader." + name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
