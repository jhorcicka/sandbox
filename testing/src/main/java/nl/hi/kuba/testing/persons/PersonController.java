package nl.hi.kuba.testing.persons;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;

// http://localhost:9090/
@RestController()
public class PersonController {
    @Autowired
    private PersonRepository repository;

    @GetMapping(value="/persons")
    public List<Person> getPersons() {
        return repository.findAll();
    }

    @GetMapping(value="/persons/{id}")
    public Person getPerson(@PathVariable(required=true, name="id") final Long id) throws Throwable {
        return repository
                .findById(id)
                .orElseThrow(
                        (Supplier<Throwable>) () -> new NotFoundException("Person with id=" + id + " was not found. "));
    }
}
