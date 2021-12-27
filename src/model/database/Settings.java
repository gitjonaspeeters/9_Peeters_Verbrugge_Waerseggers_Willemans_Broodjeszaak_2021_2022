package model.database;

import jxl.read.biff.BiffException;


import java.io.*;
import java.util.Properties;

public class Settings {
    private final static File file = new File("src/bestanden/settings.properties");
    private static Properties instance;

    public static Properties Properties() throws IOException, BiffException {
        return createInstance();
    }
    public static Properties getInstance() throws IOException, BiffException {
        return createInstance();
    }

    public static void updateSettings() throws IOException, BiffException {
        FileOutputStream outputStream = new FileOutputStream(file);
        getInstance().store(outputStream, null);
        outputStream.close();
    }
    public static Properties createInstance() throws IOException, BiffException {
        if (instance == null) {
            synchronized (Properties.class) {
                instance = new Properties();
                InputStream inputStream = new FileInputStream(file);
                instance.load(inputStream);
                inputStream.close();
            }
        }

        return instance;
    }
}
