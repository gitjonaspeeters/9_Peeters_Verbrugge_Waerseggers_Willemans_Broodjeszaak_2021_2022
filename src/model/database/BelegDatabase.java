package model.database;

import model.Broodje;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import model.BelegSoort;
import model.database.LoadSaveStrategies.BelegTekstLoadSaveStrategy;
import model.database.LoadSaveStrategies.LoadSaveStrategy;

public class BelegDatabase {

    private TreeMap<String, BelegSoort> belegSoort;
    private LoadSaveStrategy<String, BelegSoort> strategy;
    public BelegDatabase() {
        strategy = new BelegTekstLoadSaveStrategy();
        this.belegSoort = new TreeMap<>();
        load();
    }

    private void load() {
        for (BelegSoort belegs :strategy.load().values()) {
            this.belegSoort.put(belegs.getName(), belegs);
        }
    }

    public TreeMap<String, BelegSoort> getBelegSoort(){
        return belegSoort;
    }

}
