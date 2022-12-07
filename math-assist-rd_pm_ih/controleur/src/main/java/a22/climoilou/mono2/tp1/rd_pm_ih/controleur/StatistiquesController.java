package a22.climoilou.mono2.tp1.rd_pm_ih.controleur;

import a22.climoilou.mono2.tp1.rd_pm_ih.Categorie;
import a22.climoilou.mono2.tp1.rd_pm_ih.Equations;
import a22.climoilou.mono2.tp1.rd_pm_ih.Serie;
import a22.climoilou.mono2.tp1.rd_pm_ih.repositories.EquationService;
import a22.climoilou.mono2.tp1.rd_pm_ih.services.UIAnimation;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxControllerAndView;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@FxmlView("../vue/StatistiquesFXML.fxml")
public class StatistiquesController implements Fonctionnalite{

    @FXML
    private Text textEquationsTotal;

    @FXML
    private EquationService equationService;

    private List<Equations> equationsEnBd;

    @Autowired
    public void setEquationService(EquationService equationService) {
        this.equationService = equationService;
    }

    @FXML
    private void initialize() {
        equationsEnBd = equationService.GetAllEquations();
        textEquationsTotal.setText(String.valueOf(calculNombreEquationTotal()));
    }

    private int calculNombreEquationTotal() {
        return calculNombreEquationTotalPureRec(0);
    }

    private int calculNombreEquationTotalPureRec(int position) {
        int retVal = 0;

        if(position != equationsEnBd.size()){
            retVal = 1 + calculNombreEquationTotalPureRec(position + 1);
        }

        return retVal;
    }

    @Override
    public String getNom() {
        return "Statistiques";
    }

    public void setStage(ConfigurableApplicationContext context, Serie s,  List<Serie> series) {
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
