package com.ms1.activity.currencyconversionfactor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ms1.activity.currencyconversionfactor.datamodel.CurrencyConversionFactor;
import com.ms1.activity.currencyconversionfactor.response.ResponseStatus;
import com.ms1.activity.currencyconversionfactor.service.CurrencyConversionFactorService;

@RestController
public class CurrencyConversionFactorController {
	
	@Autowired 
	CurrencyConversionFactorService currencyConversionFactorService;
	
	@PostMapping("/addconversionfactor")
	public ResponseStatus addConversionFactor(@RequestBody CurrencyConversionFactor conversionFactor){
		ResponseStatus responseStatus = new ResponseStatus();
		responseStatus = currencyConversionFactorService.addConversion(conversionFactor);
		//System.out.println("entering>>>addConversionFactor>>");
		
		return responseStatus;
	}
	
	@GetMapping("/getconversionfactor")
	public double getConversionFactor(@RequestParam String countryCode){
		System.out.println("entering>>>>>"+countryCode);
		return currencyConversionFactorService.getConversionFactor(countryCode);
	}
	
	@PutMapping("/updateconversionfactor")
	public ResponseStatus updateconversionfactor(@RequestBody CurrencyConversionFactor conversionFactor){
		ResponseStatus responseStatus = new ResponseStatus();
		responseStatus = currencyConversionFactorService.updateConversion(conversionFactor);
		//System.out.println("entering>>>addConversionFactor>>");
		
		return responseStatus;
	}
	
	/*@GetMapping(value="/getconversionfactor/{countryCode}")
	public List<CurrencyConversionFactor> getConversionFactor(@PathVariable String countryCode){
		System.out.println("entering>>>>>"+countryCode);
		return currencyConversionFactorService.getConversionFactor(countryCode);
	}*/

	/*@GetMapping(value="/prabu")
	public String index() {
System.out.println("preeeee>>>>");
return currencyConversionFactorService.getConversionFactor(countryCode);
	}*/
}
