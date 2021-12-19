package model.bestelStates;

import model.Bestelling;

public class Annuleren extends BestellingState{
    public Annuleren(Bestelling bestelling) {
        super(bestelling);
    }
    @Override
    public void nieuweBestelling(){
        getBestelling().setState(getBestelling().getInbestelling());
    }
}
