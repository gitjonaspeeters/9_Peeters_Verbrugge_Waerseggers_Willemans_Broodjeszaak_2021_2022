package controller;

import model.BestelFacade;
import model.BestellingsEvents;
import model.observer.Observer;
import view.KitchenView;
import view.OrderView;

import java.util.ArrayList;

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
            view.setNieuweBestelling();
        }
    }

    public void setView(KitchenView view){
        this.view = view;
    }

    public int getAantalBestellingen(){
        return facade.getAantalBestellingen();
    }

    public ArrayList<Integer> volgnummer(){
        return facade.volgnummers();
    }

    public int getAantalBroodjesWachtrij(int volgnr){
        return facade.getAantalBroodjesWachtrij(volgnr);
    }
    public int getWachtrijAantalvanBroodje(int volgnr, String broodje, ArrayList beleg){
        throw new IllegalArgumentException();
        //facade.getWachtrijAantalvanBroodje(volgnr,broodje,beleg);
    }
    public int getWachtrijAantalvanBeleg(int volgnr, String beleg){
        return getWachtrijAantalvanBeleg(volgnr, beleg);
    }
    public ArrayList<String> getWachtrijBroodje(int volgnr){
        return facade.getWachtrijBroodje(volgnr);
    }
    public ArrayList<String> getBelegWachtrij(int volgnr, String broodje) {
        return  facade.getBelegWachtrij(volgnr, broodje);
    }
}
