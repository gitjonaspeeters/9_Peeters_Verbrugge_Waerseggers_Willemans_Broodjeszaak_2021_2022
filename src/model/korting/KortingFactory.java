package model.korting;

import model.database.LoadSaveStrategies.LoadSaveStrategy;
import model.database.LoadSaveStrategies.LoadSaveStrategyEnum;

public class KortingFactory {
    public static KortingsInterface Korting(String format) {
        KortingsInterface instance = null;

        KortingEnum kortingEnum1 = KortingEnum.valueOf(format);
        String formaatnaam = kortingEnum1.getPropertyName();

        try{
            Class kortingClass= Class.forName(formaatnaam);
            Object object= kortingClass.newInstance();
            instance= (KortingsInterface) object;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  instance;
    }
}
