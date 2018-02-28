package com.wilsonfranca.currencyconverter.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by wilson.franca on 28/02/18.
 */
@Service
public class ConverterRateService {

    private ConverterRateRepository converterRateRepository;

    @Autowired
    public ConverterRateService(ConverterRateRepository converterRateRepository) {
        this.converterRateRepository = converterRateRepository;
    }

    public Collection<ConverterRate> getLastUserRates(String user, int max) {
        return converterRateRepository.findByUserOrderByDateCreated(user, new PageRequest(0, max));
    }

    public ConverterRate save(ConverterRate converterRate) {
        return converterRateRepository.save(converterRate);
    }
}
