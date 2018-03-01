package com.wilsonfranca.currencyconverter.auth.person;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * Created by wilson.franca on 01/03/18.
 */
public interface PersonRepository extends MongoRepository<Person, ObjectId> {


    Optional<Person> findByEmail(String email);

    Optional<Person> findByEmailAndPassword(String email, String password);

}
