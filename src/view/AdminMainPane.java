package view;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Beleg;
import model.Broodje;
import model.database.BelegDatabase;
import model.database.BroodjesDatabase;
import model.database.BroodjesDatabase;
import view.panels.SandwichOverviewPane;

import static java.time.zone.ZoneRulesProvider.refresh;

public class AdminMainPane extends BorderPane {
        private TableView<Broodje> table;
	public AdminMainPane(){

	    TabPane tabPane = new TabPane(); 	    
        //Tab spelVerloopTab = new Tab("Spelverloop");
        SandwichOverviewPane sandwichOverviewPane = new SandwichOverviewPane();
        Tab broodjesTab = new Tab("Broodjes/Beleg",sandwichOverviewPane);
        Tab instellingTab = new Tab("Instellingen");
        Tab statistiekTab = new Tab("Statistieken");
        table=new TableView<>();
        refresh();



        GridPane grid=new GridPane();

        grid.setHgap(20);
        grid.setVgap(10);

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
            System.out.println(table.getColumns());
        this.setCenter(tabPane);

        grid.add(table,0,1,3,1);

        broodjesTab.setContent(grid);
        tabPane.getTabs().add(broodjesTab);
        tabPane.getTabs().add(statistiekTab);
        tabPane.getTabs().add(instellingTab);

        tablebeleg = new TableView<>();
        refreshbeleg();




        TableColumn<Beleg, String> colTitleBeleg = new TableColumn<Beleg, String>("Soort Beleg");
        colTitleBeleg.setMinWidth(150);
        colTitleBeleg.setCellValueFactory(new PropertyValueFactory<Beleg, String>("name"));
        TableColumn<Beleg, Double> colPriceBeleg = new TableColumn<Beleg, Double>("Prijs");
        colPriceBeleg.setMinWidth(150);
        colPriceBeleg.setCellValueFactory(new PropertyValueFactory<Beleg, Double>("prijs"));
        TableColumn<Beleg, Integer> colYearBeleg = new TableColumn<Beleg, Integer>("Aantal in stock");
        colYearBeleg.setMinWidth(150);
        colYearBeleg.setCellValueFactory(new PropertyValueFactory<Beleg, Integer>("Aantal"));
        tablebeleg.getColumns().addAll(colTitleBeleg, colYearBeleg, colPriceBeleg);


        //grid layout
        grid.add(label,0,0);
        grid.add(table, 0,1 , 1, 2);
        grid.add(label1,0,3);
        grid.add(tablebeleg, 0, 4, 1, 2);


        this.setCenter(tabPane);
    }

    public void refreshbroodjes() {
        ObservableList<Broodje> broodjes = FXCollections.observableArrayList(BroodjesDatabase.load().values());
        table.setItems(broodjes);
        table.refresh();
    }

    public void refreshbeleg(){
        ObservableList<Beleg> beleg = FXCollections.observableArrayList(BelegDatabase.load().values());
        tablebeleg.setItems(beleg);
        tablebeleg.refresh();
    }
}
