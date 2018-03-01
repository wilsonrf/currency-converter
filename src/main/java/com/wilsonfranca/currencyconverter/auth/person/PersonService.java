package com.wilsonfranca.currencyconverter.auth.person;

import com.wilsonfranca.currencyconverter.auth.SignupFormData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by wilson.franca on 01/03/18.
 */
@Service
public class PersonService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person register(SignupFormData signupFormData) {

        Person person = Person.from(signupFormData);
        Optional<Person> persisted = personRepository.findByEmail(person.getEmail());

        persisted.ifPresent(p -> {
            throw new PersonExistsException();
        });

        Person toPersist = personRepository.save(person);

        logger.info("New User [{}] registered with id [{}]", person.getEmail(), person.getId());

        return toPersist;
    }

    public Optional<Person> getByEmail(String email) {
        return personRepository.findByEmail(email);
    }
}
