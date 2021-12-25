package model.korting;

public enum KortingEnum {
    GOEDKOOPSTEGRATIS("Goedkoopste broodje gratis", "model.korting.GoedkoopsteGratis"),
    GEEN_KORTING("Geen korting", "model.korting.GeenKorting"),
    PERCENT_VAN_HEEL_DE_BESTELLING("10% korting op heel de besteling", "model.korting.KortingOpHeelBesteling");


    private final String name;
    private final String propertyName;


    KortingEnum(String name, String propertyName) {
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
