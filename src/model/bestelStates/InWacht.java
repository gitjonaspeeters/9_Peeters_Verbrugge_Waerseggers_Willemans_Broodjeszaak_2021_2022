package model.bestelStates;

import model.Bestelling;

public class InWacht extends BestellingState{
    public InWacht(Bestelling bestelling) {
        super(bestelling);
    }

    @Override
    public void annuleren(){
        getBestelling().setState(getBestelling().getAnnuleren());
    }

    @Override
    public void nieuweBestelling(){
        getBestelling().setState(getBestelling().getInbestelling());
    }

}
