package com.ms1.activity.currencyconversionfactor.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class CurrencyConversionFactor {

	public CurrencyConversionFactor(Long id, String countryCode, double convFactor) {
		super();
		this.id = id;
		this.countryCode = countryCode;
		this.convFactor = convFactor;
	}

	public CurrencyConversionFactor(){
		super();
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column 
	private String countryCode;
	
	@Column
	private double convFactor;

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public double getConvFactor() {
		return convFactor;
	}

	public void setConvFactor(double convFactor) {
		this.convFactor = convFactor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return String.format(
		        "CurrencyConversionFactor[id=%d, countryCode='%s', convFactor='%s']",
		        id, countryCode, convFactor);
	}
	
}
