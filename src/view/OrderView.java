package view;

import controller.OrderViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import model.Bestellijn;
import model.Broodje;
import model.database.BelegDatabase;
import model.database.BroodjesDatabase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.TreeMap;

import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderStroke;

public class OrderView {
	private Stage stage = new Stage();
	ObservableList<Object> items;
	private BroodjesDatabase broodjes;
	private BelegDatabase beleg;
	private int Volgnr;
	private ArrayList<Bestellijn> bestellijn;
	private TreeMap<String,Integer> broodjesVoorraad;
	private TreeMap<String,Integer> belegVoorraad;
	private OrderViewController controller;
	private TableView table;
	private Button nieuwebestelling=new Button("Nieuwe bestelling");
	private Button zelfdebestelling = new Button("Voeg zelfde broodje toe");
	private Button verwijder =new Button("Verwijder broodje");
	private Button annuleer =new Button("Annuleer bestelling");
	private Button afsluiten= new Button("Afsluiten bestelling");
	private Button betalen =new Button("Betaal");
	private Button naarkeuken= new Button("Naar keuken");
	private ArrayList<Button> broodjesKnoppen=new ArrayList<>();
	private ArrayList<Button> belegKnoppen=new ArrayList<>();






	public  OrderView(OrderViewController controller) throws Exception {
		this.controller = controller;
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
		this.table = new TableView();
		afsluiten.setDisable(true);
		annuleer.setDisable(true);
		nieuwebestelling.setDisable(false);
		zelfdebestelling.setDisable(true);
		betalen.setDisable(true);
		naarkeuken.setDisable(true);




		Scene scene = new Scene(p1, 650, 650);
		stage.setTitle("Order view");

		//p1
		Paint paint= Paint.valueOf("Grey");
		p1.setPadding(new Insets(10));

		//p2
		p2.setPadding(new Insets(10));
		ChoiceBox<String> goedkoopstegratis = new ChoiceBox<String>();
		goedkoopstegratis.setMinWidth(350);
		goedkoopstegratis.setValue("Goedkoopste broodje gratis");
		goedkoopstegratis.show();
		Label v=new Label("Volgnr: "+Volgnr);
		nieuwebestelling.setOnAction(e -> setStateBestelling());
		p2.getChildren().addAll(nieuwebestelling,v, goedkoopstegratis);



		//p31
		controller.setView(this);
		controller.update();
		this.voegBroodjetoe(p31);


		//p32
		if (belegVoorraad!=null) {
			for (String b : this.belegVoorraad.keySet()) {
				if (belegVoorraad.get(b) > 0) {
					Button buttonbeleg = new Button(b);
					buttonbeleg.setDisable(true);
					belegKnoppen.add(buttonbeleg);
				}
			}
			p32.getChildren().addAll(belegKnoppen);
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

		TableColumn<Broodje, String> Broodje = new TableColumn<Broodje, String>("Broodje");
		Broodje.setMinWidth(180);
		Broodje.setCellValueFactory(new PropertyValueFactory<>("Broodje"));
		TableColumn<model.Broodje, Integer> Beleg = new TableColumn<>("Beleg");
		Beleg.setMinWidth(180);
		Beleg.setCellValueFactory(new PropertyValueFactory<>("Beleg"));
		table.getColumns().addAll( Broodje, Beleg);



		//p511
		Label label1 = new Label("Selecteer lijn in lijst");

		zelfdebestelling.setDisable(true);

		verwijder.setDisable(true);
		p511.setBackground(new Background(new BackgroundFill(paint,null,new Insets(0))));
		p511.setBorder(new Border(new BorderStroke(Paint.valueOf("Black"),BorderStrokeStyle.SOLID,CornerRadii.EMPTY, new BorderWidths(1))));
		p511.setPadding(new Insets(10));
		p511.getChildren().addAll(label1,zelfdebestelling,verwijder);

		//p51
		p51.getChildren().addAll(p511);
		p5.getChildren().addAll(table,p51);
		p5.setPadding(new Insets(10));
		p5.setBorder(new Border(new BorderStroke(Paint.valueOf("Black"),BorderStrokeStyle.SOLID,CornerRadii.EMPTY, new BorderWidths(1))));

		//p6
		p6.setBackground(new Background(new BackgroundFill(paint,null,new Insets(0))));
		p6.setBorder(new Border(new BorderStroke(Paint.valueOf("Black"),BorderStrokeStyle.SOLID,CornerRadii.EMPTY, new BorderWidths(1))));
		Label betalen1= new Label("Te betalen: ");
		betalen.setAlignment(Pos.CENTER_RIGHT);
		p6.getChildren().addAll(afsluiten,betalen1,betalen1,naarkeuken);
		p6.setPadding(new Insets(10));




		p1.getChildren().addAll(p2,p3,p4,p5,p6);

		stage.setX(20);
		stage.setY(20);
		stage.setScene(scene);
		stage.show();
		controller.setView(this);


	}
	public void updateBestellijnen(ArrayList<Bestellijn> list){
		this.bestellijn=list;
	}
	public void updateStatusBroodjesKnoppen(TreeMap<String,Integer> voorraadLijst){
		broodjesVoorraad=voorraadLijst;
	}
	public void updateStatusBelegKnoppen(TreeMap<String,Integer> voorraadLijst){
		belegVoorraad=voorraadLijst;
	}

	public void setStateBestelling(){
        Volgnr = controller.startnieuwebestelling();
		afsluiten.setDisable(false);
		annuleer.setDisable(false);
		nieuwebestelling.setDisable(true);
		zelfdebestelling.setDisable(false);
		betalen.setDisable(false);
		naarkeuken.setDisable(false);

		for(Button b : broodjesKnoppen){
			b.setDisable(false);
		}

		for(Button b : belegKnoppen){
			b.setDisable(false);
		}

	}

	public void voegBroodjetoe(HBox p31){
		if (broodjesVoorraad!=null) {
			for (String b : this.broodjesVoorraad.keySet()) {
				if (broodjesVoorraad.get(b) > 0) {
					Button buttonbroodjes = new Button(b);
					buttonbroodjes.setOnAction(e -> {
						controller.toevoegenBroodje(b);
						ObservableList<Bestellijn> bestellijns= FXCollections.observableArrayList(controller.getBestellijnen());
						table.setItems(bestellijns);

						table.refresh();
					});
					buttonbroodjes.setDisable(true);
					broodjesKnoppen.add(buttonbroodjes);
				}
			}
			p31.getChildren().addAll(broodjesKnoppen);
		}
	}




}
