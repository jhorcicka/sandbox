import javax.inject.Inject;

public class Person {
    private final String name;
    
    @Inject
    Animal animal;

    Person(final String name) {
        this.name = name;
    }
    
    public void setAnimal(final Animal animal) {
        this.animal = animal;
    }

    @Override
    public String toString() {
        return "Person/" + name + " with " + animal.toString();
    }
}
