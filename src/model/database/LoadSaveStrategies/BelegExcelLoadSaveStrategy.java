package model.database.LoadSaveStrategies;

import excel.ExcelPlugin;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import model.BelegSoort;
import model.Broodje;
import utilities.ExcelLoadSaveTemplate;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class BelegExcelLoadSaveStrategy extends ExcelLoadSaveTemplate implements LoadSaveStrategy {


    @Override
    public BelegSoort makeObject(ArrayList inputs) {
        BelegSoort beleg = new BelegSoort(((String)inputs.get(0)), Double.parseDouble((String) inputs.get(1)), Integer.parseInt((String) inputs.get(2)), Integer.parseInt((String) inputs.get(3)));
        return beleg;
    }

    @Override
    public String getKey(ArrayList tokens) {
        return (String) tokens.get(0);
    }

    @Override
    public Map load() throws Exception {
        try {
            return super.load(new File("src/bestanden/beleg.xls"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Map a) throws IOException {
        try {
            super.save(a, new File("src/bestanden/beleg.xls"));
        } catch (IOException | BiffException | WriteException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected ArrayList<ArrayList<String>> addV(ArrayList list) {
        return null;
    }
}

