package view.panels;

import controller.BroodjesBelegController;
import controller.StatistiekController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import model.BelegSoort;
import model.Broodje;

import java.util.ArrayList;

public class Statistiekenpane extends GridPane {
    private BarChart<NumberAxis, CategoryAxis> chartBroodje;
    private BarChart<BelegSoort, Integer> chartBeleg;
    private StatistiekController controller;


    public Statistiekenpane(StatistiekController controllerp) throws Exception {
        this.controller = controllerp;

        Label labelBroodjes = new Label("Broodjes: ");
        Label labelBeleg = new Label("BelegSoort: ");
        updateBroodjes();
        updateBeleg();
        //grid layout
        this.add(labelBroodjes, 0, 0);

        this.add(labelBeleg, 1, 0);

        controller.setView(this);

    }

    public void updateBroodjes() {

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(FXCollections.<String>
                observableArrayList("Broodje"));
        xAxis.setLabel("Broodje");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Verkocht");

        //Creating the Bar chart


        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Verkoop Statistieken Broodje");

        for (Object s : controller.getBroodjes()) {
            XYChart.Series<String, Number> series1 = new XYChart.Series<>();
            series1.setName(s.toString());

            series1.getData().add(new XYChart.Data<>("Broodje", controller.getBroodjesVerkocht(s.toString())));

            barChart.getData().add(series1);
        }
        this.add(barChart, 0, 1, 1, 3);

    }

    public void updateBeleg() {

        CategoryAxis xAxis1 = new CategoryAxis();
        xAxis1.setCategories(FXCollections.<String>
                observableArrayList("Beleg"));
        xAxis1.setLabel("Beleg");

        NumberAxis yAxis1 = new NumberAxis();
        yAxis1.setLabel("Verkocht");

        //Creating the Bar chart
        BarChart<String, Number> barChart1 = new BarChart<>(xAxis1, yAxis1);
        barChart1.setTitle("Verkoop Statistieken Beleg");

        for (Object s : controller.getBeleg()) {
            XYChart.Series<String, Number> series1 = new XYChart.Series<>();
            series1.setName(s.toString());
            series1.getData().add(new XYChart.Data<>("Beleg", controller.getBelegVerkocht(s.toString())));
            barChart1.getData().add(series1);
        }

        this.add(barChart1, 1, 1, 1, 3);
    }

    public void removeNodeByRowColumnIndex() {
        for (int i=0; i<this.getChildren().size();i++) {
            if (this.getChildren().get(i).toString().contains("Bar")){
                this.getChildren().remove(i);
            }
        }
    }



}
