package Movie;

import java.io.Serializable;
import java.util.Date;

import controller.OutputController;

public class Session implements Serializable {

	/**
	 * Number of sessions so far
	 */

	private static int id;

	/**
	 * The session id
	 */

	private int sessionId;

	/**
	 * The session date
	 */

	private Date sessionDate;

	/**
	 * The movie that is shown in this session
	 */

	private Movie movie;

	/**
	 * The seats state
	 */

	private boolean showingSeats[];

	/**
	 * Create new session
	 * 
	 * @param movie       The movie of this session
	 * @param sessionDate The session date
	 */

	public Session(Movie movie, Date sessionDate) {
		id++;
		this.movie = movie;
		this.sessionDate = sessionDate;
		this.sessionId = id;
		this.showingSeats = new boolean[100];
	}

	/**
	 * Getting the number of empty seats & valid tickets
	 * 
	 * @return number of empty seats
	 */

	public int getValidTickets() {
		int res = 0;
		for (int i = 0; i < showingSeats.length; i++)
			if (!showingSeats[i])
				res++;
		return res;
	}

	/**
	 * Print the seats of this cinema
	 */

	public void printSeats() {
		for (int i = 0; i < showingSeats.length; i += 10) {
			System.out.print("Seats " + (i + 1) + " - " + (i + 10) + "\t");
			for (int j = i; j < i + 10; j++) {
				if (j == i + 10 / 2)
					System.out.print("\t\t");
				System.out.print((showingSeats[j]) ? "X " : "0 ");

			}
			System.out.println();

		}

	}

	/**
	 * Change this valid seat to invalid seat
	 * 
	 * @param index The given seat index
	 * @return false if this seat was invalid and true if someone reserve this seat
	 */

	public boolean makeTrue(int index) {
		if (showingSeats[index - 1])
			return false;
		showingSeats[index - 1] = true;
		return true;

	}

	/**
	 * Change the session's id
	 * 
	 * @param sessionId The session's new id
	 */

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * Change the session's date
	 * 
	 * @param sessionDate The session's new date
	 */

	public void setSessionDate(Date sessionDate) {
		this.sessionDate = sessionDate;
	}
	
	/**
	 * Changing this session's movie to a new one
	 * @param movie the new session's movie
	 */

	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	
	/**
	 * Changing this movie's session date to a new one
	 * @param movie the new movie's session date
	 */

	public void setShowingSeats(boolean[] showingSeats) {
		this.showingSeats = showingSeats;
	}

	/**
	 * Get the date of this session
	 * 
	 * @return the session date
	 */

	public Date getSessionDate() {
		return sessionDate;
	}

	/**
	 * Get the id of this session
	 * 
	 * @return the session id
	 */

	public int getSessionId() {
		return sessionId;
	}

	public int getId() {
		return sessionId;
	}

	public Movie getMovie() {
		return movie;
	}

	public boolean[] getShowingSeats() {
		return showingSeats;
	}

	/**
	 * Getting the string representation of this session
	 * 
	 * @return The session data
	 */

	@Override
	public String toString() {
		return "\n\tSession id:" + sessionId + "\n\tMovie title:" + movie.getName() + "\n\tDate:"
				+ OutputController.printDateTime(sessionDate) + "\n";
	}

}
