package com.java.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Proxy;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity()
@Table(name = "emp", uniqueConstraints = { @UniqueConstraint(columnNames = "ID"),
		@UniqueConstraint(columnNames = "EMAIL") })
@Proxy(lazy = false)

//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private int id;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "dob", nullable = false)
	@JsonIgnore
	private String dob;
	@Column(name = "gender", nullable = false)
	private String gender;
	@Column(name = "address", nullable = false)
	private String address;
	@Column(name = "city", nullable = false)
	private String city;
	@Column(name = "state", nullable = false)
	private String state;
	@Column(name = "pincode", nullable = false)
	private String pincode;
	@Column(name = "contact_no", nullable = false)
	private String contact_no;
	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "password", nullable = false)
	@JsonIgnore
	private String password;
	@Column(name = "com_id", nullable = false)
	private int com_id;
	@JsonIgnore
	@Column(name = "doj", nullable = false)
	private String doj;

	@Column(name = "employeeRandom_key", unique = true)
	private String employeeRandom_key;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getContact_no() {
		return contact_no;
	}

	public void setContact_no(String contact_no) {
		this.contact_no = contact_no;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getCom_id() {
		return com_id;
	}

	public void setCom_id(int com_id) {
		this.com_id = com_id;
	}

	public String getDoj() {
		return doj;
	}

	public void setDoj(String doj) {
		this.doj = doj;
	}

	public String getEmployeeRandom_key() {
		return employeeRandom_key;
	}

	public void setEmployeeRandom_key(String employeeRandom_key) {
		this.employeeRandom_key = employeeRandom_key;
	}

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(int id, String name, String dob, String gender, String address, String city, String state,
			String pincode, String contact_no, String email, String password, int com_id, String doj,
			String employeeRandom_key) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
		this.gender = gender;
		this.address = address;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.contact_no = contact_no;
		this.email = email;
		this.password = password;
		this.com_id = com_id;
		this.doj = doj;
		this.employeeRandom_key = employeeRandom_key;
	}

	@Override
	public String toString() {
		return String.format(
				"User[id=%d, name='%s', dob='%s',gender='%s',address='%s',city='%s',state='%s',pincode='%s',email='%s',password='%s',contact_no='%s',com_id=%d, doj='%s']",
				id, name, dob, gender, address, city, state, pincode, email, password, contact_no, com_id, doj);
	}

}
