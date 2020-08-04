package user;

public class Admin extends Person {

	/**
	 * This admin password
	 */

	private String password;

	/**
	 * Create an admin
	 */

	public Admin() {}
	/**
	 * Create an admin with the given attributes
	 * 
	 * @param name     This admin name
	 * @param email    This admin email
	 * @param password This admin password
	 */

	public Admin(String name, String email, String password) {
		super(name, email);
		this.password = password;
	}

	/**
	 * Update admin password
	 * 
	 * @param passowrd The new password
	 */

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Getting admin password
	 * 
	 * @return the admin name
	 */

	public String getPassword() {
		return password;
	}

}
