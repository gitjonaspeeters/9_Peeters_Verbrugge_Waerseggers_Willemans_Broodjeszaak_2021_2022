package model.database;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import model.Broodje;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class BroodjesDatabase {
	public static Map load(){
		File file = new File("src/bestanden/broodjes.txt");
		Map<String, Broodje> resultMap;
		try {
			System.out.println(new BroodjesTekstReader().load(file));
			resultMap =new BroodjesTekstReader().load(file);
			return resultMap;
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
