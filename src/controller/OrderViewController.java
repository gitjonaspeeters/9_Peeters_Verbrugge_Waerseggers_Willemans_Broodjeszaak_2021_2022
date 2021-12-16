package controller;

import model.BestelFacade;
import model.Bestellijn;
import model.BestellingsEvents;
import model.observer.Observer;
import view.OrderView;

import java.util.ArrayList;

public class OrderViewController implements Observer {
    public OrderView view;
    private BestelFacade facade;

    public OrderViewController(BestelFacade facade) throws Exception {
        this.facade=facade;
        facade.addObserver(this);
        facade.schrijfInVoorEvent(BestellingsEvents.VOEG_BESTELLIJN_TOE,this);
    }

    public int startnieuwebestelling(){
        return facade.startNieuweBestelling();
    }
    public void toevoegenBroodje(String broodje){
        facade.voegBestelLijnToe(broodje);
    }

    public void toevoegenBeleg(Bestellijn bestellijn,String beleg){
        facade.voegBelegToeAanBestelLijn(bestellijn,beleg);
    }

    public ArrayList<Bestellijn> getBestellijnen(){
        return  facade.getBestelLijnen();
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
