package com.example.jpetstore.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class Address implements Serializable {
	/* Private Fields */
	private String addr1;
	private String addr2;
	private String city;
	private String state;
	private String zip;
	private String country;

	public Address() { }
	
	public Address(String addr1, String addr2, String city, String state, String zip, String country) {
		super();
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.country = country;
	}
	
	public Address(Address address) {
		super();
		this.addr1 = address.addr1;
		this.addr2 = address.addr2;
		this.city = address.city;
		this.state = address.state;
		this.zip = address.zip;
		this.country = address.country;
	}

	/* JavaBeans Properties */
	public String getaddr1() {
		return addr1;
	}

	public void setaddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getaddr2() {
		return addr2;
	}

	public void setaddr2(String addr2) {
		this.addr2 = addr2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
