package ee.tlu.veebipood.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ee.tlu.veebipood.entity.Person;

public interface PersonRepository extends JpaRepository<Person,Long> {
    Person findByEmail(String email);
}
