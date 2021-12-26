package application;
	
import controller.BroodjesBelegController;
import controller.KitchenviewController;
import controller.OrderViewController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.BestelFacade;
import view.AdminView;
import view.KitchenView;
import view.OrderView;


public class BroodjeszaakMain extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		BestelFacade facade=new BestelFacade();
		AdminView adminView = new AdminView(new BroodjesBelegController(facade));
		OrderView orderView = new OrderView(new OrderViewController(facade));

		KitchenView kitchenView = new KitchenView(new KitchenviewController(facade));
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
