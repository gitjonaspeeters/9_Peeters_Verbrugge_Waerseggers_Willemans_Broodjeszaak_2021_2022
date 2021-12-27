package controller;

import model.BelegSoort;
import model.BestelFacade;
import model.BestellingsEvents;
import model.Broodje;
import model.database.BelegDatabase;
import model.database.BroodjesDatabase;
import model.database.LoadSaveStrategies.LoadSaveStrategyEnum;
import model.database.Settings;
import model.observer.Observer;
import view.panels.AdminMainPane;
import view.panels.BroodjesBelegPane;

import java.util.Locale;
import java.util.Map;

public class BroodjesBelegController implements Observer  {

    BroodjesBelegPane view;
    BestelFacade facade;

    public BroodjesBelegController(BestelFacade facade) throws Exception {


        this.facade=facade;
        facade.schrijfInVoorEvent(BestellingsEvents.ZET_IN_WACHTRIJ,this);
        this.facade.addObserver(this);
    }

    public void setView(BroodjesBelegPane view){
        this.view = view;
    }
    public Map<String, Broodje> getBroodjes(){
        return facade.getBroodjesDatabase().getBroodjesFromFile();
    }

    public Map<String, BelegSoort> getBeleg(){
        return facade.getBelegDatabase().getBelegSoort();
    }


    @Override
    public void update() throws Exception {
        if (view != null) {
            view.refreshBeleg();
            view.refreshbroodjes();
        }
    }
}
