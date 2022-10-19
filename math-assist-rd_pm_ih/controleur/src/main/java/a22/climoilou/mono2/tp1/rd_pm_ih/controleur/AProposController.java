package a22.climoilou.mono2.tp1.rd_pm_ih.controleur;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AProposController{

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

    public void setStage() throws IOException {
        Stage secondaryStage = new Stage();
        secondaryStage.setTitle("Des propos bien meritees");
        secondaryStage.setScene(getScene());
        secondaryStage.show();
    }


    public Scene getScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../vue/AProposFXML.fxml"));
        Parent root = fxmlLoader.load();
        return new Scene(root);
    }
}

