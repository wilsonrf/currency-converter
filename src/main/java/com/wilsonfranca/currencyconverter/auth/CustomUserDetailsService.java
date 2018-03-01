package com.wilsonfranca.currencyconverter.auth;

import com.wilsonfranca.currencyconverter.auth.person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * Created by wilson.franca on 01/03/18.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private PersonService personService;

    @Autowired
    public CustomUserDetailsService(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = personService.getByEmail(username)
                .map(person -> {
                    User detail = new User(person.getEmail(),
                            person.getPassword(),
                            person.getAuthorities().stream()
                                    .map(SimpleGrantedAuthority::new)
                                    .collect(Collectors.toList()));
                    return detail;
                })
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return user;
    }
}
