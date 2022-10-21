package a22.climoilou.mono2.tp1.rd_pm_ih.controleur;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    public void setStage(ConfigurableApplicationContext context) throws IOException {
        FxWeaver fxWeaver2 = context.getBean(FxWeaver.class);
        FxControllerAndView controllerAndView2 = fxWeaver2.load(AProposController.class);
        Parent root2 = (Pane) controllerAndView2.getView().get();
        Scene scene2 = new Scene(root2);
        Stage secondaryStage = new Stage();
        secondaryStage.setTitle("A Propos");
        secondaryStage.setScene(scene2);
        secondaryStage.show();
    }

    @Override
    public String getNom() {
        return "A Propos";
    }
}

