package model.database;

import model.Broodje;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import model.BelegSoort;
import model.database.LoadSaveStrategies.BelegTekstLoadSaveStrategy;
import model.database.LoadSaveStrategies.LoadSaveStrategy;
import model.database.LoadSaveStrategies.LoadSaveStrategyFactory;

public class BelegDatabase {

    private TreeMap<String, BelegSoort> belegSoort;
    private LoadSaveStrategy<String, BelegSoort> strategy;

    public BelegDatabase(String format) throws Exception {
        strategy= LoadSaveStrategyFactory.createLoadSaveStrategy(format);
        this.belegSoort = new TreeMap<>();
        load();
    }

    private void load() throws Exception {
        System.out.println(strategy);
        for (BelegSoort belegs :strategy.load().values()) {

            this.belegSoort.put(belegs.getName(), belegs);
        }
    }
    public  BelegSoort getBeleg(String beleg){
        return belegSoort.get(beleg);
    }
    public TreeMap<String, BelegSoort> getBelegSoort(){
        return belegSoort;
    }
    public TreeMap<String,Integer> getVoorraadLijstBeleg(){
        TreeMap<String,Integer> v=new TreeMap<>();
        for (BelegSoort b:belegSoort.values()) {
            v.put(b.getName(),b.getAantal());
        }
        return v;
    }

}
