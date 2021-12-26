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
import model.database.BelegDatabase;
import model.database.BroodjesDatabase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderStroke;

public class OrderView {
	private Stage stage = new Stage();
	private int Volgnr;
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

	private VBox p1= new VBox();
	private HBox p2= new HBox(25);
	private VBox p3= new VBox(10);
	private HBox p31= new HBox(20);
	private HBox p32= new HBox(20);
	private HBox p4= new HBox();
	private HBox p5= new HBox(20);
	private VBox p51= new VBox(10);
	private VBox p511= new VBox(10);
	private HBox p6= new HBox(10);
	private ChoiceBox<String> korting = new ChoiceBox<String>();
	private double prijs;
	private Label betalen1= new Label("Te betalen: " + prijs);
	private Label aantalbroodjes=new Label();








	public  OrderView(OrderViewController controller) throws Exception {
		this.controller = controller;
		this.table = new TableView();

		afsluiten.setDisable(true);
		annuleer.setDisable(true);
		nieuwebestelling.setDisable(false);
		zelfdebestelling.setDisable(true);
		betalen.setDisable(true);
		naarkeuken.setDisable(true);
		verwijder.setDisable(true);






		Scene scene = new Scene(p1, 650, 650);
		stage.setTitle("Order view");

		//p1
		Paint paint= Paint.valueOf("Grey");
		p1.setPadding(new Insets(10));

		//p2
		p2.setPadding(new Insets(10));
		this.korting = new ChoiceBox<String>();

		this.korting.getItems().add("Goedkoopste broodje gratis");
		this.korting.getItems().add("10% korting");
		this.korting.getItems().add("Geen korting");
		this.korting.setValue("Geen korting");
		this.korting.show();

		nieuwebestelling.setOnAction(e -> setStateBestelling());
		Label v=new Label("Volgnr: "+Volgnr);
		p2.getChildren().addAll(nieuwebestelling,v, this.korting);

		//p31
		controller.setView(this);
		controller.update();
		this.voegBroodjetoe(p31);


		//p32
		this.voegBelegtoe(p32);

		//p3
		p3.setPadding(new Insets(10));

		p3.getChildren().addAll(p31,p32);
		p3.setBackground(new Background(new BackgroundFill(paint,null,new Insets(0))));
		p3.setBorder(new Border(new BorderStroke(Paint.valueOf("Black"),BorderStrokeStyle.SOLID,CornerRadii.EMPTY, new BorderWidths(1))));

		//p4
		p4.getChildren().add(aantalbroodjes);
		p4.setPadding(new Insets(5));
		updateAantalBroodjes();
		//tabel


		TableColumn<String, String> Broodje = new TableColumn<String, String>("Broodje");
		Broodje.setMinWidth(180);
		Broodje.setCellValueFactory(new PropertyValueFactory<>("String"));
		TableColumn<String, Integer> Beleg = new TableColumn<>("Beleg");
		Beleg.setMinWidth(180);
		Beleg.setCellValueFactory(new PropertyValueFactory<>("ArrayList<String>"));
		table.getColumns().addAll( Broodje, Beleg);

		//p511
		Label label1 = new Label("Selecteer lijn in lijst");

		zelfdebestelling.setDisable(true);
		zelfdebestelling.setOnAction(e -> voegIdentiekeBestellingtoe());
		verwijder.setOnAction(e -> {verwijderBestellijn();});
		p511.setBackground(new Background(new BackgroundFill(paint,null,new Insets(0))));
		p511.setBorder(new Border(new BorderStroke(Paint.valueOf("Black"),BorderStrokeStyle.SOLID,CornerRadii.EMPTY, new BorderWidths(1))));
		p511.setPadding(new Insets(10));
		p511.getChildren().addAll(label1,zelfdebestelling,verwijder);

		//p51
		annuleer.setOnAction(e -> {verwijderAll();setAnnuleerBestelling();});
		p51.getChildren().addAll(p511,annuleer);

		//p5
		p5.getChildren().addAll(table,p51);
		p5.setPadding(new Insets(10));
		p5.setBorder(new Border(new BorderStroke(Paint.valueOf("Black"),BorderStrokeStyle.SOLID,CornerRadii.EMPTY, new BorderWidths(1))));

		//p6
		p6.setBackground(new Background(new BackgroundFill(paint,null,new Insets(0))));
		p6.setBorder(new Border(new BorderStroke(Paint.valueOf("Black"),BorderStrokeStyle.SOLID,CornerRadii.EMPTY, new BorderWidths(1))));
		afsluiten.setOnAction(e -> setAfsluitenBestelling());

		betalen.setAlignment(Pos.CENTER_RIGHT);
		betalen.setOnAction(e -> setBetalen());
		naarkeuken.setOnAction(e -> zendNaarKeuken());
		p6.getChildren().addAll(afsluiten,betalen1,betalen,naarkeuken);
		p6.setPadding(new Insets(10));

		p1.getChildren().addAll(p2,p3,p4,p5,p6);

		stage.setX(20);
		stage.setY(20);
		stage.setScene(scene);
		stage.show();
		controller.setView(this);


	}

	private void setBetalen() {
		annuleer.setDisable(true);
		naarkeuken.setDisable(false);
		betalen.setDisable(true);
		betalen1.setText("Te betalen: 0.0");
		controller.setBetalen();
	}


	public void updateStatusBroodjesKnoppen(TreeMap<String,Integer> voorraadLijst){
		broodjesVoorraad=voorraadLijst;
	}
	public void updateStatusBelegKnoppen(TreeMap<String,Integer> voorraadLijst){
		belegVoorraad=voorraadLijst;
	}

	public void setStateBestelling(){
        Volgnr= controller.startNieuweBestellingState();
		updateVolgnr();
		afsluiten.setDisable(false);
		annuleer.setDisable(false);
		nieuwebestelling.setDisable(true);
		zelfdebestelling.setDisable(false);
		betalen.setDisable(true);
		naarkeuken.setDisable(true);
		verwijder.setDisable(false);
		korting.setDisable(false);
		zetJuisteBroodjesBelegKnoppenAan();
	}
	public void updateVolgnr(){
		p2.getChildren().remove(1);
		Label v=new Label("Volgnr: "+Volgnr);
		p2.getChildren().add(1,v);


	}

	public void setAnnuleerBestelling(){
		Volgnr= controller.annuleer();
		updateVolgnr();
		afsluiten.setDisable(true);
		annuleer.setDisable(true);
		nieuwebestelling.setDisable(false);
		zelfdebestelling.setDisable(true);
		betalen.setDisable(true);
		naarkeuken.setDisable(true);
		verwijder.setDisable(true);
		korting.setDisable(true);
		zetJuisteBroodjesBelegKnoppenAan();
		for(Button b : belegKnoppen){
			b.setDisable(true);
		}
		for(Button b : broodjesKnoppen){
			b.setDisable(true);
		}
	}


	public void setAfsluitenBestelling(){
		prijs = controller.getPrijsNaKorting(korting.getValue());

		double p=prijs*100;
		p=Math.round(p);
		p=p/100;
		betalen1 = new Label("Te betalen: €" + p);
		p6.getChildren().remove(1);
		p6.getChildren().add(1,betalen1);
		controller.aflsuitenBestelling();

		korting.setDisable(true);
		afsluiten.setDisable(true);
		annuleer.setDisable(false);
		nieuwebestelling.setDisable(true);
		zelfdebestelling.setDisable(true);
		betalen.setDisable(false);
		naarkeuken.setDisable(true);
		verwijder.setDisable(true);
		zetJuisteBroodjesBelegKnoppenAan();
		for(Button b : belegKnoppen){
			b.setDisable(true);
		}
		for(Button b : broodjesKnoppen){
			b.setDisable(true);
		}
		table.getItems().clear();

	}
	public void verwijderAll(){
		while (table.getItems().size()>0)verwijderBestellijn();
	}
	public void zendNaarKeuken(){
		controller.setInwachrij();
		afsluiten.setDisable(true);
		annuleer.setDisable(true);
		nieuwebestelling.setDisable(false);
		zelfdebestelling.setDisable(true);
		betalen.setDisable(true);
		naarkeuken.setDisable(true);
		verwijder.setDisable(true);


	}
	public void voegIdentiekeBestellingtoe(){
		int best = kiesBestellijn();
		controller.voegIdentiekeBestelling(best);
		zetJuisteBroodjesBelegKnoppenAan();
		refreshTabel();
		updateAantalBroodjes();

	}

	public void verwijderBestellijn(){
		int best= kiesBestellijn();
		controller.verwijderBestellijn(best);
		zetJuisteBroodjesBelegKnoppenAan();
		refreshTabel();
		updateAantalBroodjes();
	}

	public void voegBroodjetoe(HBox p31){
		if (broodjesVoorraad!=null) {
			for (String b : this.broodjesVoorraad.keySet()) {
				if (broodjesVoorraad.get(b) > 0) {
					Button buttonbroodjes = new Button(b);
					buttonbroodjes.setOnAction(e -> {
						controller.toevoegenBroodje(b);
						refreshTabel();
						updateAantalBroodjes();
						zetJuisteBroodjesBelegKnoppenAan();
					});
					buttonbroodjes.setDisable(true);
					broodjesKnoppen.add(buttonbroodjes);
				}
			}
			p31.getChildren().addAll(broodjesKnoppen);
		}
	}

	public void voegBelegtoe(HBox p32){
		if (belegVoorraad!=null) {
			for (String b : this.belegVoorraad.keySet()) {
				if (belegVoorraad.get(b) > 0) {
					Button buttonbeleg = new Button(b);
					buttonbeleg.setOnAction(e -> {
						int best=kiesBestellijn();
						controller.toevoegenBeleg(best,b);
						refreshTabel();
						zetJuisteBroodjesBelegKnoppenAan();
					});
					buttonbeleg.setDisable(true);
					belegKnoppen.add(buttonbeleg);
				}
			}
			p32.getChildren().addAll(belegKnoppen);
		}
	}
	public int kiesBestellijn(){
		if (controller.getBestellijnen()==null||controller.getBestellijnen().size()==0) throw new IllegalStateException("Er zijn nog geen bestellijnen");
		int best= table.getSelectionModel().getSelectedIndex();
		if (table.getSelectionModel().getSelectedItem()==null){
			best=controller.getBestellijnen().keySet().toArray().length-1;
		}
		return best;
	}

	public void zetJuisteBroodjesBelegKnoppenAan(){
		for (Button b:broodjesKnoppen){
			if (broodjesVoorraad.get(b.getText())>0){
				b.setDisable(false);
			}else {
				b.setDisable(true);
			}
		}
		for (Button b:belegKnoppen){
			if (belegVoorraad.get(b.getText())>0&&controller.getBestellijnen()!=null&&controller.getBestellijnen().size()>0){
				b.setDisable(false);
			}else {
				b.setDisable(true);
			}
		}
	}
	public void refreshTabel(){
		ObservableList<Map<String,ArrayList<String>>> bestellijns= FXCollections.observableArrayList(controller.getBestellijnen());
		System.out.println(bestellijns);
		table.setItems(bestellijns);
		table.refresh();
	}
	public void updateAantalBroodjes(){
		try {
			aantalbroodjes.setText("AantalBroodjes: "+controller.getBestellijnen().size());
		}catch (Exception e){
			aantalbroodjes.setText("AantalBroodjes: 0");
		}
	}

}
