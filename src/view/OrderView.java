package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Broodje;
import model.database.BroodjesDatabase;
import model.database.BroodjesDatabase;

import java.util.List;

public class OrderView {
	//private Stage stage = new Stage();
	private TableView<Broodje> table;


	public OrderView(){
		VBox root = new VBox();
		Stage stage = new Stage();
		Scene scene = new Scene(root, 650, 650);
		stage.setTitle("Broodjes inventory");
		stage.setX(20);
		stage.setY(20);
		stage.setScene(scene);

		stage.show();
	}



	/*stage.setTitle("ORDER VIEW");
	stage.initStyle(StageStyle.UTILITY);
	stage.setX(20);
	stage.setY(20);
	Group root = new Group();
	Scene scene = new Scene(root, 650, 650);
	stage.setScene(scene);
	stage.sizeToScene();
	stage.show();*/
}
