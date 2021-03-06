package model.bestelStates;

import model.BelegSoort;
import model.Bestellijn;
import model.Bestelling;
import model.Broodje;

public class InBestelling extends BestellingState{
    public InBestelling(Bestelling bestelling) {
        super(bestelling);
    }
    @Override
    public void voegBestellijnToe(Broodje broodje){

        getBestelling().voegBestelLijnToe(broodje);
    }
    @Override
    public void afsluiten(){
        getBestelling().setState(getBestelling().getAfgesloten());
    }
    @Override
    public void annuleren(){
        getBestelling().setState(getBestelling().getAnnuleren());
    }
    @Override
    public void verwijderBroodje(Bestellijn bestellijn){
        for(int i= 0; i < getBestelling().getBestellijnen().size(); i++){
            if(getBestelling().getBestellijnen().get(i) == bestellijn){
                getBestelling().verwijderBestellijn(i);
            }
        }


    }
    @Override
    public void voegIdentiekeBestellijnToe(Bestellijn bestellijn){
        getBestelling().getBestellijnen().add(bestellijn);
    }
    @Override
    public void toevoegenBeleg(BelegSoort beleg,Bestellijn bestellijn){
        for(int i=0; i< getBestelling().getBestellijnen().size(); i++){
            if(getBestelling().getBestellijnen().get(i) == bestellijn){
                getBestelling().getBestellijnen().get(i).addBeleg(beleg);
            }
        }
    }

}
