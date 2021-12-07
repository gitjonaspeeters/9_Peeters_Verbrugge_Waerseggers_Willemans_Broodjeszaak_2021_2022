package view;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import model.BelegSoort;
import model.Broodje;
import model.database.BelegDatabase;
import model.database.BroodjesDatabase;
import model.database.LoadSaveStrategies.LoadSaveStrategyFactory;
import utilities.TekstLoadSaveTemplate;
import view.panels.SandwichOverviewPane;

import java.io.IOException;

public class AdminMainPane extends BorderPane {
    private TableView<Broodje> table;
    private TableView<BelegSoort> tablebeleg;
    private BroodjesDatabase database;
    private BelegDatabase belegDatabase;


    public AdminMainPane() throws Exception {
        this.database= new BroodjesDatabase("XLSBroodje");
        this.belegDatabase = new BelegDatabase("XLSBeleg");
        TabPane tabPane = new TabPane();
        //Tab spelVerloopTab = new Tab("Spelverloop");
        SandwichOverviewPane sandwichOverviewPane = new SandwichOverviewPane();
        Tab broodjesTab = new Tab("Broodjes/BelegSoort", sandwichOverviewPane);
        Tab instellingTab = new Tab("Instellingen");
        Tab statistiekTab = new Tab("Statistieken");
        table = new TableView<>();

        Label label=new Label("Broodjes: ");
        Label label1=new Label("BelegSoort: ");


        GridPane grid = new GridPane();


        refreshbroodjes();
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
        this.setCenter(tabPane);



        broodjesTab.setContent(grid);
        tabPane.getTabs().add(broodjesTab);
        tabPane.getTabs().add(statistiekTab);
        tabPane.getTabs().add(instellingTab);

        tablebeleg = new TableView<>();
        



refreshBeleg();
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


        //grid layout
        grid.add(label,0,0);
        grid.add(table, 0,1 , 1, 2);
        grid.add(label1,0,3);
        grid.add(tablebeleg, 0, 4, 1, 2);


        this.setCenter(tabPane);
    }

    public void refreshbroodjes() {


        ObservableList<Broodje> broodjes = FXCollections.observableArrayList(database.getBroodjes().values());

        table.setItems(broodjes);
        table.refresh();
    }
    public void refreshBeleg() {

        ObservableList<BelegSoort> belegSoort = FXCollections.observableArrayList(belegDatabase.getBelegSoort().values());

        tablebeleg.setItems(belegSoort);
        tablebeleg.refresh();
    }

}
