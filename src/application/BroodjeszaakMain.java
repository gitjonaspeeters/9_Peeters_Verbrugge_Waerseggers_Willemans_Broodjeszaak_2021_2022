package application;
	
import controller.OrderViewController;
import javafx.application.Application;
import javafx.stage.Stage;
import view.AdminView;
import view.KitchenView;
import view.OrderView;


public class BroodjeszaakMain extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		AdminView adminView = new AdminView();
		OrderView orderView = new OrderView(new OrderViewController());

		KitchenView kitchenView = new KitchenView();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
