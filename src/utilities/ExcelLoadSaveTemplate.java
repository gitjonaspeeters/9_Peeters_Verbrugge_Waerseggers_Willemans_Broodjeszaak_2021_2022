package utilities;

import excel.ExcelPlugin;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import model.BelegSoort;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;


public abstract class ExcelLoadSaveTemplate<K,V> {

    ExcelPlugin plugin = new ExcelPlugin();

    public abstract V makeObject(ArrayList<String> inputs);


    public abstract K getKey(ArrayList<String> tokens);

    public Map<K,V> load(File file) throws Exception {
        Map<K, V> map = new TreeMap<>();
        ArrayList<ArrayList<String>> excel = plugin.read(file);

        for(ArrayList<String> listexcel : excel){
            map.put(getKey(listexcel),makeObject(listexcel));
        }

        return map;
    }


    public void save(Map a, File file) throws IOException, BiffException, WriteException {
        ArrayList<V> List= (ArrayList<V>) a.values();

        plugin.write(file,addV(List));
    }

    protected abstract ArrayList<ArrayList<String>> addV(ArrayList<V> list);
}
