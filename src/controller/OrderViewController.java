package controller;

import model.BelegSoort;
import model.BestelFacade;
import model.Bestellijn;
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
    private void setAsObserver(){
        if ()
    }

    public void toevoegenBroodje(String broodje){
        facade.voegBestelLijnToe(broodje);
    }

    public void toevoegenBeleg(String broodje,String beleg){
        facade.voegBelegToeAanBestelLijn(broodje,beleg);
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
