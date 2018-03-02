package com.wilsonfranca.currencyconverter.auth;

import com.wilsonfranca.currencyconverter.auth.person.Person;
import com.wilsonfranca.currencyconverter.auth.person.PersonExistsException;
import com.wilsonfranca.currencyconverter.auth.person.PersonService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.support.BindingAwareModelMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by wilson.franca on 01/03/18.
 */
public class AuthControllerTest {

    AuthController authController;
    PersonService personService;

    @Before
    public void before() {
        personService = mock(PersonService.class);
        authController = new AuthController(personService);
    }

    @Test
    public void test_try_to_signup_new_user() {

        String result = null;
        SignupFormData signupFormData = new SignupFormData();
        signupFormData.setEmail("validemail@gmail.com");
        signupFormData.setFirstName("Wilson");
        signupFormData.setLastName("França");
        signupFormData.setPassword("password");

        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);

        Model model = new BindingAwareModelMap();

        when(personService.register(signupFormData)).thenReturn(new Person());

        result = authController.signup(signupFormData, bindingResult, model);

        assertThat(result).isEqualTo("security/login");

    }

    @Test
    public void test_try_to_signup_new_user_with_invalid_email() {

        String result = null;
        SignupFormData signupFormData = new SignupFormData();
        signupFormData.setEmail("invalidEmail");
        signupFormData.setFirstName("Wilson");
        signupFormData.setLastName("França");
        signupFormData.setPassword("password");

        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);

        Model model = new BindingAwareModelMap();

        result = authController.signup(signupFormData, bindingResult, model);

        assertThat(result).isEqualTo("security/signup");

        verify(personService, never()).register(signupFormData);

    }

    @Test
    public void test_try_to_signup_already_existed_user() {

        String result = null;
        SignupFormData signupFormData = new SignupFormData();
        signupFormData.setEmail("invalidEmail");
        signupFormData.setFirstName("Wilson");
        signupFormData.setLastName("França");
        signupFormData.setPassword("password");

        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);

        Model model = new BindingAwareModelMap();

        when(personService.register(signupFormData)).thenThrow(new PersonExistsException());

        result = authController.signup(signupFormData, bindingResult, model);

        assertThat(result).isEqualTo("security/signup");

    }

}
