package nl.hi.kuba.sandbox.jtree;

/**
 * @since 27. 11. 2015
 */
public class Employee {
    public String firstName;
    public String lastName;
    public float salary;

    public Employee(String f, String l, float s) {
        this.firstName = f;
        this.lastName = l;
        this.salary = s;
    }

    public String getName() {
        return firstName + " " + lastName;
    }
}
