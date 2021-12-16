package view.panels;


import controller.BroodjesBelegController;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;

public class AdminMainPane extends BorderPane {
    TabPane tabPane;
    private ArrayList<Tab> tabs;




    public AdminMainPane(BroodjesBelegController b) throws Exception {
        this.tabPane= new TabPane();
        this.tabs= new ArrayList<>();

        this.tabs.add(new Tab("Broodjes/BelegSoort", new BroodjesBelegPane(b)));
        this.tabs.add(new Tab("Instellingen"));
        this.tabs.add(new Tab("Statistieken"));
        for (Tab t:tabs){
            t.setClosable(false);
            tabPane.getTabs().add(t);
        }
        this.setCenter(tabPane);


    }

}
