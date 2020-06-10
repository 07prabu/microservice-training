package com.ms2.activity.currencyconverter.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ms2.activity.currencyconverter.service.CurrencyConversionService;

@RestController
public class CurrencyConversionController {
	
	@Autowired
	CurrencyConversionService currencyConversionService;
	
	@GetMapping("/currency-converter")
	  public double convertCurrency(@RequestParam String countryCode, @RequestParam double amount) {
	    return currencyConversionService.convertedAmount(countryCode, amount);
	  }
	
	@GetMapping("/feign-currency-converter")
	  public double feignConvertCurrency(@RequestParam String countryCode, @RequestParam double amount) {
		System.out.println("feign-currency-converter>>>>"+countryCode+"<<<<< amount >>>>> "+amount);
	    return currencyConversionService.feignConvertedAmount(countryCode, amount);
	  }

}
