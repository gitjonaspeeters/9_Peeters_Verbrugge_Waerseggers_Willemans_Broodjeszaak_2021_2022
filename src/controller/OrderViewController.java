package controller;

import jxl.read.biff.BiffException;
import model.BestelFacade;
import model.Bestellijn;
import model.BestellingsEvents;
import model.database.LoadSaveStrategies.LoadSaveStrategyEnum;
import model.database.Settings;
import model.korting.KortingEnum;
import model.observer.Observer;
import view.OrderView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class OrderViewController implements Observer {
    public OrderView view;
    private BestelFacade facade;

    public OrderViewController(BestelFacade facade) throws Exception {
        this.facade=facade;
        facade.addObserver(this);
        facade.schrijfInVoorEvent(BestellingsEvents.VOEG_BESTELLIJN_TOE,this);
        facade.schrijfInVoorEvent(BestellingsEvents.VOEG_BELEG_TOE,this);
        facade.schrijfInVoorEvent(BestellingsEvents.VOEG_IDENTIEKE_BESTELLING_TOE,this);
        facade.schrijfInVoorEvent(BestellingsEvents.START_NIEUWE_BESTELLING,this);
        facade.schrijfInVoorEvent(BestellingsEvents.VERWIJDER_BESTELLIJN,this);
        facade.schrijfInVoorEvent(BestellingsEvents.ANNULLEER,this);
        facade.schrijfInVoorEvent(BestellingsEvents.BETAAL,this);
        facade.schrijfInVoorEvent(BestellingsEvents.ZET_IN_WACHTRIJ,this);
    }
    public int annuleer(){
        return facade.annuleer();
    }

    public void toevoegenBroodje(String broodje){
        facade.voegBestelLijnToe(broodje);
    }

    public int startNieuweBestellingState(){
        return facade.startNieuweBestelling();
    }

    public void toevoegenBeleg(int index,String beleg){
        facade.voegBelegToeAanBestelLijn(index,beleg);
    }
    public void verwijderBestellijn(int bestellijn){
        facade.verwijderBestellijn(bestellijn);
    }

    public void voegIdentiekeBestelling(int bestellijn){
        facade.voegIdentiekeBestelLijnToe(bestellijn);
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

    public void aflsuitenBestelling() {
        facade.aflsluitenBestelling();
    }
    public double  getPrijs(){
        return facade.getPrijs();
    }
    public void setBetalen(){
        facade.betaal();
    }

    public double getPrijsNaKorting(String korting) {
        if (korting.equalsIgnoreCase("Goedkoopste broodje gratis")) return facade.getPrijsNaKorting("GOEDKOOPSTEGRATIS");
        if (korting.equalsIgnoreCase("10% korting")) return facade.getPrijsNaKorting("PERCENT_VAN_HEEL_DE_BESTELLING");
        return facade.getPrijsNaKorting("GEEN_KORTING");
    }

    public void setInwachrij(){
        facade.setInWachtrij();
    }

    public void aanpassenVoorraad() throws BiffException, IOException {
        facade.aanpassenVoorraad(Settings.getInstance().getProperty("formaat"));
    }

    public String getStandaardKorting() throws BiffException, IOException {
        return Settings.getInstance().getProperty("korting");
    }
}
