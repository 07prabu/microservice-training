package com.ms2.activity.currencyconverter;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@RestController
public class CurrencyconverterApplication {
	private static final Logger LOG = Logger.getLogger(CurrencyconverterApplication.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(CurrencyconverterApplication.class, args);
	}

	@RequestMapping("/")
	   public String index() {
	      LOG.log(Level.INFO, "Index API is calling");
	      return "MS-2 Sleuth!";
	   }
}
