package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;

import Movie.Movie;
import Movie.MovieRating;
import Movie.MovieType;
import user.Admin;

public class MovieController {

	/**
	 * a list of Movies
	 */

	private static ArrayList<Movie> movieList;
	/**
	 * The cineplex Controller
	 */
	private CineplexController cineplexController;

	/**
	 * Create a list of movies
	 */

	/**
	 * 
	 * @param cineplexController
	 */

	public MovieController(CineplexController cineplexController) {
		movieList = new ArrayList<>();
		loadData();
		this.cineplexController = cineplexController;
	}

	/**
	 * Save the movie list
	 */
	public void saveData() {
		ObjectOutputStream oos;
		File f = new File("files//movies.bin");
		try {
			oos = new ObjectOutputStream(new FileOutputStream(f));
			oos.writeObject(movieList);
			oos.close();
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		}
	}

	/**
	 * Load the movie list
	 */
	public void loadData() {
		ObjectInputStream ois;
		File f = new File("files//movies.bin");

		try {
			if (!f.exists())
				saveData();
			ois = new ObjectInputStream(new FileInputStream(f));
			movieList = (ArrayList<Movie>) ois.readObject();
		} catch (ClassNotFoundException | FileNotFoundException e) {

		} catch (IOException e) {

		}
	}

	/**
	 * Check if the given movie is not in the system and add it if not
	 * 
	 * @param newMovie The new movie object
	 * @return true if this movie was added in the system and false if not
	 */

	public boolean addMovie(Movie newMovie) {

		for (Movie movie : movieList)
			if (movie.getName().equals(newMovie.getName()))
				return false;

		movieList.add(newMovie);
		return true;

	}

	/**
	 * Get the given id's index
	 */
	public int getMovieIdx(int id) {
		for (int i = 0; i < movieList.size(); i++)
			if (movieList.get(i).getMovidId() == id)
				return i;

		return -1;
	}

	/**
	 * Show details of a given movie id
	 */

	public static boolean viewMovieDetails(int id) {
		for (Movie movie : movieList)
			if (movie.getMovidId() == id) {
				System.out.println(movie);
				return true;
			}

		return false;

	}

	/**
	 * List all movies
	 */

	public static boolean listMovies() {
		if (movieList.size() == 0)
			return false;

		for (Movie movie : movieList)
			System.out.println("Movie ID:" + movie.getMovidId() + "\nTitle:" + movie.getName() + "\nRelease Date:"
					+ OutputController.printDate(movie.getReleaseDate()) + "\n-------------------");

		return true;
	}

	/**
	 * Search for a movie by its title
	 * 
	 * @param title The movie title
	 * @return true if the movie was found and false if it wasn't found
	 */

	public static boolean searchTitle(String title, boolean print) {

		for (Movie movie : movieList)
			if (movie.getName().equals(title)) {
				if (!print)
					return true;
				System.out.println("Movie ID:" + movie.getMovidId() + "\nTitle:" + movie.getName() + "\nRelease Date:"
						+ OutputController.printDate(movie.getReleaseDate()) + "\n-------------------");
				return true;
			}

		return false;
	}

	/**
	 * Search for a movie by its type and print all movies with the given type
	 * 
	 * @param movieType The movie type
	 */

	public static boolean searchType(MovieType movieType) {
		boolean flag = false;
		for (Movie movie : movieList)
			if (movie.getType().equals(movieType)) {
				System.out.println("Movie ID:" + movie.getMovidId() + "\nTitle:" + movie.getName() + "\nRelease Date:"
						+ OutputController.printDate(movie.getReleaseDate()) + "\n-------------------");
				flag = true;
			}

		if (flag)
			return true;
		return false;
	}

	/**
	 * Remove movie from movieList
	 * 
	 * @param id The movie id
	 * @return true if the movie was deleted
	 */
	public boolean removeMovie(int id) {
		for (Movie movie : movieList)
			if (movie.getMovidId() == id) {
				movieList.remove(movie);
				cineplexController.deleteSessions(id);
				return true;
			}
		return false;
	}

	/**
	 * Update name for a movie
	 * 
	 * @param id    The movie id
	 * @param title The movie title
	 */
	public void updateTitle(int id, String title) {
		movieList.get(id).setName(title);

	}

	/**
	 * Update type for a movie
	 * 
	 * @param id   The movie id
	 * @param type The user choice of movie's type
	 */

	public void updateType(int id, int type) {

		movieList.get(id).setType(type == 1 ? MovieType.TWOD : type == 2 ? MovieType.THREED : MovieType.BLOCK_BUSTER);

	}

	/**
	 * Update synopsis for a movie
	 * 
	 * @param id    The movie id
	 * @param title The movie synopsis
	 */

	public void updateSynopsis(int id, String synopsis) {
		movieList.get(id).setSynopsis(synopsis);

	}

	/**
	 * Update rating for a movie
	 * 
	 * @param id     The movie id
	 * @param choice The user choice of movie's type
	 */

	public void updateRating(int id, int choice) {

		movieList.get(id)
				.setRate(choice == 1 ? MovieRating.G
						: choice == 2 ? MovieRating.PG13
								: choice == 3 ? MovieRating.NC16 : choice == 4 ? MovieRating.M18 : MovieRating.R21);

	}

	/**
	 * Update release date for a movie
	 * 
	 * @param id      The movie id
	 * @param release The movie release date
	 */

	public void updateReleaseDate(int id, Date release) {
		movieList.get(id).setReleaseDate(release);

	}

	/**
	 * Update end date for a movie
	 * 
	 * @param id      The movie id
	 * @param release The movie end date
	 */

	public void updateEndDate(int id, Date end) {
		movieList.get(id).setEndDate(end);

	}

	/**
	 * Update cast names for a movie
	 * 
	 * @param The        movie id
	 * @param castNumber The number of casts of the movie
	 * @param castNames  The cast names
	 */

	public void updateCast(int id, int castNumber, String castNames[]) {
		movieList.get(id).setCastNames(castNames);
		movieList.get(id).setCastNumber(castNumber);
	}

	/**
	 * Update duration for a movie
	 * 
	 * @param id       The movie id
	 * @param duration The movie duration
	 */

	public void updateDuration(int id, double duration) {
		movieList.get(id).setDuration(duration);

	}

	/**
	 * Update director for a movie
	 * 
	 * @param id    The movie id
	 * @param title The movie director
	 */

	public void updatedirector(int id, String director) {
		movieList.get(id).setDirector(director);

	}

	/**
	 * returnig the list of movies
	 * 
	 * @return a list of movies
	 */
	public static ArrayList<Movie> getMovieList() {
		return movieList;
	}

	/**
	 * Changing the current movieList to a new one
	 * 
	 * @param movieList The new movie List
	 */
	public static void setMovieList(ArrayList<Movie> movieList) {
		MovieController.movieList = movieList;
	}
}
