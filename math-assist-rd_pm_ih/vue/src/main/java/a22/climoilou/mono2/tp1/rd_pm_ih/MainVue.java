package a22.climoilou.mono2.tp1.rd_pm_ih;

import a22.climoilou.mono2.tp1.rd_pm_ih.IMainVue;
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
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Hello world!
 * :o)
 */
public class MainVue implements IMainVue {


    @FXML
    private VBox vBox1;

    @FXML
    private Pane paneGauche;

    @FXML
    private HBox hBox1;

    @FXML
    private Button btnAPropos;

    @FXML
    private Pane paneDroit;

    @FXML
    private SplitPane splitPanePrincipal;

    @FXML
    private Button btnValiderSerie;

    @FXML
    private Button btnRandom;


    @FXML
    private void initialize(URL location, ResourceBundle resourceBundle){
        vBox1 = new VBox();
        paneGauche = new Pane();
        hBox1 = new HBox();
        btnAPropos = new Button();
        paneDroit = new Pane();
        splitPanePrincipal = new SplitPane();
        btnValiderSerie = new Button();
        btnRandom = new Button();
    }

    @Override
    public Scene getScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("monoposte2FXML.fxml"));
        splitPanePrincipal = fxmlLoader.load();
        return new Scene(splitPanePrincipal);
    }
}
