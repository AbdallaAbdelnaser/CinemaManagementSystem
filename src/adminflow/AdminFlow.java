package adminflow;

import Movie.MovieType;
import controller.AdminController;
import controller.CineplexController;
import controller.InputController;
import controller.MovieController;
import controller.OutputController;
import controller.PriceController;
import entity.CinemaType;
import controller.ConfigurationController;
import user.Admin;

public class AdminFlow {

	/**
	 * The controller of the system
	 */

	private AdminController adminController;

	/**
	 * The controller of movie interface
	 */

	private MovieListFlow movieFlow;

	/**
	 * The controller of holidays settings interface
	 */

	private ConfigurationFlow configurationFlow;

	/**
	 * The controller of cineplex interface
	 */

	private PriceController priceController;

	/**
	 * The controller of prices
	 */

	private CineplexFlow cineplexFlow;

	/**
	 * the search flow
	 */

	private MoviePrinting moviePrinting;

	/**
	 * Create adminController , movieFlow , sessionFlow , systemFlow and load the
	 * saved data
	 * 
	 * @param cineplexController
	 * @param configurationController
	 * @param priceController
	 * @param movieController
	 * @param adminController
	 */

	public AdminFlow(AdminController adminController, MovieController movieController, PriceController priceController,
			ConfigurationController configurationController, CineplexController cineplexController,
			MoviePrinting moviePrinting) {
		this.adminController = adminController;
		this.priceController = priceController;
		this.moviePrinting = moviePrinting;
		movieFlow = new MovieListFlow(movieController);
		configurationFlow = new ConfigurationFlow(configurationController);
		cineplexFlow = new CineplexFlow(cineplexController, movieController);
		adminController.saveData();
	}

	/**
	 * Show login interface
	 */

	public void login() {

		boolean found = false;
		while (!found) {
			System.out.print("Please Enter Your Email(gmail or yahoo):");
			String email = InputController.readEmail();
			System.out.print("Password:");
			String password = InputController.readLine();
			found = adminController.isAdmin(email, password);
			if (!found) {
				System.out.println("Wrong password or Email.\n1-Enter again\n2-Exit");
				int choice = InputController.readInt(1, 2);
				if (choice == 2)
					return;
			}

		}
		showAdminList();

	}

	/**
	 * Print the main admin's list
	 */

	public void showAdminList() {
		int choice = 0;
		String adminList[] = { "Create/Update/Remove movie listing", "Create/Update/Remove movie session",
				"Configure system settings", "Search/List movies", "View move details", "New admin account",
				"Log out" };
		while (choice != 7) {
			System.out.println("\n|=========================================|");
			System.out.println("|=========|MOBLIMA Administrator|=========|");
			System.out.println("|=========================================|");
			OutputController.printList(adminList);
			System.out.print("Select Action:");
			choice = InputController.readInt(1, 7);

			if (choice == 1)
				movieList();
			else if (choice == 2)
				sessionList();
			else if (choice == 3)
				systemList();
			else if (choice == 4)
				moviePrinting.searchMovie();
			else if (choice == 5)
				moviePrinting.viewDetails();
			else if (choice == 6) {
				System.out.print("Please Enter Your name:");
				String name = InputController.readLine();
				System.out.print("Please enter your email(gmail or yahoo):");
				String email = InputController.readEmail();
				System.out.print("Please Enter Your Password");
				String password = InputController.readLine();
				adminController.addAdmin(new Admin(name, email, password));
			}

		}

	}

	/**
	 * Print movie List
	 */

	private void movieList() {
		String[] movieList = { "Create Movie Listing", "Update Movie Listing", "Remove Movie Listing",
				"Return to Main Menu" };
		int choice = 0;
		while (choice != 4) {
			System.out.println("\nCreate/Update/Remove Movie:");
			OutputController.printList(movieList);
			System.out.print("Select Option:");
			choice = InputController.readInt(1, 4);
			if (choice == 1)
				movieFlow.creatMovie();
			else if (choice == 2)
				movieFlow.updateMovie();
			else if (choice == 3)
				movieFlow.removeMovie();

		}

	}

	/**
	 * Print session List
	 */

	private void sessionList() {
		String[] sessionList = { "Create Movie Session", "Update Movie Session", "Remove Movie Session",
				"View Movie Sessions", "Return to Main Menu" };
		int choice = 0;
		while (choice != 5) {
			System.out.println("\nCreate/Update/Remove session:");
			OutputController.printList(sessionList);
			System.out.print("Select Option:");
			choice = InputController.readInt(1, 5);
			if (choice == 1)
				cineplexFlow.createSession();
			else if (choice == 2)
				cineplexFlow.updateSession();
			else if (choice == 3)
				cineplexFlow.removeSession();
			else if (choice == 4)
				cineplexFlow.viewSession();

		}
	}

	/**
	 * Print system sittings List
	 */

	private void systemList() {
		String[] systemList = { "Add Holiday", "Delete Holiday", "List all holidays", "Update Movie Type Price",
				"Update Cinema Type Price", "Update Student Price", "Update Senior Citizen Price",
				"Update Standard Price", "Update Weekend Price", "Update Holiday Price", "Return to Main Menu" };
		int choice = 0, option;
		double newPrice = 0;
		while (choice != 11) {
			System.out.println("\nConfigure System Settings:");
			OutputController.printList(systemList);
			System.out.print("Select Option:");
			choice = InputController.readInt(1, 11);
			if (choice > 5 && choice != 11)
				newPrice = getPrice();
			switch (choice) {
			case 1:
				configurationFlow.addHoliday();
				break;
			case 2:
				configurationFlow.deleteHoliday();
				break;
			case 3:
				configurationFlow.ListHoliday();
				break;
			case 4:
				System.out.print("Choose Movie Type:\n\t1-2D\n\t2-3D\n\t3-Blockbuster\nOption:");
				newPrice = getPrice();
				option = InputController.readInt(1, 3);
				newPrice = getPrice();

				priceController.updateMovieType(option == 1 ? MovieType.TWOD.toString()

						: option == 2 ? MovieType.THREED.toString() : MovieType.BLOCK_BUSTER.toString(), newPrice);
				break;
			case 5:
				System.out.print("Choose Movie Type:\n\t1-Standard\n\t2-Premium\nOption:");
				option = InputController.readInt(1, 2);
				newPrice = getPrice();

				priceController.updateCinemaType(
						option == 1 ? CinemaType.STANDARD.toString() : CinemaType.PREMIUM.toString(), newPrice);
				break;

			case 6:
				priceController.updateStudent("STUDENT", newPrice);
				break;

			case 7:
				priceController.updateSenior("SENIOR", newPrice);
				break;
			case 8:
				priceController.updateStudent("Normal", newPrice);
				break;
			case 9:
				priceController.updateWeekend("WEEKEND", newPrice);
				break;
			case 10:
				priceController.updateHoliday("HOLIDAY", newPrice);
				break;
			}

		}
	}

	/**
	 * Take double from the user
	 * 
	 * @return the new price
	 */
	public double getPrice() {
		System.out.print("Enter new price:");
		return InputController.readDouble();
	}

}
