package a22.climoilou.mono2.tp1.rd_pm_ih.controleur;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class GenerateurController {



    @FXML
    private Text textMinimum;

    @FXML
    private Text textNombreValeurs;

    @FXML
    private TextField inputTextMin;

    @FXML
    private Text textNomSerie;

    @FXML
    private TextField inputTextMax;

    @FXML
    private Text textMaximum;

    @FXML
    private TextField inputTextNombreSeries;

    @FXML
    private Pane mainPane;

    @FXML
    private TextField inputTextNombreValeurs;

    @FXML
    private Text textNombreSeries;

    @FXML
    private Button btnValider;

    @FXML
    private TextField inputTextNomSerie;


    public void setStage() throws IOException {
        Stage secondaryStage = new Stage();
        secondaryStage.setTitle("Generation de series");
        secondaryStage.setScene(getScene());
        secondaryStage.show();
    }


    public Scene getScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../vue/generateur.fxml"));
        Parent root = fxmlLoader.load();
        return new Scene(root);
    }
}

