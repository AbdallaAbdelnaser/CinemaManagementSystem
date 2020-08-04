package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OutputController {
	/**
	 * Print a given list
	 * 
	 * @param list the list to be printed
	 */
	public static void printList(String[] list) {
		System.out.println();
		for (int i = 1; i <= list.length; i++)
			System.out.println(i + "-" + list[i - 1]);

	}

	/**
	 * Print a given date in a specific format
	 * 
	 * @param date The date to be printed
	 * @return String represents the data in the specific format
	 */
	public static String printDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(date);

	}

	/**
	 * Print a given date with time in a specific format
	 * 
	 * @param date The date to be printed
	 * @return String represents the data in the specific format
	 */
	public static String printDateTime(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
		return sdf.format(date);

	}

	/**
	 * Print a given date with time in a specific format
	 * 
	 * @param date The date to be printed
	 * @return String represents the data in the specific format
	 */
	public static Date getCurrentDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String current = sdf.format(new Date());
		Date currentDate = null;
		try {
			currentDate = sdf.parse(current);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return currentDate;

	}
}
