package a22.climoilou.mono2.tp1.rd_pm_ih;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.text.View;
import java.io.IOException;

/**
 * Hello world!
 * :o)
 */
public class MainVue{

    private IVue iVue = null;

    public MainVue() {
        this.iVue = MainVue();
    }

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



//    @FXML
//    void APropos(ActionEvent event) throws IOException {
//
//
//    }
//
//    @FXML
//    void RandomSerie(ActionEvent event) {
//
//    }
//
//    @FXML
//    void ValiderLaSerie(ActionEvent event) {
//
//    }
//
//
//    @FXML
//    private void initialize(){
//        vBox1 = new VBox();
//        paneGauche = new Pane();
//        hBox1 = new HBox();
//        btnAPropos = new Button();
//        paneDroit = new Pane();
//        splitPanePrincipal = new SplitPane();
//        btnValiderSerie = new Button();
//        btnRandom = new Button();
//    }

}
