package model.database.LoadSaveStrategies;

import model.BelegSoort;
import utilities.TekstLoadSaveTemplate;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class BelegTekstLoadSaveStrategy extends TekstLoadSaveTemplate implements LoadSaveStrategy {

    @Override
    public Map<String, BelegSoort> load() {
        try {
            return super.load(new File("src/bestanden/beleg.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Map a) {

    }


    @Override
    public BelegSoort makeObject(String[] tokens) {
        BelegSoort beleg = new BelegSoort(tokens[0], Double.parseDouble(tokens[1]), Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));
        return beleg;
    }

    @Override
    public String getKey(String[] tokens) {
        return tokens[0];
    }
}
