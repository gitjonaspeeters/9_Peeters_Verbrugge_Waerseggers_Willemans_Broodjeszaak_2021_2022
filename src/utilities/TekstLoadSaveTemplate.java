package utilities;
import model.BelegSoort;
import model.Broodje;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class TekstLoadSaveTemplate<K,V>{



    public abstract V makeObject(String[] inputs);


    public abstract K getKey(String[] tokens);


    public final Map<K, V> load (File file) throws IOException {
        Map<K, V> returnMap = new HashMap<K, V>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while (line != null && !line.trim().equals("")) {
                String[] tokens = line.split(",");
                V element = makeObject(tokens);
                K key =  getKey(tokens);
                returnMap.put(key, element);
                line = reader.readLine();
            }
        }
        return returnMap;
    }

    public void save (Map a, File file) throws IOException {
        FileWriter fileWriter= new FileWriter(file);

        for(Object object: a.values()){
            fileWriter.write(toStringEnzo(object)+"\n");
        }
        fileWriter.close();
    }

    protected abstract String toStringEnzo(Object object);


}








