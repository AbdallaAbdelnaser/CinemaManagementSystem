import adminflow.AdminFlow;
import adminflow.MoviePrinting;
import controller.AdminController;
import controller.CineplexController;
import controller.ConfigurationController;
import controller.GoerController;
import controller.MovieController;
import controller.PriceController;
import goerflow.GoerFlow;

public class ApplicationRunning {

	public static void main(String[] args) {
		CineplexController cineplexController = new CineplexController();
		MovieController movieController = new MovieController(cineplexController);
		AdminController adminController = new AdminController();
		PriceController priceController = new PriceController();
		MoviePrinting moviePrinting = new MoviePrinting();
		ConfigurationController configurationController = new ConfigurationController();

		GoerController goerController = new GoerController(movieController, priceController, cineplexController,
				configurationController);

		AdminFlow adminFlow = new AdminFlow(adminController, movieController, priceController, configurationController,
				cineplexController, moviePrinting);

		GoerFlow goerFlow = new GoerFlow(goerController, moviePrinting);
		UserFlow userFlow = new UserFlow(adminFlow, goerFlow);
		userFlow.showMainList();
	}

}
