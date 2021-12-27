package controller;

import com.sun.org.apache.xpath.internal.axes.AxesWalker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.Axis;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import jxl.write.Number;
import model.BestelFacade;
import model.BestellingsEvents;
import model.Broodje;
import model.observer.Observer;
import view.panels.SettingsPane;
import view.panels.Statistiekenpane;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class StatistiekController implements Observer {
    private BestelFacade facade;
    public Statistiekenpane view;

    public StatistiekController(BestelFacade facade) {
        this.facade = facade;
        this.facade.schrijfInVoorEvent(BestellingsEvents.ZET_IN_WACHTRIJ,this);

    }

    @Override
    public void update() throws Exception {
        view.removeNodeByRowColumnIndex();
        view.updateBeleg();
        view.updateBroodjes();
    }
    public void setView(Statistiekenpane view){
        this.view=view;
    }

    public ObservableList getBroodjes(){
        ObservableList<String> broodjes= FXCollections.observableArrayList();
        for (String s:facade.getVerkochtLijstBroodje().keySet()) {
            broodjes.add(s);
        }
        return broodjes;
    }
    public Integer getBroodjesVerkocht(String broodje){
        return facade.getVerkochtLijstBroodje().get(broodje);
    }
    public ObservableList getBeleg(){
        ObservableList<String> broodjes= FXCollections.observableArrayList();
        for (String s:facade.getVerkochtLijstBeleg().keySet()) {
            broodjes.add(s);
        }
        return broodjes;
    }
    public Integer getBelegVerkocht(String broodje){
        return facade.getVerkochtLijstBeleg().get(broodje);
    }

}
