package controller;

import java.util.Comparator;

import Movie.Movie;

public class SortByRating implements Comparator<Movie> {

	@Override
	public int compare(Movie o1, Movie o2) {
		return (int) (o2.getOverAllRating() - o1.getOverAllRating());
	}

}
