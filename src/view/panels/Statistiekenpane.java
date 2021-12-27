package view.panels;

import controller.BroodjesBelegController;
import controller.StatistiekController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import model.BelegSoort;
import model.Broodje;

import java.util.ArrayList;

public class Statistiekenpane extends GridPane {
    private BarChart<NumberAxis, CategoryAxis> chartBroodje;
    private BarChart<BelegSoort,Integer> chartBeleg;
    private StatistiekController controller;




    public Statistiekenpane(StatistiekController controllerp) throws Exception {
        this.controller = controllerp;

        Label labelBroodjes=new Label("Broodjes: ");
        Label labelBeleg=new Label("BelegSoort: ");
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(FXCollections.<String>
                observableArrayList(String.valueOf(controller.getBroodjes())));
        xAxis.setLabel("Broodje");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Verkocht");

        //Creating the Bar chart
        StackedBarChart<String, Number> stackedBarChart = new StackedBarChart<>(xAxis, yAxis);

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Verkoop Statistieken");

        for (Object s:controller.getBroodjes()){
            XYChart.Series<String, Number> series1 = new XYChart.Series<>();
            series1.setName(s.toString());
            series1.getData().add(new XYChart.Data<>(s.toString(), controller.getBroodjesVerkocht(s.toString())));
            barChart.getData().add(series1);
            stackedBarChart.getData().add(series1);
        }
        //beleg chart
        CategoryAxis xAxis1 = new CategoryAxis();
        xAxis.setCategories(FXCollections.<String>
                observableArrayList(String.valueOf(controller.getBeleg())));
        xAxis.setLabel("Beleg");

        NumberAxis yAxis1 = new NumberAxis();
        yAxis.setLabel("Verkocht");

        //Creating the Bar chart
        BarChart<String, Number> barChart1 = new BarChart<>(xAxis1, yAxis1);
        barChart1.setTitle("Verkoop Statistieken Beleg");

        for (Object s:controller.getBeleg()){
            XYChart.Series<String, Number> series1 = new XYChart.Series<>();
            series1.setName(s.toString());
            series1.getData().add(new XYChart.Data<>(s.toString(), controller.getBelegVerkocht(s.toString())));
            barChart1.getData().add(series1);
        }


        //grid layout
        this.add(labelBroodjes,0,0);
        this.add(barChart, 0,1 , 1, 2);
        this.add(labelBeleg,0,3);
        this.add(barChart1, 0, 4, 1, 2);
        controller.setView(this);

    }



}
