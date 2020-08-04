package user;

import java.io.Serializable;

public abstract class Person implements Serializable {
	/**
	 * 
	 */
	/**
	 * This person name
	 */
	protected String name;
	/**
	 * This person mobile number
	 */
	protected String mobileNumber;
	/**
	 * This person email
	 */
	protected String email;

	/**
	 * Create a person
	 */
	public Person() {
	}

	/**
	 * Create a person with the given attributes
	 * 
	 * @param name  This person name
	 * @param email This person email
	 */
	public Person(String name, String email) {
		this.name = name;
		this.email = email;
	}

	/**
	 * Create a person with the givent attributes
	 * 
	 * @param name         This person name
	 * @param mobileNumber This person mobileNumber
	 * @param email        This person email
	 */
	public Person(String name, String mobileNumber, String email) {
		this.name = name;
		this.mobileNumber = mobileNumber;
		this.email = email;
	}

	/**
	 * Update a person name
	 * 
	 * @param name The new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Update a person mobile number
	 * 
	 * @param mobileNumber The new mobile numbers
	 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	/**
	 * Update a person email
	 * 
	 * @param email The new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Getting a person name
	 * 
	 * @return the person name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getting a person mobile number
	 * 
	 * @return the person mobile number
	 */
	public String getMobileNumber() {
		return mobileNumber;
	}

	/**
	 * Getting a person email
	 * 
	 * @return the person email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return string representation of this object
	 */
	@Override
	public String toString() {
		return "Name :" + name + "\nEmail:" + email + "\nMobile number: " + mobileNumber;
	}

}
