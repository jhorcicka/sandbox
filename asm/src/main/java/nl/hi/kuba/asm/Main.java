package nl.hi.kuba.asm;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Map;

import org.objectweb.asm.ClassReader;

/**
 * @since 29. 09. 2015
 */
public class Main {
    public static String[] getParameterNames(Method m) throws IOException {
        Class<?> declaringClass = m.getDeclaringClass();
        String resourceName = "/" + declaringClass.getName().replace('.', '/') + ".class";
        InputStream classData = declaringClass.getResourceAsStream(resourceName);

        VariableReader variableDiscoverer = new VariableReader();

        ClassReader r = new ClassReader(classData);
        r.accept(variableDiscoverer, false);

        Map<Integer, String> variableNames = variableDiscoverer.getVariableNames(m);

        if (variableNames == null) {
            return new String[0];
        }

        String[] parameterNames = new String[variableNames.size()];

        for (int i = 0; i < parameterNames.length; i++) {
            parameterNames[i] = variableNames.get(i);
        }

        return parameterNames;
    }

    public static void main(String[] args) {
        try {
            Class personClass = Person.class;

            for (Method method : personClass.getMethods()) {
                String[] names = getParameterNames(method);

                if (names.length > 0) {
                    for (String name : names) {
                        System.out.println(method.getName() + ": " + name);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
    public static List<String> getParameterNames(Constructor<?> constructor) throws IOException {
        Class<?> declaringClass = constructor.getDeclaringClass();
        ClassLoader declaringClassLoader = declaringClass.getClassLoader();

        Type declaringType = Type.getType(declaringClass);
        String constructorDescriptor = Type.getConstructorDescriptor(constructor);
        constructorDescriptor = Type.getMethodDescriptor(constructor.getClass());
        String url = declaringType.getInternalName() + ".class";

        InputStream classFileInputStream = declaringClassLoader.getResourceAsStream(url);
        if (classFileInputStream == null) {
            throw new IllegalArgumentException("The constructor's class loader cannot find the bytecode that defined 
            the constructor's class (URL: " + url + ")");
        }

        ClassNode classNode;
        try {
            classNode = new ClassNode();
            ClassReader classReader = new ClassReader(classFileInputStream);
            classReader.accept(classNode, 0);
        } finally {
            classFileInputStream.close();
        }

        @SuppressWarnings("unchecked")
        List<MethodNode> methods = classNode.methods;
        for (MethodNode method : methods) {
            if (method.name.equals("<init>") && method.desc.equals(constructorDescriptor)) {
                Type[] argumentTypes = Type.getArgumentTypes(method.desc);
                List<String> parameterNames = new ArrayList<String>(argumentTypes.length);

                @SuppressWarnings("unchecked")
                List<LocalVariableNode> localVariables = method.localVariables;
                for (int i = 0; i < argumentTypes.length; i++) {
                    // The first local variable actually represents the "this" object
                    parameterNames.add(localVariables.get(i + 1).name);
                }

                return parameterNames;
            }
        }

        return null;
    }
    */
}
