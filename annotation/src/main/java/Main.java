import java.lang.reflect.Method;

/**
 * @author j.horcicka (GMC)
 * @since 21.5.15
 */
public class Main {
    public static void main(String[] arguments) {
        try {
            Class<MyClass> classObject = MyClass.class;

            for (Method method : classObject.getDeclaredMethods()) {
                if (method.isAnnotationPresent(MyMethodAnnotation.class)) {
                    MyMethodAnnotation annotation = method.getAnnotation(MyMethodAnnotation.class);

                    if (annotation.enabled()) {
                        method.invoke(new MyClass());
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
