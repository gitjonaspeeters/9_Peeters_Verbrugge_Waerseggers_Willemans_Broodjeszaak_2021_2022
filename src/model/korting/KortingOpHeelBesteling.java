package model.korting;

import model.Bestelling;

public class KortingOpHeelBesteling implements KortingsInterface{

    @Override
    public double BerekenKorting(Bestelling bestelling) {
        return bestelling.getPrijs()*0.9;
    }
}
