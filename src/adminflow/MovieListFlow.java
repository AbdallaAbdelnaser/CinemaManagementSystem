package adminflow;

import java.text.SimpleDateFormat;
import java.util.Date;

import Movie.Movie;
import Movie.MovieRating;
import Movie.MovieStatus;
import Movie.MovieType;
import controller.AdminController;
import controller.InputController;
import controller.MovieController;
import controller.OutputController;
import user.Admin;

public class MovieListFlow {
	private MovieController movieController;

	public MovieListFlow(MovieController movieController) {
		this.movieController = movieController;
	}

	/**
	 * Create new movie
	 */
	public void creatMovie() {

		System.out.print("Enter movie title:");
		String movieName = InputController.readLine();
		System.out.print("Possible movie types:\n\t1-2D\n\t2-3D\n\t3-Blockbuster\nSelect movie type (number):");
		int choice = InputController.readInt(1, 3);
		MovieType movieType = null;
		if (choice == 1)
			movieType = movieType.TWOD;
		else if (choice == 2)
			movieType = movieType.THREED;
		else
			movieType = movieType.BLOCK_BUSTER;

		System.out.print("Enter movie synopsis:");
		String synopsis = InputController.readLine();
		System.out.print(
				"Possible movie rating:\n\t1-G\n\t2-PG13\n\t3-NC16\n\t4-M18\n\t5-R21\nSelect movie rating (number):");
		choice = InputController.readInt(1, 5);
		MovieRating movieRating = null;

		if (choice == 1)
			movieRating = movieRating.G;
		else if (choice == 2)
			movieRating = movieRating.PG13;
		else if (choice == 3)
			movieRating = movieRating.NC16;
		else if (choice == 4)
			movieRating = movieRating.M18;
		else
			movieRating = movieRating.R21;

		System.out.print("Enter movie duration:");
		double duration = InputController.readDouble();
		System.out.print("Enter movie release date (DD/MM/YYYY):");
		Date releaseDate = InputController.readDate();
		System.out.print("Enter movie end date (DD/MM/YYYY):");
		Date endDate = InputController.readDate();
		System.out.print("Enter Director Name:");
		String movieDirector = InputController.readLine();

		System.out.print("Enter number of casts (at least 2):");
		int castNumber = InputController.readInt(2, 50);
		String castNames[] = new String[castNumber];
		for (int i = 0; i < castNumber; i++) {
			System.out.print("Enter name of cast " + (i + 1) + ":");
			castNames[i] = InputController.readLine();
		}
		MovieStatus movieStatus = null;
		Date currentDate = OutputController.getCurrentDate();
		if (releaseDate.after(currentDate))
			movieStatus = MovieStatus.COMING_SOON;
		else if (releaseDate.equals(currentDate))
			movieStatus = MovieStatus.NOW_SHOWING;

		if (movieController.addMovie(new Movie(movieName, synopsis, movieDirector, castNumber, castNames, movieRating,
				movieType, movieStatus, duration, releaseDate, endDate)))
			System.out.println("Movie created....");
		else
			System.out.println("Movie is already existed");

		movieController.saveData();
	}

	/**
	 * Update movie flow
	 */

	public void updateMovie() {
		System.out.println();
		if (!MovieController.listMovies()) {
			System.out.println("There aren't movies.\nReturning to menu..");
			return;

		}
		System.out.println("\nUpdating movie...\nSelect movie to be updated:");

		System.out.print("Movie id:");
		int choice = 0, idx = movieController.getMovieIdx(InputController.readInt());
		if (idx == -1) {
			System.out.println("Movie Id does not exist\n!Returning to menu...");
			return;
		}
		String list[] = { "Title", "Type", "Synopsis", "Rating", "Duration", "Movie Release date",
				"End of Showing date", "Director", "Cast", "Back" };
		while (choice != 10) {
			OutputController.printList(list);
			System.out.print("Enter Option:");
			choice = InputController.readInt(1, 10);

			switch (choice) {
			case 1:
				System.out.print("Enter new Title:");
				movieController.updateTitle(idx, InputController.readLine());
				break;
			case 2:
				System.out.print("Possible movie types:\n1-2D\n2-3D\n3-Blockbuster\nEnter your choice:");
				movieController.updateType(idx, InputController.readInt(1, 3));
				break;
			case 3:
				System.out.print("Enter new Synopsis");
				movieController.updateSynopsis(idx, InputController.readLine());
				break;

			case 4:
				System.out.print("Enter new Rating:\n\t1-G\n\t2-PG13\n\t3-NC16\n\t4-M18\n\t5-R21\nSelect Action:");
				movieController.updateRating(idx, InputController.readInt(1, 5));
				break;

			case 5:
				System.out.print("Enter new Duration:");
				movieController.updateDuration(idx, InputController.readDouble());
				break;

			case 6:
				System.out.print("Enter new Movie Release date (DD/MM/YYYY):");

				Date currentDate = OutputController.getCurrentDate();
				Date releaseDate = InputController.readDate();
				MovieStatus movieStatus = null;
				if (releaseDate.after(currentDate))
					movieStatus = MovieStatus.COMING_SOON;
				else if (releaseDate.equals(currentDate))
					movieStatus = MovieStatus.NOW_SHOWING;

				movieController.updateReleaseDate(idx, releaseDate);
				movieController.getMovieList().get(idx).setStatus(movieStatus);
				break;

			case 7:
				System.out.print("Enter new End of Showing date (DD/MM/YYYY)");
				movieController.updateEndDate(idx, InputController.readDate());
				break;

			case 8:
				System.out.print("Enter new Director:");
				movieController.updatedirector(idx, InputController.readLine());
				break;

			case 9:
				System.out.print("Enter number of new cast members (at least 2):");
				int castNumber = InputController.readInt(2, 50);
				String castNames[] = new String[castNumber];
				for (int i = 0; i < castNumber; i++) {
					System.out.print("Enter name of cast " + (i + 1) + ":");
					castNames[i] = InputController.readLine();
				}
				movieController.updateCast(idx, castNumber, castNames);
				break;

			default:
				System.out.println("Wrong input");

			}

		}
		movieController.saveData();

	}

	/**
	 * Remove movie flow
	 */

	public void removeMovie() {

		if (!MovieController.listMovies()) {
			System.out.println("\nThere aren't movies.\nReturning to menu..");
			return;

		}
		System.out.println("\nDeleting movie...\nSelect movie to be deleted:");

		System.out.print("Movie id:");
		int id = InputController.readInt();
		if (!movieController.removeMovie(id))
			System.out.println("Movie does not exist!\nReturning to menu..");
		else
			System.out.println("Movie successfully deleted!");
		movieController.saveData();

	}

}
