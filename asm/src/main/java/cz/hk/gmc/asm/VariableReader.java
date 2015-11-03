package cz.hk.gmc.asm;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.EmptyVisitor;

/**
 * @since 29. 09. 2015
 */
class VariableReader extends EmptyVisitor {
    private Map<String, Map<Integer, String>> methodParameters = new HashMap<>();
    private String currentMethod;

    public MethodVisitor visitMethod(int access, String methodName, String description, String signature,
                                     String[] exceptions) {
        currentMethod = methodName + description;
        return this;
    }

    public void visitLocalVariable(String variableName, String description, String signature, Label start, Label end,
                                   int index) {
        Map<Integer, String> parameters = methodParameters.get(currentMethod);

        if (parameters==null) {
            parameters = new HashMap<>();
            methodParameters.put(currentMethod, parameters);
        }

        parameters.put(index, variableName);
    }

    public Map<Integer, String> getVariableNames(Method method) {
        String key = method.getName() + Type.getMethodDescriptor(method);
        return methodParameters.get(key);
    }
}
