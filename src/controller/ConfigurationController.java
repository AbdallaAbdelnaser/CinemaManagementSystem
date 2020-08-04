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

public class ConfigurationController {

	/**
	 * The holidays list
	 */

	private ArrayList<Date> holidayList;

	/**
	 * Create configurationController and load holiday Data
	 */

	public ConfigurationController() {
		holidayList = new ArrayList<>();
		loadHolidays();

	}

	/**
	 * Load Data
	 */

	public void loadHolidays() {

		ObjectInputStream ois;
		File f = new File("files//holidays.bin");

		try {
			if (!f.exists())
				saveData();
			ois = new ObjectInputStream(new FileInputStream(f));
			holidayList = (ArrayList<Date>) ois.readObject();
		} catch (ClassNotFoundException | FileNotFoundException e) {

		} catch (IOException e) {
		}
	}

	/**
	 * Save Data
	 */

	public void saveData() {
		ObjectOutputStream oos;
		File f = new File("files//holidays.bin");
		try {
			oos = new ObjectOutputStream(new FileOutputStream(f));
			oos.writeObject(holidayList);
			oos.close();
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		}
	}

	/**
	 * Put given date in holiday list
	 * 
	 * @param holidayDate The holiday date
	 */

	public void addHoliday(Date holidayDate) {
		holidayList.add(holidayDate);
	}

	/**
	 * Getting holiday list
	 */

	public ArrayList<Date> getHolidayList() {
		return holidayList;
	}

	/**
	 * Delete a holiday
	 * 
	 * @param holidayDate The holiday to be deleted
	 * @return true if the holiday was deleted
	 */
	public boolean deleteHoliday(Date holidayDate) {
		for (Date date : holidayList)
			if (date.equals(holidayDate)) {
				holidayList.remove(holidayDate);
				return true;
			}
		return false;
	}

	/**
	 * Changing the current holidayList to a new one
	 * 
	 * @param holidayList The new holidayList
	 */

	public void setHolidayList(ArrayList<Date> holidayList) {
		this.holidayList = holidayList;
	}

	public boolean isWeekend(Date date) {
		int day = date.getDay();
		return day == 5;
	}

	public boolean isHolidy(Date date) {
		for (Date holiday : holidayList)
			if (date.getDay() == holiday.getDay() && date.getMonth() == holiday.getMonth())
				return true;

		return false;
	}

}
