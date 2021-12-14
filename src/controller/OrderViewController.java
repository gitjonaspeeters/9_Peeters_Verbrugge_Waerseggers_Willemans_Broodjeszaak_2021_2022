package controller;

import model.BelegSoort;
import model.BestelFacade;
import model.database.BelegDatabase;
import model.database.BroodjesDatabase;
import model.observer.Observer;
import view.OrderView;

import java.util.Observable;

public class OrderViewController implements Observer {
    public OrderView view;
    private BestelFacade facade;

    public OrderViewController() throws Exception {
        facade=new BestelFacade();
        facade.addObserver(this);



    }

    public void setView(OrderView view){
        this.view = view;
    }

    @Override
    public void update() throws Exception {
        if (view != null) {
            view.updateStatusBelegKnoppen(facade.getVoorraadLijstBeleg());
            view.updateStatusBroodjesKnoppen(facade.getVoorraadLijstBroodje());
        }

    }
}
