/**
 * @author j.horcicka (GMC)
 * @since 21.5.15
 */
public class MyClass {
    @MyMethodAnnotation(enabled=false)
    public void myTest1() {
        System.out.println("MyTest1");
    }

    @MyMethodAnnotation(enabled=true)
    public void myTest2() {
        System.out.println("MyTest2");
    }
}
