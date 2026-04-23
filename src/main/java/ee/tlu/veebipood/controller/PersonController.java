package ee.tlu.veebipood.controller;

import ee.tlu.veebipood.dto.PersonLoginRecordDto;
import ee.tlu.veebipood.entity.Person;
import ee.tlu.veebipood.repository.PersonRepository;
import ee.tlu.veebipood.service.PersonService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController

public class PersonController {
    @Autowired
    private PersonRepository personRepository;

    // Dependency Injection. Kui luuakse see klass (PersonController), seotakse ära samal ajal temaga kõik allolevad muutujad

    @Autowired
    private PersonService personService;

    @GetMapping("persons")
    public List<Person> getPersons() {
        return  personRepository.findAll();
    }

    @DeleteMapping("persons/{id}")
    public List<Person> deletePerson(@PathVariable Long id){
        personRepository.deleteById(id);
        return personRepository.findAll();
    }

    @PostMapping("signup")
    public Person signup(@RequestBody Person person){
        personService.validate(person);
        return personRepository.save(person); //siin uuenenud seis
    }

    @PostMapping("login")
    public Person login(@RequestBody PersonLoginRecordDto personDto){
        Person dbPerson = personRepository.findByEmail(personDto.email());
        if (dbPerson == null) {
            throw new RuntimeException("Invalid email");
        }
        if (!dbPerson.getPassword().equals(personDto.password())) {
            throw new RuntimeException("Invalid password");
        }
        return dbPerson;
    }
}
