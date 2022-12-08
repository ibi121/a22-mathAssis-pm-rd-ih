package a22.climoilou.mono2.tp1.rd_pm_ih.controleur;

import a22.climoilou.mono2.tp1.rd_pm_ih.Categorie;
import a22.climoilou.mono2.tp1.rd_pm_ih.Equations;
import a22.climoilou.mono2.tp1.rd_pm_ih.Serie;
import a22.climoilou.mono2.tp1.rd_pm_ih.repositories.CategorieService;
import a22.climoilou.mono2.tp1.rd_pm_ih.repositories.EquationService;
import a22.climoilou.mono2.tp1.rd_pm_ih.repositories.SerieService;
import a22.climoilou.mono2.tp1.rd_pm_ih.services.UIAnimation;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    @FXML
    private EquationService equationService;

    @FXML
    private CategorieService categorieService;

    @FXML
    private VBox conteneurTextEquationSerieMemeCat;
    @FXML
    private SerieService serieService;

    private List<Serie> listeSeriesEnBd;

    private List<Equations> listeEquationsEnBd;

    private List<Categorie> listeDeCategorie;

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
        textEquationsTotal.setText(String.valueOf(calculNombreEquationTotal()));

        listeSeriesEnBd = new ArrayList<>();
        listeEquationsEnBd = new ArrayList<>();
        listeSeriesEnBd.addAll(serieService.GetAllSerie());
        listeEquationsEnBd.addAll(equationService.GetAllEquations());
        for (Categorie c : categorieService.GetAllSousCatgeorie()
        ) {
            Text text = new Text();
            text.setText(c.getNom() + " : " + String.valueOf(calculNombreEquationEtSerieMemeCategorie(c)));
            conteneurTextEquationSerieMemeCat.getChildren().add(text);
        }

        listeDeCategorie = new ArrayList<>();

        listeDeCategorie.addAll(categorieService.GetAllSousCatgeorie());


        System.out.println(CalculerNombreDeSousCategoriePureRec(listeDeCategorie.get(1), 0));

    }

    private int calculNombreEquationTotal() {
        return calculNombreEquationTotalPureRec(0);
    }

    private int calculNombreEquationTotalPureRec(int position) {
        int retVal = 0;

        if (position != equationService.GetAllEquations().size()) {
            retVal = 1 + calculNombreEquationTotalPureRec(position + 1);
        }

        return retVal;
    }

    private int calculNombreEquationEtSerieMemeCategorie(Categorie c) {
        int retVal = 0;

        retVal += calculNombreEquationEtSerieMemeCategoriePureRec(c, 0, 0);

        return retVal;
    }

    private int calculNombreEquationEtSerieMemeCategoriePureRec(Categorie c, int positionSerie, int positionEquation) {
        int retVal = 0;

        if (positionSerie < listeSeriesEnBd.size()) {

            if (!listeSeriesEnBd.get(positionSerie).getCategorie().getNom().equals(c.getNom())) {
                retVal = calculNombreEquationEtSerieMemeCategoriePureRec(c, positionSerie + 1, positionEquation);
            }
            if (listeSeriesEnBd.get(positionSerie).getCategorie().getNom().equals(c.getNom())) {
                retVal = 1 + calculNombreEquationEtSerieMemeCategoriePureRec(c, positionSerie + 1, positionEquation);
            }
        }

        if (positionSerie == listeSeriesEnBd.size() && positionEquation < listeEquationsEnBd.size()) {
            if (!listeEquationsEnBd.get(positionEquation).getCategorie().getNom().equals(c.getNom())) {
                retVal = calculNombreEquationEtSerieMemeCategoriePureRec(c, positionSerie, positionEquation + 1);
            }
            if (listeEquationsEnBd.get(positionEquation).getCategorie().getNom().equals(c.getNom())) {
                retVal = 1 + calculNombreEquationEtSerieMemeCategoriePureRec(c, positionSerie, positionEquation + 1);
            }
        }


        return retVal;
    }

    private int CalculerNombreDeSousCategoriePureRec(Categorie categorie, int index) {
        int nmbr = 0;
        if (!(categorie.getSousCategorie().isEmpty())) {
            nmbr += 1 + CalculerNombreDeSousCategoriePureRec(categorie.getSousCategorie().get(index), 0);
            if (categorie.getSousCategorie().size() > 1) {
                nmbr += 1 + CalculerNombreDeSousCategoriePureRec(categorie.getSousCategorie().get(index), 0);
                index++;
            }
        } else {
            return nmbr;
        }
        return nmbr;
    }

    @Override
    public String getNom() {
        return "Statistiques";
    }

    public void setStage(ConfigurableApplicationContext context, Serie s, List<Serie> series) {
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
        ui.deplacerFenetre(secondaryStage, 1, 1, 400, 400);
    }
}
