public class Animal {
    private static int counter = 0;
    private final String name;

    Animal() {
        this("Unnamed animal");
    }

    Animal(String name) {
        this.name = name + ":" + counter++;
    }

    @Override
    public String toString() {
        return "Animal/" + name;
    }
}
