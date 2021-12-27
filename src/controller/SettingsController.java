package controller;

import jxl.read.biff.BiffException;
import model.BestelFacade;
import model.BestellingsEvents;
import model.database.Settings;
import model.korting.KortingEnum;
import model.observer.Observer;
import view.KitchenView;
import view.panels.SettingsPane;

import java.io.IOException;
import java.util.Locale;

public class SettingsController implements Observer {
    private BestelFacade facade;
    public SettingsPane view;

    public SettingsController(BestelFacade facade) {
        this.facade = facade;

    }

    @Override
    public void update() throws Exception {

    }
    public void setView(SettingsPane view){
        this.view=view;
    }


}
