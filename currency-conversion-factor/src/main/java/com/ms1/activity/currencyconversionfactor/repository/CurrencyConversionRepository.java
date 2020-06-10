package com.ms1.activity.currencyconversionfactor.repository;

import org.springframework.data.repository.CrudRepository;

import com.ms1.activity.currencyconversionfactor.datamodel.CurrencyConversionFactor;

public interface CurrencyConversionRepository extends CrudRepository<CurrencyConversionFactor, Integer> {

}
