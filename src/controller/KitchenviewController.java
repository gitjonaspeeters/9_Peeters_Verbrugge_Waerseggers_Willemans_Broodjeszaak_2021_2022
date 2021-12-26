package controller;

import model.BestelFacade;
import model.BestellingsEvents;
import model.observer.Observer;
import view.KitchenView;
import view.OrderView;

public class KitchenviewController implements Observer {
    private BestelFacade facade;
    public KitchenView view;

    public KitchenviewController(BestelFacade facade) {
        this.facade = facade;
        this.facade.schrijfInVoorEvent(BestellingsEvents.ZET_IN_WACHTRIJ,this);
    }
    @Override
    public void update() throws Exception {
        if (view != null) {

        }
    }

    public void setView(KitchenView view){
        this.view = view;
    }

    public void getWachtrij(){

    }
}
