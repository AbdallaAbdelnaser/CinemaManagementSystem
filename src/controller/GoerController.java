package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

import Movie.Movie;
import Movie.MovieStatus;
import Movie.Review;
import Movie.Session;
import adminflow.CineplexFlow;
import adminflow.MoviePrinting;
import entity.Cinema;
import user.Goer;

public class GoerController {

	/**
	 * a list of goer
	 */

	private ArrayList<Goer> goerList;

	/**
	 * The movie Controller
	 */

	private MovieController movieController;

	/**
	 * The price Controller
	 */

	private PriceController priceController;

	/**
	 * The cineplex Controller
	 */

	private CineplexController cineplexController;

	/**
	 * configuration Controller
	 */

	private ConfigurationController configurationController;

	/**
	 * Create a GoerController
	 * 
	 * @param configurationController
	 * @param cineplexController
	 * @param priceController
	 * @param movieController
	 */
	public GoerController(MovieController movieController, PriceController priceController,
			CineplexController cineplexController, ConfigurationController configurationController) {
		this.movieController = movieController;
		this.priceController = priceController;
		this.cineplexController = cineplexController;
		this.configurationController = configurationController;
		goerList = new ArrayList<>();
		loadData();
	}

	/**
	 * Load Data
	 */

	public void loadData() {

		ObjectInputStream ois;
		File f = new File("files//goer.bin");

		try {
			if (!f.exists())
				saveData();
			ois = new ObjectInputStream(new FileInputStream(f));
			goerList = (ArrayList<Goer>) ois.readObject();
		} catch (ClassNotFoundException | FileNotFoundException e) {

		} catch (IOException e) {
		}
	}

	/**
	 * Save Data
	 */

	public void saveData() {
		ObjectOutputStream oos;
		File f = new File("files//goer.bin");
		try {
			oos = new ObjectOutputStream(new FileOutputStream(f));
			oos.writeObject(goerList);
			oos.close();
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		}
	}

	/**
	 * Getting the given movie title idx
	 * 
	 * @param title The movie title
	 * @return the movie index of -1 if cannot find it
	 */

	public int getMovieIdx(String title) {
		int i = 0;
		for (Movie movie : movieController.getMovieList()) {
			if (movie.getName().equals(title))
				return i;

			i++;
		}

		return -1;

	}

	/**
	 * Creat new Review for a movie with given index
	 * 
	 * @param movieIdx The movie index
	 * @param stars    The stars that are given to the movie
	 * @param userName The reviewer username
	 * @param comment  The reviewer comment
	 */

	public void rateMovie(int movieIdx, int stars, String userName, String comment) {

		movieController.getMovieList().get(movieIdx).addReview(new Review(userName, stars, comment));

	}

	/**
	 * Sort the movie list by rating and print it
	 * 
	 * @return
	 */

	public boolean sortByRating() {
		Collections.sort(movieController.getMovieList(), new SortByRating());

		if (movieController.getMovieList().size() == 0)
			return false;
		int i = 1;
		for (Movie movie : movieController.getMovieList()) {
			if (i == 6)
				break;
			System.out.println("ID:" + movie.getMovidId() + "\nTitle:" + movie.getName() + "\nRating:"
					+ ((movie.getReviews().size() < 2) ? "N/A" : movie.getOverAllRating()) + "\n-------------------");
			i++;
		}

		return true;
	}

	/**
	 * Sort the movie list by number of tickets and print it
	 * 
	 * @return
	 */

	public boolean sortByTickets() {
		if (movieController.getMovieList().size() == 0)
			return false;
		int i = 1;
		for (Movie movie : movieController.getMovieList()) {
			if (i == 6)
				break;
			System.out.println("ID:" + movie.getMovidId() + "\nTitle:" + movie.getName() + "\nSold Tickets:"
					+ movie.getTicketCounter() + "\n-------------------");
			i++;
		}

		return true;
	}

	/**
	 * Show the movies with status NOW_SHOWING
	 * 
	 * @return true if there were movies in NOW_SHOWING status
	 */

	public boolean showMovies() {
		boolean flag = false;
		for (Movie movie : movieController.getMovieList())
			if (movie.getStatus().equals(MovieStatus.NOW_SHOWING)) {
				flag = true;
				System.out.println(movie.getName());
			}

		return flag;
	}

	/**
	 * Show session for the current cineplex and print sessions for the given movie
	 * title
	 * 
	 * @param title The movie title
	 * @return true if there were sessions for the given movie title
	 */
	public boolean showSession(String title) {
		boolean flag = false;
		if (!MovieController.searchTitle(title, false))
			return false;
		for (Cinema cinema : CineplexController.getCineplexList()[CineplexController.getCurrentCineplex()]
				.getCinemaList()) {
			if (cinema.getSessionList().size() == 0)
				continue;
			System.out.println("------------------------------------------------------");
			System.out.println(
					"Cinema code:" + cinema.getCinemaCode() + "\t\t\tCinema type:" + cinema.getCinemaType().toString());
			for (Session session : cinema.getSessionList()) {
				System.out.println("\nAvailable screening times of " + title + " in this cinema:\n");
				if (session.getMovie().getName().equals(title)) {
					flag = true;
					System.out.println("\tDate: " + OutputController.printDateTime(session.getSessionDate()) + "\n");

				}
			}

		}
		System.out.println("------------------------------------------------------");
		return flag;
	}

	public void bookTicket(String title) {
		Cinema[] cinema = CineplexController.getCineplexList()[CineplexController.getCurrentCineplex()].getCinemaList();
		System.out.print("Choose your cinema code:\n\t1-" + cinema[0].getCinemaCode() + "\n\t2-"
				+ cinema[1].getCinemaCode() + "\n\t3-" + cinema[2].getCinemaCode() + "\nEnter action:");

		int choice = InputController.readInt(1, 3) - 1;
		Session session = null;
		System.out.print("Choose your viewing date and time (in format DD/MM/YYYY HH:MM):");
		Date date = InputController.readDateTime();
		for (int i = 0; i < cinema[choice].getSessionList().size(); i++)
			for (Session s : cinema[choice].getSessionList())
				if (cinema[choice].getSessionList().get(i).getSessionDate().equals(date)) {
					session = s;
					break;
				}
		if (session == null) {
			System.out.println("Session with this date doesn't exist!\n\tReturning to main menu...");
			return;
		}
		double price = 0;
		if (configurationController.isWeekend(date))
			price += priceController.getPrice("WEEKEND");
		if (configurationController.isHolidy(date))
			price += priceController.getPrice("HOLIDAY");

		price += priceController.getPrice(cinema[choice].getCinemaType().toString());
		Movie movie = null;
		for (Movie m : MovieController.getMovieList())
			if (m.getName().equals(title)) {
				movie = m;
				price += priceController.getPrice(m.getType().toString());
			}

		int validTickets = session.getValidTickets();
		if (validTickets == 0) {
			System.out.println("\nThere are'nt empty seats for this session\nReturning to main menu...");
			return;
		}
		System.out.print("Enter the amount of tickets(Max seats are " + validTickets + "):");
		int amount = InputController.readInt(1, validTickets);
		price = amount * price;

		for (int i = 1; i <= amount; i++) {
			System.out.print(
					"Enter age type for ticket " + i + ":\n\t1-Student\n\t2-Senior\n\t3-Standard\nEnter action:");
			int choose = InputController.readInt(1, 3);
			if (choose == 1)
				price += priceController.getPrice("STUDENT");
			else if (choose == 2)
				price += priceController.getPrice("SENIOR");
			else
				price += priceController.getPrice("Normal");
		}
		System.out.println("Total price is equal:" + price + " pound");
		System.out.println("Do you want to continue? Yes(0)/No(1):");
		if (InputController.readInt(0, 1) == 1) {
			System.out.println("Returning to main menu...");
			return;
		}
		System.out.println("\nX means occupied, 0 means not occupied.");
		session.printSeats();
		for (int i = 1; i <= amount; i++) {
			System.out.print("Choose seat id for ticket " + i + ":");
			while (!session.makeTrue(InputController.readInt(1, 100))) {
				System.out.println("Seat already taken!");
				System.out.print("Choose seat id for ticket " + i + ":");

			}
		}
		String tid = cinema[choice].getCinemaCode() + "" + OutputController.printDateTime(new Date()).toString();
		boolean flag = false;
		System.out.print("Enter your name:");
		String goerName = InputController.readLine();
		System.out.print("Enter your email:");
		String email = InputController.readEmail();
		for (int i = 0; i < goerList.size(); i++)
			if (goerList.get(i).getEmail().equals(email) && goerList.get(i).getName().equals(goerName)) {
				goerList.get(i).addTid(tid, movie.getName());
				flag = true;
			}
		String mobileNumber;
		if (!flag) {
			System.out.print("Enter your mobile Number:");
			mobileNumber = InputController.read();
			goerList.add(new Goer(goerName, mobileNumber, email));
			goerList.get(goerList.size() - 1).addTid(tid, movie.getName());
		}
		System.out.println("Transaction successful!");

		saveData();
		cineplexController.saveData();
	}

	public void checkAvailability() {
		if (!CineplexFlow.determineCineplex())
			return;
		int i = 1;
		System.out.println("Here are available sessions in cineplex Orchard Movies Cineplex:");
		for (Cinema cinema : CineplexController.getCineplexList()[CineplexController.getCurrentCineplex()]
				.getCinemaList()) {
			for (Session session : cinema.getSessionList())
				System.out.println("\t" + (i++) + "- Cinema:" + cinema.getCinemaCode() + "\n\tMovie:"
						+ session.getMovie().getName() + "\n\tDate:"
						+ OutputController.printDateTime(session.getSessionDate()));

		}

		System.out.print("Select session: (Cinema code, Date)\nEnter cinema code:");
		String cinemaCode = InputController.read();
		System.out.print("Enter date:");
		Date date = InputController.readDateTime();

		for (Cinema cinema : CineplexController.getCineplexList()[CineplexController.getCurrentCineplex()]
				.getCinemaList()) {
			for (Session session : cinema.getSessionList())
				if (cinema.getCinemaCode().equals(cinemaCode) && date.equals(session.getSessionDate())) {
					session.printSeats();
					return;
				}

		}
		System.out.println("Wrong Input ! ");

	}

	public void showHistory() {
		System.out.print("Enter your email:");
		String email = InputController.readEmail();
		for (Goer goer : goerList)
			if (goer.getEmail().equals(email)) {
				System.out.println("Here is booking history of " + email + ":\nGoer Name:" + goer.getName()
						+ "\nMobile Number:" + goer.getMobileNumber());
				for (Map.Entry<String, String> map : goer.getGoerHistory().entrySet()) {
					System.out.println("TID:" + map.getKey() + "\tMovie:" + map.getValue() + "\n");

				}
				return;
			}

		System.out.println("\nCannot find this goer\nReturning to main menu...");
	}
}
