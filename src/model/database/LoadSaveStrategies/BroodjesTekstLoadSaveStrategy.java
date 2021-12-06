package model.database.LoadSaveStrategies;

import model.BelegSoort;
import model.Broodje;
import utilities.TekstLoadSaveTemplate;

import java.io.File;
import java.io.IOException;
import java.util.Map;


public class BroodjesTekstLoadSaveStrategy extends TekstLoadSaveTemplate implements LoadSaveStrategy{


    @Override
    public  Map<String, Broodje> load() {
        try {
            return super.load(new File("src/bestanden/broodjes.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Map a) {
        try {
            super.save(a, new File("src/bestanden/broodjes.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Broodje makeObject(String[] tokens) {
        Broodje broodje = new Broodje(tokens[0], Double.parseDouble(tokens[1]), Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));
        return broodje;
    }

    @Override
    public String getKey(String[] tokens) {
        return tokens[0];
    }

    @Override
    protected String toStringEnzo(Object object) {
        String string= ((Broodje) object).getName() + ","+ ((Broodje) object).getPrijs() + ","+ ((Broodje) object).getAantal() + ","+ ((Broodje) object).getAantal();
        return string;
    }
}
