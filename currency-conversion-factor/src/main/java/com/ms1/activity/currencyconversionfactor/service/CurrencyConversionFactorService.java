package com.ms1.activity.currencyconversionfactor.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.ms1.activity.currencyconversionfactor.datamodel.CurrencyConversionFactor;
import com.ms1.activity.currencyconversionfactor.repository.CurrencyConversionRepository;
import com.ms1.activity.currencyconversionfactor.response.ResponseStatus;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class CurrencyConversionFactorService {
	
	@Autowired
	CurrencyConversionRepository currencyConversionRepository;
	
	@Autowired
    Environment environment;
	
	public ResponseStatus addConversion(CurrencyConversionFactor currencyConversionFactor){
		boolean exists = false;
		ResponseStatus responseStatus = new ResponseStatus();
		List<CurrencyConversionFactor> currencyConversionFactorList = new ArrayList<CurrencyConversionFactor>();
		currencyConversionRepository.findAll().forEach(getconversionfactor -> currencyConversionFactorList.add(getconversionfactor));
		Iterator<CurrencyConversionFactor> iterator =currencyConversionFactorList.iterator();
		CurrencyConversionFactor cFactor = new CurrencyConversionFactor();
		while(iterator.hasNext()){
			cFactor = iterator.next();
			if(cFactor.getCountryCode().equals(currencyConversionFactor.getCountryCode())){
				exists = true;
				break;
			}
			//System.out.println("cFactor>>>>"+cFactor.getConvFactor());
		}
		if(!exists){
			currencyConversionRepository.save(currencyConversionFactor);
			responseStatus.setStatus("Sucess");
			responseStatus.setErrorMsg("Sucessfully instered into DB");
		}else{
			responseStatus.setStatus("Failed");
			responseStatus.setErrorMsg("country conversion already exists");
		}
		return responseStatus;
	}
	
	public ResponseStatus updateConversion(CurrencyConversionFactor currencyConversionFactor){
		boolean exists = false;
		ResponseStatus responseStatus = new ResponseStatus();
		List<CurrencyConversionFactor> currencyConversionFactorList = new ArrayList<CurrencyConversionFactor>();
		currencyConversionRepository.findAll().forEach(getconversionfactor -> currencyConversionFactorList.add(getconversionfactor));
		Iterator<CurrencyConversionFactor> iterator =currencyConversionFactorList.iterator();
		CurrencyConversionFactor cFactor = new CurrencyConversionFactor();
		while(iterator.hasNext()){
			cFactor = iterator.next();
			if(cFactor.getCountryCode().equals(currencyConversionFactor.getCountryCode())){
				exists = true;
				break;
			}
		}
		if(!exists){
			responseStatus.setStatus("Failed");
			responseStatus.setErrorMsg("country does not exists");
		}else{
			currencyConversionRepository.save(currencyConversionFactor);
			responseStatus.setStatus("Sucess");
			responseStatus.setErrorMsg("Sucessfully updated into DB");
		}
		return responseStatus;
	}
	
	@HystrixCommand(fallbackMethod = "getDefaultConversionFactor")
	public double getConversionFactor(String country){
		String serverPort = environment.getProperty("local.server.port");
		 
        System.out.println("load balaner check  Port : " + serverPort);
		List<CurrencyConversionFactor> currencyConversionFactorList = new ArrayList<CurrencyConversionFactor>();
		double conversionFactor = 0;
		currencyConversionRepository.findAll().forEach(getconversionfactor -> currencyConversionFactorList.add(getconversionfactor));
		Iterator<CurrencyConversionFactor> iterator =currencyConversionFactorList.iterator();
		CurrencyConversionFactor cFactor = new CurrencyConversionFactor();
		while(iterator.hasNext()){
			cFactor = iterator.next();
			if(cFactor.getCountryCode().equals(country)){
				conversionFactor = cFactor.getConvFactor();
				break;
			}
			//System.out.println("cFactor>>>>"+cFactor.getConvFactor());
		}
		
		/*Iterator<CurrencyConversionFactor> iterator = currencyConversionRepository.findAll().iterator();
		CurrencyConversionFactor cFactor = new CurrencyConversionFactor();
		while(iterator.hasNext()){
			cFactor = iterator.next();
			if(cFactor.getCountryCode().equals(countryCode)){
				break;
			}
		}*/
		return conversionFactor;
	}
	
	@SuppressWarnings("unused")
	double getDefaultConversionFactor(String country){
		double defaultValue = 1;
		return defaultValue;
	}

}
