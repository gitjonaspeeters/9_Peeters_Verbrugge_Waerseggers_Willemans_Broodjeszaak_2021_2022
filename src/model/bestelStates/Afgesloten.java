package model.bestelStates;

import model.Bestelling;

public class Afgesloten extends BestellingState{
    public Afgesloten(Bestelling bestelling) {
        super(bestelling);
    }
    @Override
    public void annuleren(){
        getBestelling().setState(getBestelling().getAnnuleren());
    }
    @Override
    public void betalen(){
        getBestelling().setState(getBestelling().getBetaald());
    }
}
