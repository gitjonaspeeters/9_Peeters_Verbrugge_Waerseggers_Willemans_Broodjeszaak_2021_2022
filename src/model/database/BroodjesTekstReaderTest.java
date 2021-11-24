package model.database;
import model.Broodje;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
public class BroodjesTekstReaderTest {
	public static Map load(){
		File file = new File("src/bestanden/broodjes.txt");
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
