package a22.climoilou.mono2.tp1.rd_pm_ih;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainController  implements IMainController{

    private IMainVue interfaceMainVue = null;

    public MainController() {
        this.interfaceMainVue = new MainVue();
    }

    @Override
    public Scene getScene() {
        try {
            return interfaceMainVue.getScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}

