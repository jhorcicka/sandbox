package nl.hi.kuba.dependency.framework;

import java.util.Arrays;

import nl.hi.kuba.dependency.app.Controller;
import nl.hi.kuba.dependency.app.Service;

public class Container {
    private Service service;
    private Controller controller;

    public Container() {
        service = new Service();
        controller = new Controller();
        inject();
    }

    public Controller getController() {
        return controller;
    }

    private void inject() {
        Arrays
                .stream(controller.getClass().getDeclaredFields())
                .filter(field -> field.getAnnotation(Dependency.class) != null)
                .forEach(field -> {
                    try {
                        field.set(controller, service);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
