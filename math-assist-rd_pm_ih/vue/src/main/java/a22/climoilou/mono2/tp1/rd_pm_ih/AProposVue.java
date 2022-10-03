package a22.climoilou.mono2.tp1.rd_pm_ih;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;

import java.io.IOException;

public class AProposVue implements IVue {

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

    @Override
    public Scene getScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("AProposFXML.fxml"));
        Object splitPanePrincipal = fxmlLoader.load();
        return new Scene((Parent) splitPanePrincipal);
    }
}
