package model.database.LoadSaveStrategies;

public enum LoadSaveStrategyEnum {
    XLSBroodje("Excel", "model.database.LoadSaveStrategies.BroodjesExcelLoadSaveStrategy"),
    TXTBroodje("Tekst", "model.database.LoadSaveStrategies.BroodjesTekstLoadSaveStrategy"),
    XLSBeleg("Excel", "model.database.LoadSaveStrategies.BelegExcelLoadSaveStrategy"),
    TXTBeleg("Tekst", "model.database.LoadSaveStrategies.BelegTekstLoadSaveStrategy");

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
