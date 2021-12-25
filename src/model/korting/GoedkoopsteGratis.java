package model.korting;

import model.Bestellijn;
import model.Bestelling;

public class GoedkoopsteGratis implements KortingsInterface{

    @Override
    public double BerekenKorting(Bestelling bestelling) {
        double prijs= Double.POSITIVE_INFINITY;
        if (bestelling.getPrijs()==0){
            return bestelling.getPrijs();
        }
        for (Bestellijn b: bestelling.getBestellijnen()) {
            if (prijs>b.getPrijs()){
                prijs=b.getPrijs();
            }
        }

        return bestelling.getPrijs()-prijs;
    }
}
