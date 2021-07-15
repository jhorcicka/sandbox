package nl.hi.kuba.ocpjp;

public class Person implements Comparable<Person> {
    private String name;
    private int age;

    public Person(final String name, final int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int compareTo(Person person) {
        int comp = this.name.compareTo(person.name);
        int result = comp != 0 ? comp : person.age - this.age;

        System.err.println("MYTODO: comparing: " + this.toString() + " <> " + person.toString() + " => " + result);
        return result;
    }

    public String toString() {
        return this.name + ": " + this.age;
    }
}
