package com.ms2.activity.currencyconverter.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name="currency-conversion-factor-service")
@RibbonClient(name="currency-conversion-factor-service")
public interface  CurrencyConversionServiceProxy {

	@GetMapping("/getconversionfactor")
	public double retrieveConvertedValue(@RequestParam(value="countryCode") String country);
}
