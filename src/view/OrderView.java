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
import model.database.BroodjesTekstReaderTest;

import java.util.List;

public class OrderView {
	//private Stage stage = new Stage();
	private TableView<Broodje> table;

		
	public OrderView(){
		VBox root = new VBox();
		root.setSpacing(10);
		root.setPadding(new Insets(10, 10, 10, 10));
		Label lblHeading = new Label("Broodjeslijst");
		lblHeading.setFont(new Font("Arial", 20));
		table = new TableView<Broodje>();
		refresh();
		TableColumn<Broodje, String> colTitle = new TableColumn<Broodje, String>("Soort Broodje");
		colTitle.setMinWidth(150);
		colTitle.setCellValueFactory(new PropertyValueFactory<Broodje, String>("name"));
		TableColumn<Broodje, Double> colPrice = new TableColumn<Broodje, Double>("Prijs");
		colPrice.setMinWidth(150);
		colPrice.setCellValueFactory(new PropertyValueFactory<Broodje, Double>("prijs"));
		TableColumn<Broodje, Integer> colYear = new TableColumn<Broodje, Integer>("Aantal in stock");
		colYear.setMinWidth(150);
		colYear.setCellValueFactory(new PropertyValueFactory<Broodje, Integer>("Aantal"));
		table.getColumns().addAll(colTitle, colYear, colPrice);
		root.getChildren().addAll(lblHeading, table);
		Stage stage = new Stage();
		Scene scene = new Scene(root, 650, 650);
		stage.setTitle("Broodjes inventory");
		stage.setX(20);
		stage.setY(20);
		stage.setScene(scene);

		stage.show();
	}

	public void refresh(){

		System.out.println(BroodjesTekstReaderTest.load().values());

		ObservableList<Broodje> broodjes = FXCollections.observableArrayList(BroodjesTekstReaderTest.load().values());
		table.setItems( broodjes);
		table.refresh();
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
