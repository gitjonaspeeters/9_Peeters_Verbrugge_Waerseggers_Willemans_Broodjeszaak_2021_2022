package view.panels;

import controller.SettingsController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import jxl.read.biff.BiffException;
import model.database.LoadSaveStrategies.LoadSaveStrategyEnum;
import model.database.Settings;
import model.korting.KortingEnum;

import java.io.IOException;


public class SettingsPane extends GridPane {
    private SettingsController settingsController;
    private Label label = new Label("Formaat:");
    private Label label1 = new Label("Korting:");
    private ChoiceBox korting=new ChoiceBox<>();
    private ChoiceBox formaat=new ChoiceBox<>();
    private Button save = new Button("Save");



    public SettingsPane(SettingsController controller) throws BiffException, IOException {
        settingsController=controller;
        Paint paint= Paint.valueOf("Grey");
        this.add(label, 1, 0);
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        this.setBackground(new Background(new BackgroundFill(paint,null,new Insets(0))));
        LoadSaveStrategyEnum[] formaten = LoadSaveStrategyEnum.values();
        String huidig = Settings.getInstance().getProperty("formaat");
        korting.setValue(huidig);
        korting.getItems().add("TXT");
        korting.getItems().add("XLS");
        korting.setValue(huidig);

        this.add(korting, 1 , 1);
        this.add(label1, 1, 2);

        KortingEnum[] kortings = KortingEnum.values();
        for(int i = 0; i < kortings.length; i++){
            formaat.getItems().add(kortings[i].getName());
        }
        formaat.setValue(Settings.getInstance().getProperty("korting"));
        this.add(formaat, 1,3);

        save.setOnAction(e -> {
            try {
                SaveKnop();
             } catch (BiffException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        this.add(save, 1,4);
        this.settingsController.setView(this);
    }

    public void SaveKnop() throws BiffException, IOException {
        String formaat1 = ((String) korting.getValue());
        String korting = (String) formaat.getValue();
        Settings.getInstance().setProperty("formaat", formaat1);
        Settings.getInstance().setProperty("korting", korting);
        Settings.updateSettings();
    }

}
