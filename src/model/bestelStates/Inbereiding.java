package model.bestelStates;

import model.Bestelling;

public class Inbereiding extends BestellingState {
    public Inbereiding(Bestelling bestelling) {
        super(bestelling);
    }
    
    public void klaargemaakt(){
        getBestelling().setState(getBestelling().getKlaargemaakt());
    }
}
