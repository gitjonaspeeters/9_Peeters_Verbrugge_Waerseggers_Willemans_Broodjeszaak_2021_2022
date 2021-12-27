package controller;

import model.BelegSoort;
import model.BestelFacade;
import model.BestellingsEvents;
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

    public BroodjesBelegController(BestelFacade facade) throws Exception {
        this.broodjesDatabase = new BroodjesDatabase("XLSBroodje");
        this.belegDatabase = new BelegDatabase("XLSBeleg");
        this.facade=facade;
        facade.schrijfInVoorEvent(BestellingsEvents.ZET_IN_WACHTRIJ,this);
        this.facade.addObserver(this);
    }

    public void setView(BroodjesBelegPane view){
        this.view = view;
    }
    public Map<String, Broodje> getBroodjes(){
        return broodjesDatabase.getBroodjesFromFile();
    }

    public Map<String, BelegSoort> getBeleg(){
        return belegDatabase.getBelegSoort();
    }


    @Override
    public void update() throws Exception {
        if (view != null) {
            view.refreshBeleg();
            view.refreshbroodjes();
        }
    }
}
