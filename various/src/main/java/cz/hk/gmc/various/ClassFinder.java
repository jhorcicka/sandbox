package cz.hk.gmc.various;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ClassFinder {
    public static void main(String[] args) {
        try {
            for (Class clazz : getClasses("cz.hk.gmc.various")) {
                System.out.println(clazz.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Class[] getClasses(String packageName) throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace('.', '/');
        Enumeration resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList();

        while (resources.hasMoreElements()) {
            URL resource = (URL) resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }

        ArrayList classes = new ArrayList();

        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }

        return (Class[]) classes.toArray(new Class[classes.size()]);
    }

    private static List findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class> classes = new ArrayList();

        if (!directory.exists()) {
            return classes;
        }

        File[] files = directory.listFiles();

        for (File file : files) {
            if (file.isDirectory()) {
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                String className = packageName + '.' + file.getName().substring(0, file.getName().length() - 6);

                if (className.indexOf("$") < 0) {
                    Class clazz = Class.forName(className);
                    classes.add(clazz);
                }
            }
        }

        return classes;
    }
}
