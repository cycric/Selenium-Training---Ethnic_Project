package com.EthnicStore.test.integration.model;

public class NewRegistrationDetails {
	private String email;
	private String password;
	private String rePassword;
	private String firstName;
	private String lastName;
	private String country;
	private String state;
	private String zipcode;
	private String city;
	private String streetLine1;
	private String streetLine2;
	private String phone;
	private String gender;
	private String dob;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setGender(String gender)
	{
		this.gender = gender;
	}
	public void setDOB(String dob)
	{
		this.dob=dob;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRePassword() {
		return rePassword;
	}
	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStreetLine1() {
		return streetLine1;
	}
	public void setStreetLine1(String streetLine1) {
		this.streetLine1 = streetLine1;
	}
	public String getStreetLine2() {
		return streetLine2;
	}
	public void setstreetLine2(String streetLine2) {
		this.streetLine2 = streetLine2;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDOB()
	{
		return dob;
	}
	public String getGender()
	{
		return gender;
	}
	
}
