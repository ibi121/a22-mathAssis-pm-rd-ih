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
import net.rgielen.fxweaver.core.FxControllerAndView;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
@Scope("prototype")
@FxmlView("../vue/generateur.fxml")
public class GenerateurController implements Fonctionnalite {

    private Generateur generateur;

    private BD bd;
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

    @Autowired
    public void setBd(BD bd) {
        this.bd = bd;
    }

    @FXML
    void valider(ActionEvent event) throws IOException {
        generateur = new Generateur(Integer.parseInt(inputTextMin.getText()), Integer.parseInt(inputTextMax.getText()),
                Integer.parseInt(inputTextNombreValeurs.getText()), Integer.parseInt(inputTextNombreSeries.getText()), inputTextNomSerie.getText());

        generateur.creationValeurs();
        generateur.creationSeries();

        for (Serie serie : generateur.getSeriesCrees()) {
            bd.SaveSerie(serie);
            System.out.println(bd.GetAllSerie());
        }
    }

    public void setStage(ConfigurableApplicationContext context) throws IOException {
        FxWeaver fxWeaver2 = context.getBean(FxWeaver.class);
        FxControllerAndView controllerAndView2 = fxWeaver2.load(GenerateurController.class);
        Parent root2 = (Pane) controllerAndView2.getView().get();
        Scene scene2 = new Scene(root2);
        Stage secondaryStage = new Stage();
        secondaryStage.setTitle("Generation de series");
        secondaryStage.setScene(scene2);
        secondaryStage.show();
    }


    @Override
    public String getNom() {
        return "Génerateur aleatoire";
    }
}

