package utilities;

import excel.ExcelPlugin;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import model.BelegSoort;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
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


    public void save(Map<K,V> a, File file) throws IOException, BiffException, WriteException {

        plugin.write(file,addV(a.values()));
    }

    protected abstract ArrayList<ArrayList<String>> addV(Collection list);

}
