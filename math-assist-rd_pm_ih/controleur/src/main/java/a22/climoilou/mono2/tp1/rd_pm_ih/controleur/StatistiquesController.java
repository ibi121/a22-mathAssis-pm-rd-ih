package a22.climoilou.mono2.tp1.rd_pm_ih.controleur;

import a22.climoilou.mono2.tp1.rd_pm_ih.*;
import a22.climoilou.mono2.tp1.rd_pm_ih.repositories.CategorieService;
import a22.climoilou.mono2.tp1.rd_pm_ih.repositories.EquationService;
import a22.climoilou.mono2.tp1.rd_pm_ih.repositories.SerieService;
import a22.climoilou.mono2.tp1.rd_pm_ih.services.UIAnimation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TreeItem;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxControllerAndView;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@FxmlView("../vue/StatistiquesFXML.fxml")
public class StatistiquesController implements Fonctionnalite {

    @FXML
    private Text textEquationsTotal;


    private List<Serie> listeSeriesEnBd;

    private List<Equations> listeEquationsEnBd;

    private List<Categorie> listeDeCategorie;

    @FXML
    private VBox conteneurTextEquationSerieMemeCat;


    @FXML
    private Spinner<Categorie> choixCategorie;


    @FXML
    private Text textNbSousCat;

    private EquationService equationService;
    private CategorieService categorieService;

    private SerieService serieService;


    @Autowired
    public void setCategorieService(CategorieService categorieService) {
        this.categorieService = categorieService;
    }

    @Autowired
    public void setEquationService(EquationService equationService) {
        this.equationService = equationService;
    }

    @Autowired
    public void setSerieService(SerieService serieService) {
        this.serieService = serieService;
    }

    @FXML
    private void initialize() {
        Recursives recursives = new Recursives();
        listeSeriesEnBd = new ArrayList<>();
        listeEquationsEnBd = new ArrayList<>();
        listeSeriesEnBd.addAll(serieService.GetAllSerie());
        listeEquationsEnBd.addAll(equationService.GetAllEquations());
        listeDeCategorie = new ArrayList<>();
        listeDeCategorie.addAll(categorieService.GetAllSousCatgeorie());

        textEquationsTotal.setText(String.valueOf(recursives.calculNombreEquationTotal(listeEquationsEnBd)));

        for (Categorie c : categorieService.GetAllSousCatgeorie()
        ) {
            Text text = new Text(c.getNom() + " : " + String.valueOf(recursives.calculNombreEquationEtSerieMemeCategorie(listeSeriesEnBd, listeEquationsEnBd,c)));
            text.setStyle("-fx-fill: white");

            conteneurTextEquationSerieMemeCat.getChildren().add(text);
        }

        ObservableList<Categorie> categories = FXCollections.observableArrayList(//
                listeDeCategorie);

        SpinnerValueFactory<Categorie> valueFactory = //
                new SpinnerValueFactory.ListSpinnerValueFactory<Categorie>(categories);

        valueFactory.setValue(categories.get(0));

        choixCategorie.setValueFactory(valueFactory);

        textNbSousCat.setText(String.valueOf(recursives.CalculerNombreDeSousCategorie(categories.get(0), 0)));

        choixCategorie.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {


                Categorie categorie = null;

                for (Categorie c: listeDeCategorie
                ) {
                    if (c.getNom().equals(choixCategorie.getValue().getNom())){
                        categorie = c;
                    }
                }

                textNbSousCat.setText(String.valueOf(recursives.CalculerNombreDeSousCategorie(categorie, 0)));
            }
        });



    }

    @Override
    public String getNom() {
        return "Statistiques";
    }

    public void setStage(ConfigurableApplicationContext context, Serie serie,  List<Serie> series) {
        FxWeaver fxWeaver2 = context.getBean(FxWeaver.class);
        FxControllerAndView controllerAndView2 = fxWeaver2.load(StatistiquesController.class);
        Parent root2 = (Pane) controllerAndView2.getView().get();
        Scene scene2 = new Scene(root2);
        Stage secondaryStage = new Stage();
        secondaryStage.sizeToScene();
        secondaryStage.setResizable(false);
        secondaryStage.setTitle(getNom());
        secondaryStage.setScene(scene2);
        secondaryStage.show();

        UIAnimation ui = new UIAnimation();
        ui.deplacerFenetre(secondaryStage, 1, 1, secondaryStage.getWidth(), secondaryStage.getHeight());
    }
}
