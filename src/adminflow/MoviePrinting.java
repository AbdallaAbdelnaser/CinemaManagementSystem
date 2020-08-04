package adminflow;

import Movie.MovieType;
import controller.AdminController;
import controller.InputController;
import controller.MovieController;
import controller.OutputController;

public class MoviePrinting {

	/**
	 * Show movie details
	 */

	public void viewDetails() {
		System.out.print("Enter movie ID to view movie detail (0 to exit):");
		int id = InputController.readInt();
		if (id == 0)
			return;
		if (!MovieController.viewMovieDetails(id))
			System.out.println("Movie with this id doesn't exist!\n\tReturning to main menu...");

	}

	/**
	 * Search movie flow
	 */

	public void searchMovie() {
		String list[] = { "Search by movie title", "Search by movie type", "List all movie title", "Exit" };
		int choice = 0;
		while (choice != 4) {
			OutputController.printList(list);
			System.out.print("Enter Action:");
			choice = InputController.readInt(1, 4);
			if (choice == 1)
				searchTitle();
			else if (choice == 2)
				searchType();
			else if (choice == 3 && !MovieController.listMovies())
				System.out.println("\nThere aren't movies.\nReturning to menu..");

		}
	}

	/**
	 * Search for a movie by its title
	 */

	public void searchTitle() {
		System.out.print("Enter movie title:");
		String title = InputController.readLine();
		if (!MovieController.searchTitle(title, true))
			System.out.println("No search results matching given title!");
	}

	/**
	 * Search for a movie by its type
	 */

	public void searchType() {
		while (true) {
			System.out.println("Select movie type:\n\t1-2D\n\t2-3D\n\t3-Blockbuster");
			System.out.print("Enter movie type:");
			int choice = InputController.readInt(1, 3);
			if (!MovieController.searchType(
					choice == 1 ? MovieType.TWOD : choice == 2 ? MovieType.THREED : MovieType.BLOCK_BUSTER)) {
				System.out.println("No search results matching given type!");
				return;
			}
		}

	}
}
