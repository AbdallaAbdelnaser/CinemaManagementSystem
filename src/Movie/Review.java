package Movie;

import java.io.Serializable;

public class Review implements Serializable {

	/**
	 * The userName of the reviewer
	 */

	private String userName;

	/**
	 * The number of stars for the movie
	 */

	private int starsNumber;

	/**
	 * The reviewer comment
	 */

	private String comment;

	/**
	 * Creating review
	 * 
	 * @param userName    reviewer's username
	 * @param starsNumber reviewer's stars
	 * @param comment     reviewer's comment
	 */

	public Review(String userName, int starsNumber, String comment) {
		this.userName = userName;
		this.starsNumber = starsNumber;
		this.comment = comment;
	}

	/**
	 * Getting reviewer's username
	 * 
	 * @return reviewer's username
	 */

	public String getUserName() {
		return userName;
	}

	/**
	 * Getting reviewer's stars
	 * 
	 * @return reviewer's stars
	 */

	public int getStarsNumber() {
		return starsNumber;
	}

	/**
	 * Getting reviewer's comment
	 * 
	 * @return reviewer's comment
	 */

	public String getComment() {
		return comment;
	}

	/**
	 * @return String representation of this object
	 */

	@Override
	public String toString() {
		return "\n\tUsername:" + userName + "\n\tNumber of stars:" + starsNumber + "\n\tComment:" + comment + "\n\n";
	}

}
