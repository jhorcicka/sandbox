package cz.hk.gmc.jersey;

public class Employee {
    private Integer id;
    private String name;

    Employee() {
    }

    Employee(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    Integer getId() {
        return id;
    }

    void setId(Integer id) {
        this.id = id;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + "]";
    }
}
