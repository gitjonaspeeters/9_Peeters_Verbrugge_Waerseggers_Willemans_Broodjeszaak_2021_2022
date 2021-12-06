package model.database.LoadSaveStrategies;

public enum LoadSaveStrategyEnum {
    XLS("Excel", "model.database.LoadSaveStrategies.BroodjesExcelLoadSaveStrategy"),
    TXT("Tekst", "model.database.LoadSaveStrategies.BroodjesTekstLoadSaveStrategy");

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
