package com.ecommerce.multivendor.multivendor.modal;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Address {
	@Id
	 @GeneratedValue(strategy =GenerationType.AUTO )
	 private Long id;
	
	
	private String name;
	
	
	private String locality;
	
	
	private String address;
	
	
	private String city;
	
	
	private String state;
	
	
	private String pinCode;
	
	private String mobile;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getPinCode() {
		return pinCode;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, city, id, locality, mobile, name, pinCode, state);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return Objects.equals(address, other.address) && Objects.equals(city, other.city)
				&& Objects.equals(id, other.id) && Objects.equals(locality, other.locality)
				&& Objects.equals(mobile, other.mobile) && Objects.equals(name, other.name)
				&& Objects.equals(pinCode, other.pinCode) && Objects.equals(state, other.state);
	}

	public Address() {
		super();
	}

	public Address(Long id, String name, String locality, String address, String city, String state, String pinCode,
			String mobile) {
		super();
		this.id = id;
		this.name = name;
		this.locality = locality;
		this.address = address;
		this.city = city;
		this.state = state;
		this.pinCode = pinCode;
		this.mobile = mobile;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
	
	
	
	
	
	
	
}
