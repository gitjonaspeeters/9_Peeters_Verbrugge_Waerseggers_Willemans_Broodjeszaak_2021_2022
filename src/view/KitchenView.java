package view;

import controller.KitchenviewController;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

import java.awt.*;

public class KitchenView {
	private Stage stage = new Stage();
	private VBox p1= new VBox();
	private HBox p2 = new HBox();
	private  VBox p3 = new VBox();
	private KitchenviewController controller;
	private Button volgende = new Button("Volgende Bestelling");
	private Button afgewerkt = new Button("Bestelling afgewerkt");
	private int inwachtij;
	private int volgnr;
	private int aantalbroodjes;
	private int aantalbroodjesvanzelfde;
	private int aantalbelegvanzelfde;
	private Label labelwachtrij= new Label("Bestellingen in wachtrij:" + inwachtij);
	private Label labelbestelling= new Label("Volgnummer bestelling:" + volgnr + " - Aantal broodjes:");

	
	public KitchenView(){			
		stage.setTitle("KITCHEN VIEW");
		stage.initStyle(StageStyle.UTILITY);
		stage.setX(680);
		stage.setY(470);

		p1.getChildren().addAll(p2,labelwachtrij,p3);
		p1.setPadding(new Insets(10));
		p2.getChildren().addAll(volgende,afgewerkt);
		p2.setPadding(new Insets(10));
		Paint paint= Paint.valueOf("Grey");
		p2.setBackground(new Background(new BackgroundFill(paint,null,new Insets(0))));
		p2.setBorder(new Border(new BorderStroke(Paint.valueOf("Black"),BorderStrokeStyle.SOLID,CornerRadii.EMPTY, new BorderWidths(1))));
		p3.getChildren().addAll(labelbestelling);
		p3.setPadding(new Insets(10));


		Scene scene = new Scene(p1, 650, 200);


		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();
		this.controller.setView(this);
	}
}
