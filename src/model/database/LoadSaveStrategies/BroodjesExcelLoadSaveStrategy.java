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
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class BroodjesExcelLoadSaveStrategy extends ExcelLoadSaveTemplate implements LoadSaveStrategy {


    @Override
    public Broodje makeObject(ArrayList inputs) {
        Broodje broodje = new Broodje(((String)inputs.get(0)), Double.parseDouble((String) inputs.get(1)), Integer.parseInt((String) inputs.get(2)), Integer.parseInt((String) inputs.get(3)));
        return broodje;
    }

    @Override
    public String getKey(ArrayList tokens) {
        return (String) tokens.get(0);
    }

    @Override
    public Map load() throws Exception {
        try {
            return super.load(new File("src/bestanden/broodjes.xls"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void save(File file,Map a) throws IOException, BiffException, WriteException {
        try {
            super.save(a, new File("src/bestanden/broodjes.xls"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    protected ArrayList<ArrayList<String>> addV(ArrayList list) {
        ArrayList<ArrayList<String>> write= new ArrayList<>();
        ArrayList<Broodje> belegList= list;
        for(int i = 0; i < list.size(); i++){
            ArrayList<String> write1= new ArrayList<>();
            write1.add(belegList.get(i).getName());
            write1.add(String.valueOf(belegList.get(i).getPrijs()));
            write1.add(String.valueOf(belegList.get(i).getAantal()));
            write1.add(String.valueOf(belegList.get(i).getVerkocht()));
            write.add(i, write1);
        }
        return write;
    }

    @Override
    protected ArrayList<ArrayList<String>> addV(Collection list) {
        ArrayList<ArrayList<String>> write= new ArrayList<>();

        for(Object o:list){
            Broodje b= (Broodje) o;
            ArrayList<String> write1= new ArrayList<>();
            write1.add(b.getName());
            write1.add(String.valueOf(b.getPrijs()));
            write1.add(String.valueOf(b.getAantal()));
            write1.add(String.valueOf(b.getVerkocht()));
            write.add(write1);
        }
        return write;
    }
}
