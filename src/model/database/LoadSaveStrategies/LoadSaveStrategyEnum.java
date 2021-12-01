package model.database.LoadSaveStrategies;

public enum LoadSaveStrategyEnum {
    XLS("Excel", "xls", BroodjesExcelLoadSaveStrategy.class),
    TXT("Tekst", "txt", BroodjesTekstLoadSaveStrategy.class);

    private final String name;
    private final String propertyName;
    private final Class<? extends LoadSaveStrategy> strategy;


    LoadSaveStrategyEnum(String name, String propertyName,  Class<? extends LoadSaveStrategy> strategy) {
        this.name = name;
        this.propertyName = propertyName;
        this.strategy = strategy;
    }
    public String getName() {
        return name;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public Class<? extends LoadSaveStrategy> getStrategy() {
        return strategy;
    }
}
