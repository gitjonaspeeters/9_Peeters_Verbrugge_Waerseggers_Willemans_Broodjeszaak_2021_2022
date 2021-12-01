package model.database;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import model.Broodje;
import model.database.LoadSaveStrategies.BroodjesTekstLoadSaveStrategy;
import model.database.LoadSaveStrategies.LoadSaveStrategy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class BroodjesDatabase {

    private TreeMap<String, Broodje> broodjes;
    private LoadSaveStrategy<String, Broodje> strategy;
    public BroodjesDatabase() {
        strategy = new BroodjesTekstLoadSaveStrategy();
        this.broodjes = new TreeMap<>();
        load();
    }

    private void load() {
        for (Broodje broodje :strategy.load().values()) {
            this.broodjes.put(broodje.getName(), broodje);
        }
    }

    public TreeMap<String, Broodje> getBroodjes(){
        return broodjes;
    }
}
