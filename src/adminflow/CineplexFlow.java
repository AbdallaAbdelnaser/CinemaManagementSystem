package adminflow;

import java.util.Date;

import Movie.MovieStatus;
import Movie.Session;
import controller.CineplexController;
import controller.InputController;
import controller.MovieController;

public class CineplexFlow {
	/**
	 * The cineplex controller
	 */
	private CineplexController cineplexController;
	/**
	 * The movie controller
	 */
	private MovieController movieController;

	/**
	 * Create CineplexFlow and CineplexController
	 * 
	 * @param cineplexController The cineplex controller
	 * @param movieController    The movie controller
	 */
	public CineplexFlow(CineplexController cineplexController, MovieController movieController) {
		this.cineplexController = cineplexController;
		this.movieController = movieController;
	}

	/**
	 * Create new session interface
	 */
	public void createSession() {
		if (determineCineplex()) {
			cineplexController.printCinema();
			System.out.print("Enter cinema code:");
			if (!cineplexController.determineCinema(InputController.readLine())) {
				System.out.println("\nCineplex does not exist!\nReturning to menu...");
				return;
			}

			System.out.print("Enter movie id:");
			int idx = movieController.getMovieIdx(InputController.readInt());
			if (idx == -1) {
				System.out.println("\nMovie does not exist!\nReturning to menu...");
				return;
			} else if (!movieController.getMovieList().get(idx).getStatus().equals(MovieStatus.NOW_SHOWING)) {
				System.out.println("\nThis movie isn't in the now-showing status!");
				return;

			}
			System.out.print("Enter session date and time:");
			Date date = InputController.readDateTime();
			System.out.println("Session successfully created!");
			cineplexController.addSession(new Session(movieController.getMovieList().get(idx), date));

		}
	}

	/**
	 * Update session interface
	 */
	public void updateSession() {

		if (determineCineplex()) {
			cineplexController.showSession();
			System.out.print("Enter session id:");
			int id = InputController.readInt();
			System.out.print("Select attribute to update:\n1-Movie\n2-Date & Time\nEnter Action:");
			int choice = InputController.readInt(1, 2);
			boolean updated = true;
			if (choice == 1) {
				System.out.print("Enter new Movie id:");
				int movieId = movieController.getMovieIdx(InputController.readInt());
				if (movieId == -1) {
					System.out.println("\nMovie does not exist!\nReturning to menu...");
					return;
				}
				updated = cineplexController.updateMovie(id, movieController.getMovieList().get(movieId));
			} else {
				System.out.print("Enter new session date and time:");
				Date date = InputController.readDateTime();
				updated = cineplexController.updateDate(id, date);

			}
			if (!updated)
				System.out.println("Session id is wrong ! ");
			else
				System.out.println("Session successfully created!");

		}
	}

	/**
	 * Remove session interface
	 */
	public void removeSession() {
		if (determineCineplex()) {
			cineplexController.showSession();
			System.out.print("Enter session id:");
			int id = InputController.readInt();
			if (!cineplexController.deleteSession(id))
				System.out.println("\nSession does not exist!\nReturning to menu...");
			else
				System.out.println("Session successfully deleted!");

		}
	}

	/**
	 * View session interface
	 */

	public void viewSession() {
		if (determineCineplex()) {
			cineplexController.showSession();
		}
	}

	public static boolean determineCineplex() {
		System.out.print(
				"\nCineplex List:\nName:Orchard Movies Cineplex\nName:Sentosa Beach Cineplex\nName:Movie Base Cineplex\n\nEnter Cineplex Name:");
		if (!CineplexController.determineCineplex(InputController.readLine())) {
			System.out.println("\nCineplex does not exist!\nReturning to menu...");
			return false;
		}

		return true;

	}

}
