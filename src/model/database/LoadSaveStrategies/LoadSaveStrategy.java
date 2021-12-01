package model.database.LoadSaveStrategies;

import java.util.Map;

public interface LoadSaveStrategy<K,V> {

    public Map<K,V> load();

    public void save(Map<K,V> a);


}
