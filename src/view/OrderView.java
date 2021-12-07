package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.BelegSoort;
import model.Broodje;
import model.database.BelegDatabase;
import model.database.BroodjesDatabase;
import model.database.BroodjesDatabase;

import javafx.scene.control.Label;

import java.awt.*;
import java.awt.TextField;
import java.util.List;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderStroke;

import javax.swing.text.Style;

public class OrderView {
	//private Stage stage = new Stage();

	private BroodjesDatabase broodjes;
	private BelegDatabase beleg;
	private int Volgnr;


	public  OrderView() throws Exception {
		this.Volgnr= 1;
		this.broodjes= new BroodjesDatabase("XLSBroodje");
		this.beleg = new BelegDatabase("XLSBeleg");
		VBox p1= new VBox();
		HBox p2= new HBox(25);
		VBox p3= new VBox(10);
		HBox p31= new HBox(20);
		HBox p32= new HBox(20);
		HBox p4= new HBox();
		HBox p5= new HBox(20);
		VBox p51= new VBox(10);
		VBox p511= new VBox(10);
		HBox p6= new HBox(10);
		Stage stage = new Stage();
		Scene scene = new Scene(p1, 650, 650);
		stage.setTitle("Order view");


		//p1
		Paint paint= Paint.valueOf("Grey");

		p1.setPadding(new Insets(10));

		//p2
		p2.setPadding(new Insets(10));
		Button nieuwebestelling = new Button("Nieuwe bestelling");
		Label volgnr= new Label("Volgnr: "+this.Volgnr);
		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e)
			{
				Volgnr=Volgnr+1;
				volgnr.setText("Volgnr: "+Volgnr);

			}
		};

		// when button is pressed
		nieuwebestelling.setOnAction(event);

		ChoiceBox<String> goedkoopstegratis = new ChoiceBox<String>();
		goedkoopstegratis.setMinWidth(350);
		goedkoopstegratis.setValue("Goedkoopste broodje gratis");
		goedkoopstegratis.show();

		p2.getChildren().addAll(nieuwebestelling,volgnr, goedkoopstegratis);



		//p31

		for(Broodje broodje : this.broodjes.getBroodjes().values()){
			if(broodje.getAantal()>0){
				Button buttonbroodjes = new Button(broodje.getName());
				p31.getChildren().addAll(buttonbroodjes);
			}

		}
		//p32

		for(BelegSoort beleg: this.beleg.getBelegSoort().values()){
			if (beleg.getAantal()>0){
				Button buttonbeleg = new Button(beleg.getName());
				p32.getChildren().addAll(buttonbeleg);
			}

		}
		//p3
		p3.setPadding(new Insets(10));

		p3.getChildren().addAll(p31,p32);
		p3.setBackground(new Background(new BackgroundFill(paint,null,new Insets(0))));
		p3.setBorder(new Border(new BorderStroke(Paint.valueOf("Black"),BorderStrokeStyle.SOLID,CornerRadii.EMPTY, new BorderWidths(1))));

		//p4
		Label label = new Label("Aantal broodje:");
		p4.setPadding(new Insets(5));
		p4.getChildren().addAll(label);



		//tabel
		TableView table = new TableView<>();
		TableColumn<Broodje, String> Broodje = new TableColumn<Broodje, String>("Broodje");
		Broodje.setMinWidth(180);
		TableColumn<Broodje, String> Beleg = new TableColumn<Broodje, String>("Beleg");
		Beleg.setMinWidth(180);
		table.getColumns().addAll( Broodje, Beleg);



		//p511
		Label label1 = new Label("Selecteer lijn in lijst");
		Button voegtoe = new Button("Voeg zelfde broodje toe");
		Button verwijder = new Button("Verwijder broodje");
		p511.setBackground(new Background(new BackgroundFill(paint,null,new Insets(0))));
		p511.setBorder(new Border(new BorderStroke(Paint.valueOf("Black"),BorderStrokeStyle.SOLID,CornerRadii.EMPTY, new BorderWidths(1))));
		p511.setPadding(new Insets(10));
		p511.getChildren().addAll(label1,voegtoe,verwijder);

		//p51
		p51.getChildren().addAll(p511);
		Button annuleer = new Button("Annuleer bestelling");
		p51.getChildren().addAll(annuleer);
		//p5
		p5.getChildren().addAll(table,p51);
		p5.setPadding(new Insets(10));
		p5.setBorder(new Border(new BorderStroke(Paint.valueOf("Black"),BorderStrokeStyle.SOLID,CornerRadii.EMPTY, new BorderWidths(1))));

		//p6
		p6.setBackground(new Background(new BackgroundFill(paint,null,new Insets(0))));
		p6.setBorder(new Border(new BorderStroke(Paint.valueOf("Black"),BorderStrokeStyle.SOLID,CornerRadii.EMPTY, new BorderWidths(1))));
		Button afsluiten = new Button("AFSLUITEN BESTELLING");
		Label betalen= new Label("Te betalen: ");
		betalen.setAlignment(Pos.CENTER_RIGHT);
		Button betaal = new Button("Betaal");
		Button keuken = new Button("NAAR KEUKEN");
		p6.getChildren().addAll(afsluiten,betalen,betaal,keuken);
		p6.setPadding(new Insets(10));




		p1.getChildren().addAll(p2,p3,p4,p5,p6);
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
