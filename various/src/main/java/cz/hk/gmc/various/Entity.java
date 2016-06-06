package cz.hk.gmc.various;

/**
 * Created by j.horcicka (GMC).
 */
public class Entity {
    private String name = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void doMath() {
        int x = 42;
        int y = 24;
        int z = x + y;
        System.out.println("z=" + z);
    }

    @Override
    public String toString() {
        return "Entity{" +
                "name='" + name + '\'' +
                '}';
    }
}
