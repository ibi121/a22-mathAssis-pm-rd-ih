package a22.climoilou.mono2.tp1.rd_pm_ih.controleur;

//import a22.climoilou.mono2.tp1.rd_pm_ih.origine.Fonctionnalite;
import a22.climoilou.mono2.tp1.rd_pm_ih.Serie;
import a22.climoilou.mono2.tp1.rd_pm_ih.TreeItemI;
import a22.climoilou.mono2.tp1.rd_pm_ih.services.UIAnimation;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxControllerAndView;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@Scope("prototype")
@FxmlView("../vue/AProposFXML.fxml")
public class AProposController implements Fonctionnalite{

    @FXML
    private Text aPropos;

    @FXML
    private Text aProposProf;

    @FXML
    private Text aProposCegep;

    @FXML
    private Text aProposEquipe;

    @FXML
    private Text aProposProduit;

    @FXML
    private Text aProposNoms;

    @FXML
    private Text presenteAText;

    @FXML
    private Text aProposAnnee;

    @FXML
    private Text presenteParText;

    public void initialize(){
        aPropos = new Text();
        aProposProf = new Text();
        aProposCegep = new Text();
        aProposEquipe = new Text();
        aProposProduit = new Text();
        aProposNoms = new Text();
        presenteAText = new Text();
        aProposAnnee = new Text();
        presenteParText = new Text();
    }

    public void setStage(ConfigurableApplicationContext context, Serie serie,  List<Serie> series) throws IOException {
        FxWeaver fxWeaver2 = context.getBean(FxWeaver.class);
        FxControllerAndView controllerAndView2 = fxWeaver2.load(AProposController.class);
        Parent root2 = (Pane) controllerAndView2.getView().get();
        Scene scene2 = new Scene(root2);
        Stage secondaryStage = new Stage();
        secondaryStage.setTitle("A Propos");
        secondaryStage.setScene(scene2);
        secondaryStage.sizeToScene();
        secondaryStage.resizableProperty().set(false);
        secondaryStage.show();

        UIAnimation ui = new UIAnimation();
        ui.deplacerFenetre(secondaryStage, 1, 500, secondaryStage.getWidth(), secondaryStage.getHeight());
    }

    public String getNom() {
        return "A Propos";
    }
}

