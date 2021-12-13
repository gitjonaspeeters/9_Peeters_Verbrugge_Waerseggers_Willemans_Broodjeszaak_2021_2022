package controller;

import model.BelegSoort;
import model.Broodje;
import model.database.BelegDatabase;
import model.database.BroodjesDatabase;
import model.observer.Observer;
import view.panels.AdminMainPane;
import view.panels.BroodjesBelegPane;

import java.util.Map;

public class BroodjesBelegController  {
    BroodjesDatabase broodjesDatabase;
    BelegDatabase belegDatabase;
    BroodjesBelegPane view;

    public BroodjesBelegController() throws Exception {
        this.broodjesDatabase = new BroodjesDatabase("XLSBroodje");
        this.belegDatabase = new BelegDatabase("XLSBeleg");
    }

    public void setView(BroodjesBelegPane view){
        this.view = view;
    }
    public Map<String, Broodje> getBroodjes(){
        return broodjesDatabase.getBroodjes();
    }

    public Map<String, BelegSoort> getBeleg(){
        return belegDatabase.getBelegSoort();
    }


}
