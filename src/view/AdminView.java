package view;

import controller.BroodjesBelegController;
import controller.SettingsController;
import controller.StatistiekController;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.panels.AdminMainPane;

public class AdminView {
	private Stage stage = new Stage();		
		
	public AdminView(BroodjesBelegController b, SettingsController s , StatistiekController r) throws Exception {
		stage.setTitle("ADMIN VIEW");

		stage.setX(680);
		stage.setY(20);
		Group root = new Group();
		Scene scene = new Scene(root, 650, 400);
		BorderPane borderPane = new AdminMainPane(b,s,r);
		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());
		root.getChildren().add(borderPane);



		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();		
	}
}
