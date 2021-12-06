package model.database.LoadSaveStrategies;


import java.util.Map;

public class LoadSaveStrategyFactory {
   public static LoadSaveStrategy createLoadSaveStrategy(String format, Object... args){

       LoadSaveStrategy instance = null;

       LoadSaveStrategyEnum loadSaveStrategyEnum = LoadSaveStrategyEnum.valueOf(format);
       String formaatnaam = loadSaveStrategyEnum.getPropertyName();

       try{
           Class dbClass= Class.forName(formaatnaam);
           Object object= dbClass.newInstance();
           instance= (LoadSaveStrategy) object;
       } catch (Exception e) {
           e.printStackTrace();
       }
       return instance;
   }
}
