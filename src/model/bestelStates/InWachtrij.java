package model.bestelStates;

import model.Bestelling;

public class InWachtrij extends BestellingState {
    public InWachtrij(Bestelling bestelling) {
        super(bestelling);
    }

    @Override
    public void startBereiding() {
        getBestelling().setState(getBestelling().getInBereiding());
    }
}
