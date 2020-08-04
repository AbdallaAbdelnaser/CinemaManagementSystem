package adminflow;

import java.util.Date;

import controller.ConfigurationController;
import controller.InputController;
import controller.OutputController;

public class ConfigurationFlow {
	/**
	 * The configuration controller
	 */
	private ConfigurationController configurationController;

	/**
	 * Create ConfigurationFlow
	 * 
	 * @param configurationController The configuration controller
	 */
	public ConfigurationFlow(ConfigurationController configurationController) {
		this.configurationController = configurationController;
	}

	/**
	 * Create Holiday
	 */

	public void addHoliday() {
		System.out.print("Enter holiday date:");
		Date holidayDate = InputController.readDate();
		System.out.println("Holiday successfully created!");
		configurationController.addHoliday(holidayDate);
		configurationController.saveData();

	}

	/**
	 * Delete Holiday
	 */

	public void deleteHoliday() {
		ListHoliday();
		System.out.print("Enter holiday date to delete:");
		Date date = InputController.readDate();
		if (!configurationController.deleteHoliday(date))
			System.out.println("Holiday does not exist!");
		else {
			System.out.println("Holiday successfully deleted!");
			configurationController.saveData();

		}
	}

	/**
	 * ListHoliday
	 */

	public void ListHoliday() {
		System.out.println("Currently declared holidays:+\n");
		for (Date date : configurationController.getHolidayList())
			System.out.println(OutputController.printDate(date));
		System.out.println();
	}

}
