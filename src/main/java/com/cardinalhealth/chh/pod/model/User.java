package com.cardinalhealth.chh.pod.model;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="user")
public class User {
	
	@Id
    @Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private Long id;
	
	private String guid;
	
	private String firstName;
	
	private String lastName;
	
	@JsonIgnore
	private String password;
	
	private int age;
	
	private String Address;
	
	private String state;
	
	private String country;
	
	private long mobileNumber;

	
	public User(String firstName, String lastName, int age, String address, String state, String country,
			long mobileNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		Address = address;
		this.state = state;
		this.country = country;
		this.mobileNumber = mobileNumber;
	}

	public User(String firstName, String lastName, String password, int age, String address, String state,
			String country, long mobileNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.age = age;
		Address = address;
		this.state = state;
		this.country = country;
		this.mobileNumber = mobileNumber;
		this.guid = UUID.randomUUID().toString();
	}

	public User(Long id, String guid, String firstName, String lastName, String password, int age, String address,
			String state, String country, long mobileNumber) {
		super();
		this.id = id;
		this.guid = guid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.age = age;
		Address = address;
		this.state = state;
		this.country = country;
		this.mobileNumber = mobileNumber;
	}

	public User() {
		super();
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGuid() {
		return guid;
	}

	
	public void setGuid(String guid) {
		if (this.guid == null || this.guid.isEmpty()) {
		      this.guid = UUID.randomUUID().toString();
		}
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}


	public void setMobileNumber(int mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Address == null) ? 0 : Address.hashCode());
		result = prime * result + age;
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((guid == null) ? 0 : guid.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + (int) (mobileNumber ^ (mobileNumber >>> 32));
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (Address == null) {
			if (other.Address != null)
				return false;
		} else if (!Address.equals(other.Address))
			return false;
		if (age != other.age)
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (guid == null) {
			if (other.guid != null)
				return false;
		} else if (!guid.equals(other.guid))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (mobileNumber != other.mobileNumber)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", guid=" + guid + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", password=" + password + ", age=" + age + ", Address=" + Address + ", state=" + state + ", country="
				+ country + ", mobileNumber=" + mobileNumber + "]";
	}
	
	

}
