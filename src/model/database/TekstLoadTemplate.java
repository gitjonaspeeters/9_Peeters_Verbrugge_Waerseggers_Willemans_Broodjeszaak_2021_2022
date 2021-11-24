package model.database;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class TekstLoadTemplate <K,V>{	
		
	public final Map<K,V> load(File file) throws IOException {
		Map<K,V> returnMap = new HashMap<K,V>(); 
		try (BufferedReader reader = new BufferedReader(new FileReader(file))){			
			String line = reader.readLine();
			while (line != null && !line.trim().equals("")) {
				String[] tokens = line.split(",");
				V element = maakObject(tokens);
				K key = getKey(tokens);
				returnMap.put(key,element);
				line = reader.readLine();
			}
		}
		return returnMap;
	}

	abstract V maakObject(String[] tokens);
	
	abstract K getKey(String[] tokens);
}

	


