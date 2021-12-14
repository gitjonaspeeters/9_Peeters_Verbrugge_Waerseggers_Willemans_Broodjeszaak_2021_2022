package controller;

import model.BelegSoort;
import model.BestelFacade;
import model.Broodje;
import model.database.BelegDatabase;
import model.database.BroodjesDatabase;
import model.observer.Observer;
import view.panels.AdminMainPane;
import view.panels.BroodjesBelegPane;

import java.util.Map;

public class BroodjesBelegController implements Observer  {
    BroodjesDatabase broodjesDatabase;
    BelegDatabase belegDatabase;
    BroodjesBelegPane view;
    BestelFacade facade;

    public BroodjesBelegController() throws Exception {
        this.broodjesDatabase = new BroodjesDatabase("XLSBroodje");
        this.belegDatabase = new BelegDatabase("XLSBeleg");
        facade=new BestelFacade();
        facade.addObserver(this);
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


    @Override
    public void update() {

    }
}
