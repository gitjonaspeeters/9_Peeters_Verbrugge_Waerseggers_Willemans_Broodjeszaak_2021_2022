package model.database;

import model.Broodje;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class BelegDatabase {
    public static Map load(){
        File file = new File("src/bestanden/beleg.txt");
        Map<String, Broodje> resultMap;
        try {
            resultMap = new BroodjesTekstReader().load(file);
            return resultMap;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
