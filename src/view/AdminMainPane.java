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
import model.Broodje;
import model.database.BroodjesTekstReaderTest;
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
	}
        public void refresh(){
                ObservableList<Broodje> broodjes = FXCollections.observableArrayList(BroodjesTekstReaderTest.load().values());
                table.setItems( broodjes);
                table.refresh();
        }
}
