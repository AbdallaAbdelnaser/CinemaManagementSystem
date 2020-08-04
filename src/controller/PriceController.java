package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import user.Admin;

public class PriceController {

	/**
	 * The prices list
	 */

	private Map<String, Double> pricesList;

	/**
	 * Create PriceController and load data
	 */

	public PriceController() {
		pricesList = new HashMap<String, Double>();
		loadPrices();
	}

	/**
	 * Save data
	 */
	public void savePrices() {

		File priceData = new File("files//prices.bin");

		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(priceData));
			oos.writeObject(pricesList);
			oos.close();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}

	}

	/**
	 * Load data
	 */

	@SuppressWarnings("unchecked")
	public void loadPrices() {
		File priceData = new File("files//prices.bin");
		try {

			if (!priceData.exists()) {
				fillData();
				priceData.createNewFile();
				savePrices();
			}
			ObjectInputStream ois;

			ois = new ObjectInputStream(new FileInputStream(priceData));
			pricesList = (Map<String, Double>) ois.readObject();
			ois.close();
		} catch (FileNotFoundException | ClassNotFoundException e) {
		} catch (IOException e) {
		}

	}

	/**
	 * Initialize the priceList
	 */

	private void fillData() {
		pricesList.put("TWOD", 0.0);
		pricesList.put("THREED", 3.0);
		pricesList.put("BLOCK_BUSTER", 3.0);
		pricesList.put("PREMIUM", 1.0);
		pricesList.put("STANDARD", 0.0);
		pricesList.put("STUDENT", 8.0);
		pricesList.put("HOLIDAY", 12.0);
		pricesList.put("Normal", 10.0);
		pricesList.put("SENIOR", 6.0);
		pricesList.put("WEEKEND", 3.0);

	}

	/**
	 * Changing the movie type price to a new price
	 */

	public void updateMovieType(String movieType, double newPrice) {
		pricesList.put(movieType, newPrice);
	}

	/**
	 * Changing the cinema type price to a new price
	 */

	public void updateCinemaType(String cinemaType, double newPrice) {
		pricesList.put(cinemaType, newPrice);

	}

	/**
	 * Changing the holiday price to a new price
	 */

	public void updateHoliday(String string, double newPrice) {
		pricesList.put(string, newPrice);

	}

	/**
	 * Changing the weekend price to a new price
	 */

	public void updateWeekend(String string, double newPrice) {
		pricesList.put(string, newPrice);

	}

	/**
	 * Changing student price to a new price
	 */

	public void updateStudent(String string, double newPrice) {
		pricesList.put(string, newPrice);

	}

	/**
	 * Changing senior price to a new price
	 */

	public void updateSenior(String string, double newPrice) {
		pricesList.put(string, newPrice);

	}

	/**
	 * Getting the price List
	 * 
	 * @return the price List
	 */

	public Map<String, Double> getPricesList() {
		return pricesList;
	}

	/**
	 * Changing the price List to a new one
	 * 
	 * @return the new price List
	 */

	public void setPricesList(Map<String, Double> pricesList) {
		this.pricesList = pricesList;
	}
	
	public double getPrice(String str)
	{
		return (double) pricesList.get(str);
	}
}
