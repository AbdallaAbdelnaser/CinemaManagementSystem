
import adminflow.AdminFlow;
import controller.InputController;
import controller.OutputController;
import goerflow.GoerFlow;

public class UserFlow {

	/**
	 * The controller of admin interface
	 */

	AdminFlow adminFlow;

	/**
	 * The controller of goer interface
	 */

	GoerFlow goerFlow;

	/**
	 * Create UserFlow and initialize adminFlow & goerFlow
	 * 
	 * @param adminFlow The flow controller of admin
	 * @param goerFlow  The flow controller of goer
	 */

	public UserFlow(AdminFlow adminFlow, GoerFlow goerFlow) {
		this.adminFlow = adminFlow;
		this.goerFlow = goerFlow;
	}

	/**
	 * Print the main list
	 */

	public void showMainList() {
		String list[] = { "Login as Admin", "Movie Goer", "Exit" };
		int choice = 0;
		while (choice != 3) {
			System.out.println("|======================================|");
			System.out.println("|=========|Welcome to MOBLIMA|=========|");
			System.out.println("|======================================|");
			OutputController.printList(list);
			System.out.print("Selcet Action:");
			choice = InputController.readInt(1, 3);
			if (choice == 1)
				adminFlow.login();
			if (choice == 2)
				goerFlow.showGoerList();
			if (choice == 3)
				System.out.print("Exiting MOBLIMA");

		}
	}

}
