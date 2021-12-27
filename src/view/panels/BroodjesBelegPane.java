package view.panels;

import controller.BroodjesBelegController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import model.BelegSoort;
import model.Broodje;
import model.database.BelegDatabase;
import model.database.BroodjesDatabase;

public class BroodjesBelegPane extends GridPane {

    private TableView<Broodje> table;
    private TableView<BelegSoort> tablebeleg;
    private BroodjesBelegController controller;




    public BroodjesBelegPane(BroodjesBelegController controller) throws Exception {
        this.controller = controller;

        Label label=new Label("Broodjes: ");
        Label label1=new Label("BelegSoort: ");

        this.table = new TableView<>();
        this.refreshbroodjes();
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

        tablebeleg = new TableView<>();

        this.refreshBeleg();
        TableColumn<BelegSoort, String> colTitleBeleg = new TableColumn<BelegSoort, String>("Soort BelegSoort");
        colTitleBeleg.setMinWidth(150);
        colTitleBeleg.setCellValueFactory(new PropertyValueFactory<BelegSoort, String>("name"));
        TableColumn<BelegSoort, Double> colPriceBeleg = new TableColumn<BelegSoort, Double>("Prijs");
        colPriceBeleg.setMinWidth(150);
        colPriceBeleg.setCellValueFactory(new PropertyValueFactory<BelegSoort, Double>("prijs"));
        TableColumn<BelegSoort, Integer> colYearBeleg = new TableColumn<BelegSoort, Integer>("Aantal in stock");
        colYearBeleg.setMinWidth(150);
        colYearBeleg.setCellValueFactory(new PropertyValueFactory<BelegSoort, Integer>("Aantal"));
        tablebeleg.getColumns().addAll(colTitleBeleg, colYearBeleg, colPriceBeleg);
        Paint paint= Paint.valueOf("Grey");
        this.table.setBackground(new Background(new BackgroundFill(paint,null,new Insets(0))));
        this.table.setBackground(new Background(new BackgroundFill(paint,null,new Insets(0))));


        //grid layout
        this.add(label,0,0);
        this.add(table, 0,1 , 1, 2);
        this.add(label1,0,3);
        this.add(tablebeleg, 0, 4, 1, 2);
        controller.setView(this);

    }

    public void refreshbroodjes() {
        ObservableList<Broodje> broodjes = FXCollections.observableArrayList(controller.getBroodjes().values());
        table.setItems(broodjes);
        /*for (Broodje b:broodjes) {
            System.out.println(b.getName()+" "+b.getPrijs()+" "+b.getAantal()+" "+b.getVerkocht());
        }*/
        table.refresh();
    }
    public void refreshBeleg() {
        ObservableList<BelegSoort> belegSoort = FXCollections.observableArrayList(controller.getBeleg().values());
        tablebeleg.setItems(belegSoort);
        tablebeleg.refresh();
    }
}
