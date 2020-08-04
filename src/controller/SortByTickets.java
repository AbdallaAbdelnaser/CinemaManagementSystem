package controller;

import java.util.Comparator;

import Movie.Movie;

public class SortByTickets implements Comparator<Movie> {

	@Override
	public int compare(Movie m1, Movie m2) {
		return m2.getTicketCounter() - m1.getTicketCounter();
	}

}
