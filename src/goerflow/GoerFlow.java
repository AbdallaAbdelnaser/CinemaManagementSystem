package goerflow;

import adminflow.CineplexFlow;
import adminflow.MoviePrinting;
import controller.GoerController;
import controller.InputController;
import controller.MovieController;
import controller.OutputController;

public class GoerFlow {

	/**
	 * The goer controller
	 */
	private GoerController goerController;

	/**
	 * The goer controller
	 */

	private MoviePrinting moviePrinting;

	/**
	 * Create new GoerFlow
	 */

	public GoerFlow(GoerController goerController, MoviePrinting moviePrinting) {
		this.goerController = goerController;
		this.moviePrinting = moviePrinting;
	}

	public void showGoerList() {
		String[] list = { "Search/List movie", "View movie details", "Check seat availibility", "Book ticket",
				"View booking history", "List Top 5 movies", "Rate Movie", "Exit" };
		int choice = 0;
		while (choice != 8) {
			System.out.println("\n|=========================================|");
			System.out.println("|================|MOBLIMA|================|");
			System.out.println("|=========================================|");
			OutputController.printList(list);
			System.out.print("Select Action:");
			choice = InputController.readInt(1, 8);

			switch (choice) {
			case 1:
				moviePrinting.searchMovie();
				break;
			case 2:
				moviePrinting.viewDetails();
				break;
			case 3:
				goerController.checkAvailability();
				break;
			case 4:
				System.out.println("Here are the available movies\n");
				if (!goerController.showMovies()) {
					System.out.println("\nThere aren't movies in the NOW_SHOWING status.\nReturning to menu..");
					return;
				}
				CineplexFlow.determineCineplex();
				System.out.print("Enter movie title to view available sessions:");
				String title = InputController.readLine();
				if (!goerController.showSession(title)) {
					System.out.println("No available sessions for this cineplex! Please choose another.");
					return;
				}
				goerController.bookTicket(title);

				break;
			case 5:
				goerController.showHistory();
				break;
			case 6:
				topFive();
				break;
			case 7:
				rateMovie();
				break;
			case 8:
				System.out.println("Exiting...");

			}

		}

	}

	public void topFive() {
		System.out.print(
				"Select 1 to list top 5 movies according to ratings.\nSelect 2 to list top 5 movies according to ticket sales.\nSelect 3 to go back.\nSelect Action:");
		int choice = InputController.readInt(1, 3);
		if (choice == 1 && !goerController.sortByRating() || choice == 2 && !goerController.sortByTickets())
			System.out.println("\nThere aren't movies.\nReturning to menu..");
		if (choice == 3) {
			System.out.println("Going back...");
			return;
		}
		moviePrinting.viewDetails();

	}

	private void rateMovie() {
		System.out.print("Input your username:");
		String userName = InputController.read();
		MovieController.listMovies();
		System.out.print("Select the title of movie which you want to rate:");
		String title = InputController.readLine();
		int MovieIdx = goerController.getMovieIdx(title);
		if (MovieIdx == -1) {
			System.out.println("\nMovie with this title doesn't exist!\nReturning to menu...");
			return;
		}

		System.out.print("Input number of stars (0-5):");
		int stars = InputController.readInt(0, 5);

		System.out.print("Input additional comment:");
		String comment = InputController.readLine();
		goerController.rateMovie(MovieIdx, stars, userName, comment);

	}
}
