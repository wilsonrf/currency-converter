package com.wilsonfranca.currencyconverter.auth.person;

import com.wilsonfranca.currencyconverter.auth.SignupFormData;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by wilson.franca on 01/03/18.
 */
public class PersonServiceTest {

    PersonService personService;
    PersonRepository personRepository;

    @Before
    public void before() {
        personRepository = mock(PersonRepository.class);
        personService = new PersonService(personRepository);
    }

    @Test(expected = PersonExistsException.class)
    public void test_register_new_account_if_not_exists() {

        String email = "wilsonrf@gmail.com";

        Person person = new Person();
        person.setEmail(email);

        SignupFormData signupFormData = new SignupFormData();
        signupFormData.setEmail(email);

        when(personRepository.findByEmail(email)).thenReturn(Optional.ofNullable(person));

        personService.register(signupFormData);

    }

    @Test
    public void test_try_to_register_new_user_that_not_exists() {

        String email = "wilsonrf@gmail.com";

        Person person = new Person();
        person.setEmail(email);

        SignupFormData signupFormData = new SignupFormData();
        signupFormData.setEmail(email);

        when(personRepository.findByEmail(email)).thenReturn(Optional.empty());

        Person persisted = new Person();
        persisted.setEmail(email);

        when(personRepository.save(any(Person.class))).thenReturn(persisted);

        Person registered = personService.register(signupFormData);

        assertThat(registered.getEmail()).isEqualTo(signupFormData.getEmail());

    }

}
