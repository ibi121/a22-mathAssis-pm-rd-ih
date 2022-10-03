package a22.climoilou.mono2.tp1.rd_pm_ih;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainController  implements IVue {

    private MainVue interfaceMainVue = null;
    private AProposController controllerAPropos = null;

    public MainController() {
        this.interfaceMainVue = new MainVue();
        this.controllerAPropos = new AProposController();
    }
    public AProposController getControllerAPropos() {
        return controllerAPropos;
    }
    @Override
    public Scene getScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("monoposte2FXML.fxml"));
        Parent root = fxmlLoader.load();
        return new Scene(root);
    }



    @FXML
    void APropos(ActionEvent event) throws IOException {


    }

    @FXML
    void RandomSerie(ActionEvent event) {

    }

    @FXML
    void ValiderLaSerie(ActionEvent event) {

    }


    @FXML
    private void initialize(){
        vBox1 = new VBox();
        paneGauche = new Pane();
        hBox1 = new HBox();
        btnAPropos = new Button();
        paneDroit = new Pane();
        splitPanePrincipal = new SplitPane();
        btnValiderSerie = new Button();
        btnRandom = new Button();
    }


}

