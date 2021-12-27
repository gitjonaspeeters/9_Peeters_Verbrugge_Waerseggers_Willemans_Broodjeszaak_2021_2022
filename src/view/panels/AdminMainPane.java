package view.panels;


import controller.BroodjesBelegController;
import controller.SettingsController;
import controller.StatistiekController;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;

public class AdminMainPane extends BorderPane {
    TabPane tabPane;
    private ArrayList<Tab> tabs;




    public AdminMainPane(BroodjesBelegController b, SettingsController s , StatistiekController r) throws Exception {
        this.tabPane= new TabPane();
        this.tabs= new ArrayList<>();

        this.tabs.add(new Tab("Broodjes/BelegSoort", new BroodjesBelegPane(b)));
        this.tabs.add(new Tab("Instellingen", new SettingsPane(s)));
        this.tabs.add(new Tab("Statistieken", new Statistiekenpane(r)));
        for (Tab t:tabs){
            t.setClosable(false);
            tabPane.getTabs().add(t);
        }
        this.setCenter(tabPane);


    }

}
