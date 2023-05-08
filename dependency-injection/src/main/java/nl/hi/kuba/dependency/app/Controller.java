package nl.hi.kuba.dependency.app;

import nl.hi.kuba.dependency.framework.Dependency;

public class Controller {

    @Dependency
    private Service service;

    public int calculate(int x, int y) {
        return service.add(x, y);
    }
}
