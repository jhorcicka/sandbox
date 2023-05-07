package nl.hi.kuba.dependency;

import nl.hi.kuba.dependency.app.Controller;
import nl.hi.kuba.dependency.framework.Container;

public class DependencyInjectionDemo {
    public static void main(String[] args) {
        final Controller controller = new Container().getController();
        System.out.println(controller.calculate(11, 22));
    }
}
