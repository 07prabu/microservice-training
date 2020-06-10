package com.ms1.activity.currencyconversionfactor;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
/*import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;*/
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms1.activity.currencyconversionfactor.configuration.RibbonConfiguration;

@EnableHystrixDashboard
@EnableCircuitBreaker
@SpringBootApplication
@EnableDiscoveryClient
@RibbonClient(name = "currency-convertor-service", configuration = RibbonConfiguration.class)
@RestController
public class CurrencyConversionFactorApplication {
	private static final Logger LOG = Logger.getLogger(CurrencyConversionFactorApplication.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(CurrencyConversionFactorApplication.class, args);
	}
	
	@RequestMapping("/")
	   public String index() {
	      LOG.log(Level.INFO, "Index API is calling");
	      return "MS-1 Sleuth!";
	   }

}
