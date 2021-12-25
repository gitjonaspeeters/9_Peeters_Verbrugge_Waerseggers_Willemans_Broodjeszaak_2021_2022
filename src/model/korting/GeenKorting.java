package model.korting;

import model.Bestelling;

public class GeenKorting implements KortingsInterface{
   @Override
    public double BerekenKorting(Bestelling bestelling) {
        return bestelling.getPrijs();
    }
}
