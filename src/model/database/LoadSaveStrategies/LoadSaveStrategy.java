package model.database.LoadSaveStrategies;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public interface LoadSaveStrategy<K,V> {

    public Map<K,V> load() throws IOException, Exception;

    public void save(File file, Map<K,V> a) throws IOException, BiffException, WriteException;


}
