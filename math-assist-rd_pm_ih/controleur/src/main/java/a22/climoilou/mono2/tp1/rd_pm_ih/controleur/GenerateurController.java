package a22.climoilou.mono2.tp1.rd_pm_ih.controleur;

import a22.climoilou.mono2.tp1.rd_pm_ih.Generateur;
import a22.climoilou.mono2.tp1.rd_pm_ih.Serie;
import a22.climoilou.mono2.tp1.rd_pm_ih.origine.Fonctionnalite;
import a22.climoilou.mono2.tp1.rd_pm_ih.repositories.BD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class GenerateurController implements Fonctionnalite {

    Generateur generateur;

    private BD bd = new BD();
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

    public void setBd(BD bd) {
        this.bd = bd;
    }



    @FXML
    void valider(ActionEvent event) throws IOException {
        generateur = new Generateur(Integer.parseInt(inputTextMin.getText()), Integer.parseInt(inputTextMax.getText()),
                Integer.parseInt(inputTextNombreValeurs.getText()), Integer.parseInt(inputTextNombreSeries.getText()), inputTextNomSerie.getText());

        System.out.println(generateur.toString());
        generateur.creationValeurs();
        System.out.println(generateur.getValeurs());
        generateur.creationSeries();
        for (Serie serie : generateur.getSeriesCrees()) {
            bd.SaveSerie(serie);
        }
//        System.out.println(generateur.getSeriesCrees());
    }

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


    @Override
    public String getNom() {
        return "Génerateur aleatoire";
    }
}

