package user;

import java.util.HashMap;
import java.util.Map;

public class Goer extends Person {

	/**
	 * The booking-history of this goer
	 */
	
	private Map<String, String> goerHistory;

	/**
	 * Create a goer
	 */
	
	public Goer() {
		goerHistory = new HashMap<>();
	}

	/**
	 * Create a geor with the given attributes
	 * 
	 * @param name         This person name
	 * @param mobileNumber This person mobile number
	 * @param email        This person email
	 */
	public Goer(String name, String mobileNumber, String email) {
		super(name, mobileNumber, email);
		goerHistory = new HashMap<>();
	}

	/**
	 * Add new booking into goerHistory map
	 * 
	 * @param tid   The tid code
	 * @param movie The movie for the reservation
	 */

	public void addTid(String tid, String movie) {
		goerHistory.put(tid, movie);
	}

	/**
	 * Change goer history to a new one
	 * 
	 * @param goerHistory The new goer history
	 */

	public void setGoerHistory(Map<String, String> goerHistory) {
		this.goerHistory = goerHistory;
	}

	/**
	 * Getting the goerHistory map
	 * 
	 * @return goerHistory
	 */

	public Map<String, String> getGoerHistory() {
		return goerHistory;
	}

}
