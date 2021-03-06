package model.bestelStates;

import model.BelegSoort;
import model.Bestellijn;
import model.Bestelling;
import model.Broodje;

public abstract class BestellingState {
    private Bestelling bestelling;

    public BestellingState(Bestelling bestelling) {
        this.bestelling = bestelling;
    }

    public Bestelling getBestelling() {
        return bestelling;
    }
    public void nieuweBestelling() {
        throw new IllegalArgumentException("Je kan geen bestelling starten ");
    }
    public void voegBestellijnToe(Broodje broodje) {
        throw new IllegalArgumentException("Je kan deze bestellijn niet toevoegen");
    }
    public void annuleren() {throw new IllegalArgumentException("Je kan deze bestelling niet annuleren ");
    }

    public void voegIdentiekeBestellijnToe(Bestellijn bestellijn){
        throw new IllegalArgumentException("Je kan deze identieke bestellijn niet toevoegen");
    }

    public void afsluiten(){
        throw new IllegalArgumentException("Je kan deze bestelling niet annuleren");
    }

    public void verwijderBroodje(Bestellijn bestellijn){
        throw new IllegalArgumentException("Je kan dit broodje niet verwijderen");
    }

    public void toevoegenBeleg(BelegSoort beleg, Bestellijn bestellijn){
        throw new IllegalArgumentException("Je kan geen beleg toevoegen");
    }

    public void betalen(){
        throw new IllegalArgumentException("Je kan nog niet betalen");
    }

    public void zendNaarKeuken(){
        throw new IllegalArgumentException("Kan bestelling nog niet naar keuken sturen");
    }

    public void startBereiding(){
        throw new IllegalArgumentException("Kan bestelling nog niet bereiden");
    }
    public void klaargemaakt(){throw new IllegalArgumentException("De bestelling is nog niet klaargemaakt");}
}
