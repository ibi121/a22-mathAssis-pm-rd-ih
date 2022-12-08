package a22.climoilou.mono2.tp1.rd_pm_ih.controleur;

import a22.climoilou.mono2.tp1.rd_pm_ih.Serie;
import a22.climoilou.mono2.tp1.rd_pm_ih.Utilisateur;
import a22.climoilou.mono2.tp1.rd_pm_ih.repositories.UserRepository;
import a22.climoilou.mono2.tp1.rd_pm_ih.repositories.UtilisateurService;
import a22.climoilou.mono2.tp1.rd_pm_ih.services.UIAnimation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
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
@FxmlView("../vue/Review.fxml")
public class AvisController implements Fonctionnalite {


    @FXML
    public ListView<Utilisateur> listViewComments;
    private UtilisateurService BD;

    @Autowired
    public void setBD(UtilisateurService BD) {
        this.BD = BD;
    }

    @FXML
    public Button bouttonSauvegarderAvis;

    @FXML
    public Button voirLesAutresAvis;
    @FXML
    public TextField fieldPrenom;

    @FXML
    public TextField fieldNom;

    @FXML
    public TextField fieldCourriel;

    @FXML
    public TextArea fieldCommentaire;

    public void SauvegarderAvis(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.NONE);

        String prenom = String.valueOf(fieldPrenom.getText());
        String nom = String.valueOf(fieldNom.getText());
        String courriel = String.valueOf(fieldCourriel.getText());
        String commentaire = String.valueOf(fieldCommentaire.getText());


        List<String> listeDeString = new ArrayList<>();
        listeDeString.add(prenom);
        listeDeString.add(nom);
        listeDeString.add(courriel);
        listeDeString.add(commentaire);

        for (String s: listeDeString) {
            if(s.isEmpty()){
                alert.setAlertType(Alert.AlertType.WARNING);
                alert.setContentText("Attention, vous n'avez pas remplis tous les champs du formulaire :o)");
                alert.show();
            }
        }

        BD.SauvegarderUtilisateur(new Utilisateur(prenom, nom, courriel, commentaire));

    }

    @FXML
    void initialize(){

        for (Utilisateur util:BD.GetAllUtilisateur()) {
            this.listViewComments.getItems().add(util);
        }
        this.listViewComments.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }



    public void setStage(ConfigurableApplicationContext context, Serie s,  List<Serie> series) throws IOException {
        FxWeaver fxWeaver2 = context.getBean(FxWeaver.class);
        FxControllerAndView controllerAndView2 = fxWeaver2.load(AvisController.class);
        Parent root2 = (SplitPane) controllerAndView2.getView().get();
        Scene scene2 = new Scene(root2);
        Stage secondaryStage = new Stage();
        secondaryStage.setTitle("Avis d'utilisateurs");
        secondaryStage.setScene(scene2);
        secondaryStage.show();

        UIAnimation ui = new UIAnimation();
        ui.deplacerFenetre(secondaryStage, 1, 1, secondaryStage.getWidth(), secondaryStage.getHeight());
    }

    @Override
    public String getNom() {
        return "Avis";
    }
}
