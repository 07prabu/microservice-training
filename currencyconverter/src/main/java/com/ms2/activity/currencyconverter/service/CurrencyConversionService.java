package com.ms2.activity.currencyconverter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ms2.activity.currencyconverter.proxy.CurrencyConversionServiceProxy;

@Service
public class CurrencyConversionService {

	@Autowired
	CurrencyConversionServiceProxy currencyConversionServiceProxy;
	
	@Autowired
    Environment environment;
	
	public double convertedAmount(String countryCode, double amount){
		String serverPort = environment.getProperty("local.server.port");
		System.out.println("Port : " + serverPort);
		System.out.println("entering>>>>>>"+countryCode+"amount>>>>>>>"+amount);
		String url = "http://9.85.109.28:9090/getconversionfactor";
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
		        .queryParam("countryCode", countryCode);
	    double convertedAmount = 1;
	    ResponseEntity<Double> responseEntity = new RestTemplate().exchange( builder.toUriString(), 
	            HttpMethod.GET, 
	            entity, 
	            double.class);
	    System.out.println("response.getConversionFactor()>>>"+responseEntity.getBody());
	    double conversionFactor = responseEntity.getBody();
	   convertedAmount = conversionFactor*amount;
		return convertedAmount;
	}
	
	public double feignConvertedAmount(String countryCode, double amount){
		System.out.println("feignConvertedAmount>>>>>>>>"+countryCode+"   amount "+amount);
		double convertedAmount = 1;
		
		double conversionFactor = currencyConversionServiceProxy.retrieveConvertedValue(countryCode);
		System.out.println("conversionFactor>>>>"+conversionFactor);
		convertedAmount = conversionFactor*amount;
		
		return convertedAmount;
	}
}
