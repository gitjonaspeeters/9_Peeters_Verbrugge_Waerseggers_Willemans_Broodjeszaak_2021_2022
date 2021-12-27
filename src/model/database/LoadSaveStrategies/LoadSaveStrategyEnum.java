package model.database.LoadSaveStrategies;

public enum LoadSaveStrategyEnum {
    XLSBroodje("XLS", "model.database.LoadSaveStrategies.BroodjesExcelLoadSaveStrategy"),
    TXTBroodje("TXT", "model.database.LoadSaveStrategies.BroodjesTekstLoadSaveStrategy"),
    XLSBeleg("XLS", "model.database.LoadSaveStrategies.BelegExcelLoadSaveStrategy"),
    TXTBeleg("TXT", "model.database.LoadSaveStrategies.BelegTekstLoadSaveStrategy");

    private final String name;
    private final String propertyName;




    LoadSaveStrategyEnum(String name, String propertyName) {
        this.name = name;
        this.propertyName = propertyName;


    }
    public String getName() {
        return name;
    }

    public  String getPropertyName() {
        return propertyName;
    }


}
