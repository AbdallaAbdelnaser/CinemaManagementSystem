package controller;

import java.util.Date;

import Movie.Movie;
import Movie.Session;
import entity.Cinema;
import entity.CinemaType;
import entity.Cineplex;

public class CineplexController {
	/**
	 * The cinema's cinplex list
	 */
	private static Cineplex[] cineplexList;

	/**
	 * The current cineplex index in the array
	 */
	private static int currentCineplex;

	/**
	 * The current cinema index
	 */
	private int currentCinema;

	/**
	 * Create CineplexController and initialize cineplexList
	 */
	public CineplexController() {
		cineplexList = new Cineplex[3];
		cineplexList[0] = new Cineplex("Orchard Movies Cineplex",
				new Cinema[] { new Cinema("RTU", CinemaType.STANDARD, "RTUSessions"),
						new Cinema("DFK", CinemaType.STANDARD, "DFKSessions"),
						new Cinema("TYU", CinemaType.PREMIUM, "TYUSessions") });
		cineplexList[1] = new Cineplex("Sentosa Beach Cineplex",
				new Cinema[] { new Cinema("XDP", CinemaType.STANDARD, "XDPSessions"),
						new Cinema("PUP", CinemaType.STANDARD, "PUPSessions"),
						new Cinema("VAL", CinemaType.PREMIUM, "VALSessions") });

		cineplexList[2] = new Cineplex("Movie Base Cineplex",
				new Cinema[] { new Cinema("TIR", CinemaType.STANDARD, "TIRSessions"),
						new Cinema("EFG", CinemaType.STANDARD, "EFGSessions"),
						new Cinema("IUT", CinemaType.PREMIUM, "IUTSessions") });

		loadData();

	}

	/**
	 * load Data in each cinema
	 */

	private void loadData() {
		for (Cineplex cineplex : cineplexList)
			for (Cinema cineam : cineplex.getCinemaList())
				cineam.loadData();

	}

	/**
	 * save Data in each cinema
	 */

	public void saveData() {
		for (Cineplex cineplex : cineplexList)
			for (Cinema cineam : cineplex.getCinemaList())
				cineam.saveData();

	}

	/**
	 * Determine the current cineplex index if found
	 * 
	 * @param name The cineplex name
	 * @return if a cineplex was determined
	 */
	public static boolean determineCineplex(String name) {
		for (int i = 0; i < 3; i++)
			if (cineplexList[i].getName().equals(name)) {
				currentCineplex = i;
				return true;
			}
		return false;
	}

	/**
	 * Print current cineplex's cinema codes
	 */
	public void printCinema() {
		for (int i = 0; i < 3; i++)
			System.out.println(cineplexList[currentCineplex].getCinemaList()[i]);

	}

	/**
	 * Determine the current cinema index if found
	 * 
	 * @param code The cinema code
	 * @return if a cinema was determined
	 */
	public boolean determineCinema(String code) {
		for (int i = 0; i < 3; i++)
			if (cineplexList[currentCineplex].getCinemaList()[i].getCinemaCode().equals(code)) {
				currentCinema = i;
				return true;
			}
		return false;
	}

	/**
	 * add session in the current cinema
	 * 
	 * @param session The new movie session
	 */
	public void addSession(Session session) {
		cineplexList[currentCineplex].getCinemaList()[currentCinema].addSession(session);
		cineplexList[currentCineplex].getCinemaList()[currentCinema].saveData();
	}

	/**
	 * Show session list in each cinema that in the current cineplex
	 */
	public void showSession() {
		Cinema[] cinemaList = cineplexList[currentCineplex].getCinemaList();
		for (int i = 0; i < cinemaList.length; i++) {
			System.out.println("Cinema code:" + cinemaList[i].getCinemaCode());
			if (cinemaList[i].getSessionList().size() == 0)
				System.out.println("\n\tNo sessions !\n");
			for (Session session : cinemaList[i].getSessionList())
				System.out.println(session);

		}

	}

	/**
	 * Delete session with given id
	 * 
	 * @param id The session id
	 * @return true if the session was deleted
	 */
	public boolean deleteSession(int id) {

		Cinema[] cinemaList = cineplexList[currentCineplex].getCinemaList();
		for (int i = 0; i < cinemaList.length; i++)
			for (Session session : cinemaList[i].getSessionList())
				if (session.getId() == id) {
					cinemaList[i].removeSession(session);
					cinemaList[i].saveData();
					return true;
				}

		return false;

	}

	/**
	 * Change movie in given session
	 * 
	 * @param sessionId The session's id
	 * @param movie     The session's movie
	 * @return true if the session & movie were found and false if not
	 */

	public boolean updateMovie(int sessionId, Movie movie) {
		Cinema[] cinemaList = cineplexList[currentCineplex].getCinemaList();
		for (int i = 0; i < cinemaList.length; i++)
			for (Session session : cinemaList[i].getSessionList())
				if (session.getId() == sessionId) {
					session.setMovie(movie);
					cinemaList[i].saveData();

					return true;
				}
		return false;
	}

	/**
	 * Change date in given session
	 * 
	 * @param sessionId The session's id
	 * @param date      The session's date
	 * @return true if the session & date were found and false if not
	 */

	public boolean updateDate(int sessionId, Date date) {
		Cinema[] cinemaList = cineplexList[currentCineplex].getCinemaList();
		for (int i = 0; i < cinemaList.length; i++)
			for (Session session : cinemaList[i].getSessionList())
				if (session.getId() == sessionId) {
					session.setSessionDate(date);
					cinemaList[i].saveData();

					return true;
				}
		return false;
	}

	/**
	 * delete sessions which have this movie id
	 */
	public void deleteSessions(int id) {

		for (int i = 0; i < cineplexList.length; i++)
			for (int j = 0; j < cineplexList[i].getCinemaList().length; j++) {
				for (Session session : cineplexList[i].getCinemaList()[j].getSessionList())
					if (session.getMovie().getMovidId() == id)
						cineplexList[i].getCinemaList()[j].removeSession(session);

				cineplexList[i].getCinemaList()[i].saveData();
			}
	}

	/**
	 * Getting the cineplexList
	 * 
	 * @return the cineplexList
	 */

	public static Cineplex[] getCineplexList() {
		return cineplexList;
	}

	/**
	 * changing the cineplexList to a new one
	 * 
	 * @return the new cineplexList
	 */

	public static void setCineplexList(Cineplex[] cineplexList) {
		CineplexController.cineplexList = cineplexList;
	}

	/**
	 * Getting the current cineplexList index
	 * 
	 * @return the current cineplexList index
	 */

	public static int getCurrentCineplex() {
		return currentCineplex;
	}

	/**
	 * Changing the current cineplexList index to a new one
	 * 
	 * @return the new current cineplexList index
	 */

	public static void setCurrentCineplex(int currentCineplex) {
		CineplexController.currentCineplex = currentCineplex;
	}

	/**
	 * Getting the current cinema index
	 * 
	 * @return the current cinema index
	 */

	public int getCurrentCinema() {
		return currentCinema;
	}

	/**
	 * Changing the current cinema index
	 */

	public void setCurrentCinema(int currentCinema) {
		this.currentCinema = currentCinema;
	}

}
