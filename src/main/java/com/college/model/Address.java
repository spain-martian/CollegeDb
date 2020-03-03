package com.college.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {
	@Column(nullable = false, length = 100)  
    private String addressLine;

	@Column(nullable = false, length = 100)  
    private String city;

	@Column(nullable = false, length = 30)  
    private String country;

	@Column(nullable = false, length = 6)  
    private String zipCode;


    public Address() {

    }


	public Address(String addressLine, String city, String country, String zipCode) {
		super();
		this.addressLine = addressLine;
		this.city = city;
		this.country = country;
		this.zipCode = zipCode;
	}


	public String getAddressLine() {
		return addressLine;
	}


	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getZipCode() {
		return zipCode;
	}


	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}


	@Override
	public String toString() {
		return "Address= (" + addressLine + ", " + city + ", " + country + ", " + zipCode + ")";
	}
	
	

}
