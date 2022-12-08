package a22.climoilou.mono2.tp1.rd_pm_ih.controleur;

import a22.climoilou.mono2.tp1.rd_pm_ih.Categorie;
import a22.climoilou.mono2.tp1.rd_pm_ih.Generateur;
import a22.climoilou.mono2.tp1.rd_pm_ih.Serie;
import a22.climoilou.mono2.tp1.rd_pm_ih.TreeItemI;
import a22.climoilou.mono2.tp1.rd_pm_ih.repositories.CategorieService;
import a22.climoilou.mono2.tp1.rd_pm_ih.repositories.SerieService;
import a22.climoilou.mono2.tp1.rd_pm_ih.services.UIAnimation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
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
import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
@FxmlView("../vue/Generateur.fxml")
public class GenerateurController implements Fonctionnalite {

    @FXML
    public TextField inputTextNomAuteur;
    @FXML
    private Generateur generateur;
    @FXML
    private SerieService bd;

    @FXML
    private CategorieService bdCategorie;

    @FXML
    private TextField inputTextMin;

    @FXML
    private TextField inputTextMax;

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

    @FXML
    private Spinner<Categorie> inputCategorie;

    private Categorie categorieSerie;

    private List<Categorie> categoriesEnBD;

    @Autowired
    public void setBd(SerieService bd) {
        this.bd = bd;
    }

    @Autowired
    public void setBdCategorie(CategorieService bd) {
        this.bdCategorie = bd;
    }

    @FXML
    private void initialize(){
        this.inputTextMin.setText("1");
        this.inputTextMax.setText("100");
        this.inputTextNombreValeurs.setText("6");
        this.inputTextNombreSeries.setText("1");
        this.inputTextNomSerie.setText("Série aléatoire avec valeurs par défaut");
        this.inputTextNomAuteur.setText("inconnue");

        //ToDo Aller chercher toutes categories en BD

        categoriesEnBD = new ArrayList<>();

        categoriesEnBD = bdCategorie.GetAllSousCatgeorie();


        ObservableList<Categorie> categories = FXCollections.observableArrayList(//
                categoriesEnBD);

        SpinnerValueFactory<Categorie> valueFactory = //
                new SpinnerValueFactory.ListSpinnerValueFactory<Categorie>(categories);

        valueFactory.setValue(categories.get(0));

        inputCategorie.setValueFactory(valueFactory);


    }
    @FXML
    void valider(ActionEvent event) throws IOException {

        for (Categorie c: categoriesEnBD
        ) {
            if (c.getNom().equals(inputCategorie.getValue().getNom())){
                categorieSerie = c;
            }
        }

        generateur = new Generateur(Integer.parseInt(inputTextMin.getText()), Integer.parseInt(inputTextMax.getText()),
                    Integer.parseInt(inputTextNombreValeurs.getText()), Integer.parseInt(inputTextNombreSeries.getText()), inputTextNomSerie.getText(), inputTextNomAuteur.getText(), categorieSerie);


        generateur.creationValeurs();
        generateur.creationSeries();

        for (Serie serie : generateur.getSeriesCrees()) {
            bd.SaveSerie(serie);
        }

    }

    public void setStage(ConfigurableApplicationContext context, Serie serie,  List<Serie> series) throws IOException {
        FxWeaver fxWeaver2 = context.getBean(FxWeaver.class);
        FxControllerAndView controllerAndView2 = fxWeaver2.load(GenerateurController.class);
        Parent root2 = (Pane) controllerAndView2.getView().get();
        Scene scene2 = new Scene(root2);
        Stage secondaryStage = new Stage();
        secondaryStage.sizeToScene();
        secondaryStage.resizableProperty().set(false);
        secondaryStage.setTitle(getNom());
        secondaryStage.setScene(scene2);
        secondaryStage.show();

        UIAnimation ui = new UIAnimation();
        ui.deplacerFenetre(secondaryStage, 1150, 1, secondaryStage.getWidth(), secondaryStage.getHeight());

    }

    @Override
    public String getNom() {
        return "Génerateur aleatoire";
    }

}

