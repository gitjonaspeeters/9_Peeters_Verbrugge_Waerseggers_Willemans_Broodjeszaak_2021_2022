package model.database.LoadSaveStrategies;

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
}
