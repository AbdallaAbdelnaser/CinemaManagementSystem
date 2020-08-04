package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

public class InputController {
	/**
	 * BufferedReader object
	 */
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	/**
	 * Take input from the user and validate that its an integer value
	 * 
	 * @return User's input
	 */
	public static int readInt() {
		int choice = -1;
		while (choice < 0) {
			try {
				choice = Integer.parseInt(br.readLine());
			} catch (Exception e) {
				System.out.print("Please Enter an Integer number:");
			}

		}

		return choice;
	}

	/**
	 * Take input from the user and validate that its an integer value and in the
	 * specific range
	 * 
	 * @param low  The least value for user's input
	 * @param high The highest value for user's input
	 * @return User's input
	 */
	public static int readInt(int low, int high) {
		int choice = -1;
		while (choice < low || choice > high) {
			try {
				choice = Integer.parseInt(readLine());
				if (choice < low || choice > high)
					throw new Exception();
			} catch (Exception e) {
				System.out.print("Please Enter an Integer In the Range [" + low + "," + high + "]:");
			}
		}

		return choice;

	}

	/**
	 * Take a String from the user and validate that its not an empty
	 * 
	 * @return User's input
	 */
	public static String readLine() {
		String input = "";
		while (input.equals("")) {
			try {
				input = br.readLine();

				if (input == "")
					System.out.print("Cannot be empty, try again!:");
			} catch (IOException e) {
				System.out.print("Enter a String value:");
			}
		}
		return input;
	}

	/**
	 * Take a String from the user without spaces
	 * 
	 * @return User's input
	 */
	public static String read() {
		while (st == null || !st.hasMoreElements()) {
			st = new StringTokenizer(readLine());

		}

		return st.nextToken();
	}

	/**
	 * Take input from the user and validate that its a double value
	 * 
	 * @return User's input
	 */
	public static double readDouble() {
		double choice = -1;
		while (choice < 0) {
			try {
				choice = Double.parseDouble(br.readLine());
			} catch (Exception e) {
				System.out.print("Please Enter a double number:");
			}

		}
		return choice;

	}

	/**
	 * Take date input from the user
	 * 
	 * @return date in the fromat dd/mm/yyyy
	 */

	public static Date readDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);
		Date date = null;
		boolean found = false;
		Date currentDate = OutputController.getCurrentDate();
		while (!found) {
			String str = read();
			try {
				date = sdf.parse(str);
				if (date.before(currentDate)) {
					System.out.println("Must be realesed from now at least.");
					throw new Exception();
				}
				found = true;
			} catch (Exception e) {
				System.out.print("Must be of pattern DD/MM/YYYY!:");
			}

		}

		return date;

	}

	/**
	 * Take date with time input from the user
	 * 
	 * @return date with time in the fromat dd/mm/yyyy
	 */

	public static Date readDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
		Date date = null;
		boolean found = false;
		Date currentDate = OutputController.getCurrentDate();
		while (!found) {
			String str = readLine();
			try {
				date = sdf.parse(str);
				if (date.before(currentDate)) {
					System.out.println("This date Must be from now at least.");
					throw new Exception();
				}
				found = true;
			} catch (Exception e) {
				System.out.print("Must be of pattern DD/MM/YYYY hh:mm Õ or ã !:");
			}

		}

		return date;

	}

	/**
	 * Take input from user and validate that its a correct email pattern
	 * 
	 * @return user's input
	 */
	public static String readEmail() {
		String emailsuffix[] = { "@yahoo.com", "@gmail.com" };
		String input = "";
		boolean found = false;
		while (!found) {
			input = read();
			int symbolAt = 0, symbolDot = 0;
			for (int i = 0; i < input.length(); i++)
				if (input.charAt(i) == '@')
					symbolAt++;
				else if (input.charAt(i) == '.')
					symbolDot++;

			found = input.contains(emailsuffix[0])
					|| input.contains(emailsuffix[1]) && symbolAt == 1 && symbolDot == 1 && input.length() > 10;

			if (!found)
				System.out.print("Must Match Email Pattern! , Try again:");

		}

		return input;

	}

}
