package Movie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import controller.OutputController;

public class Movie implements Serializable {

	/**
	 * This movie's name
	 */

	private String name;

	/**
	 * This movie's synopsis
	 */

	private String synopsis;

	/**
	 * This movie's director name
	 */

	private String director;

	/**
	 * This movie's cast number
	 */

	private int castNumber;

	/**
	 * This movie's cast names
	 */

	private String[] castNames;

	/**
	 * This movie's id
	 */

	private int movidId;

	/**
	 * number of movie objects
	 */

	private static int id;

	/**
	 * This movie's reviews list
	 */

	private ArrayList<Review> reviews;

	/**
	 * This movie's number of soldTickets
	 */

	private int ticketCounter;

	/**
	 * This movie's over all rating accroading to reviewer's stars
	 */

	private double overAllRating;

	/**
	 * This movie's rating
	 */

	private MovieRating rate;

	/**
	 * This movie's type
	 */

	private MovieType type;

	/**
	 * This movie's current status
	 */

	private MovieStatus status;

	/**
	 * This movie's duration
	 */

	private double duration;

	/**
	 * This movie's release date
	 */

	private Date releaseDate;

	/**
	 * This movie's end date
	 */

	private Date endDate;

	/**
	 * Create movie
	 */

	public Movie() {
	}

	/**
	 * Create movie with given attributes
	 */

	public Movie(String name, String synopsis, String director, int castNumber, String[] castNames, MovieRating rate,
			MovieType type, MovieStatus status, double duration, Date releaseDate, Date endDate) {
		id++;
		this.name = name;
		this.synopsis = synopsis;
		this.director = director;
		this.castNumber = castNumber;
		this.castNames = castNames;
		this.reviews = new ArrayList<>();
		this.rate = rate;
		this.ticketCounter = 0;
		this.type = type;
		this.status = status;
		this.duration = duration;
		this.releaseDate = releaseDate;
		this.endDate = endDate;
		this.movidId = id;
	}

	/**
	 * Add a new review into reviews list
	 * 
	 * @param a review object
	 */

	public void addReview(Review review) {
		reviews.add(review);
		int sum = 0;
		for (int i = 0; i < reviews.size(); i++)
			sum += reviews.get(i).getStarsNumber();

		overAllRating = (sum * 1.0) / reviews.size();
	}

	/**
	 * Change movie name to a new one
	 * 
	 * @param name The new movie name
	 */

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Change movie story to a new one
	 * 
	 * @param synopsis The new movie synopsis
	 */

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	/**
	 * Change movie director's name to a new one
	 * 
	 * @param director The new movie director's name
	 */

	public void setDirector(String director) {
		this.director = director;
	}

	/**
	 * Change the number of this movie's cast to a new one
	 * 
	 * @param castNumber The number of this movie's cast
	 */

	public void setCastNumber(int castNumber) {
		this.castNumber = castNumber;
	}

	/**
	 * Change this movie's cast names to a new one
	 * 
	 * @param castNames the new cast names
	 */

	public void setCastNames(String[] castNames) {
		this.castNames = castNames;
	}

	/**
	 * Change this movie's id to a new one
	 * 
	 * @param castNames the new movie's id
	 */

	public void setMovidId(int movidId) {
		this.movidId = movidId;
	}

	/**
	 * changing the id of this movie to a new one
	 */

	public void setReviews(ArrayList<Review> reviews) {
		this.reviews = reviews;
	}

	/**
	 * changing the reviews list of this movie to a new one
	 */

	public void setRate(MovieRating rate) {
		this.rate = rate;
	}

	/**
	 * changing the rating of this movie to a new one
	 */

	public void setType(MovieType type) {
		this.type = type;
	}

	/**
	 * changing the type of this movie to a new one
	 */

	public void setStatus(MovieStatus status) {
		this.status = status;
	}

	/**
	 * changing the status of this movie to a new one
	 */

	public void setDuration(double duration) {
		this.duration = duration;
	}

	/**
	 * changing the duration of this movie to a new one
	 */

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	/**
	 * changing the release date of this movie to a new one
	 */

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * changing the end date of this movie to a new one
	 */

	public String getName() {
		return name;
	}

	/**
	 * Getting the name of this movie
	 * 
	 * @return this movie's name
	 */

	public String getSynopsis() {
		return synopsis;
	}

	/**
	 * Getting the synopsis of this movie
	 * 
	 * @return this movie's synopsis
	 */

	public String getDirector() {
		return director;
	}

	/**
	 * Getting the director's name of this movie
	 * 
	 * @return this movie's director name
	 */

	public int getCastNumber() {
		return castNumber;
	}

	/**
	 * Getting the cast number of this movie
	 * 
	 * @return this movie's number of cast
	 */

	public String[] getCastNames() {
		return castNames;
	}

	/**
	 * Getting the cast names of this movie
	 * 
	 * @return this movie's cast names
	 */

	public int getMovidId() {
		return movidId;
	}

	/**
	 * Getting the id of this movie
	 * 
	 * @return this movie's id
	 */

	public ArrayList<Review> getReviews() {
		return reviews;
	}

	/**
	 * Getting the reviews list of this movie
	 * 
	 * @return reviews
	 */

	public MovieRating getRate() {
		return rate;
	}

	/**
	 * Getting the rating of this movie
	 * 
	 * @return this movie's rating
	 */

	public MovieType getType() {
		return type;
	}

	/**
	 * Getting the status of this movie
	 * 
	 * @return this movie's status
	 */

	public MovieStatus getStatus() {
		return status;
	}

	/**
	 * Getting the duration of this movie
	 * 
	 * @return duration
	 */

	public double getDuration() {
		return duration;
	}

	/**
	 * Getting the release date of this movie
	 * 
	 * @return release date
	 */

	public Date getReleaseDate() {
		return releaseDate;
	}

	/**
	 * Getting the end date of this movie
	 * 
	 * @return endDate
	 */

	public Date getEndDate() {
		return endDate;
	}

	/**
	 * Getting the overall rating of this movie
	 * 
	 * @return overAllRating
	 */

	public double getOverAllRating() {
		return overAllRating;
	}

	/**
	 * Getting number of reserved tickets
	 * 
	 * @return ticketCounter
	 */

	public int getTicketCounter() {
		return ticketCounter;
	}

	/**
	 * Return a string which represents the object's data
	 */
	@Override
	public String toString() {
		String str = "\nMovie ID:" + movidId + "\nTitle:" + name + "\nType:" + type.name() + "\nStatus:" + status.name()
				+ "\nSynopsis:" + synopsis + "\nRating:" + rate.name() + "\nDuration:" + duration + "\nRelease Date:"
				+ OutputController.printDate(releaseDate) + "\nEnd date:" + OutputController.printDate(endDate)
				+ "\nDirector:" + director + "\nCast: ";
		String overAll = String.format("\"%.1f", overAllRating);
		for (int i = 0; i < castNumber; i++)
			str += (i == castNumber - 1) ? castNames[i] : castNames[i] + "-";
		str += "\nOverall review rating: " + ((reviews.size() < 2) ? "N/A" : overAll) + "\nReviews:\n";

		for (Review review : reviews)
			str += review.toString();

		return str;

	}

}
