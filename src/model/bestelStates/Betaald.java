package model.bestelStates;

import model.Bestelling;

public class Betaald extends BestellingState{
    public Betaald(Bestelling bestelling) {
        super(bestelling);
    }
    @Override
    public void zendNaarKeuken(){
        getBestelling().setState(getBestelling().getInWachtrij());
    }
}
