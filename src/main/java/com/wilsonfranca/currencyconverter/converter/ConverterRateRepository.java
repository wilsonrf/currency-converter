package com.wilsonfranca.currencyconverter.converter;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by wilson.franca on 28/02/18.
 */
public interface ConverterRateRepository extends MongoRepository<ConverterRate, ObjectId> {

    public List<ConverterRate> findByUserOrderByDateCreated(String user, Pageable pageable);

    public List<ConverterRate> findFirs10ByUserOrderByDateCreated(String user);
}
