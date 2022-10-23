package a22.climoilou.mono2.tp1.rd_pm_ih.controleur;

import a22.climoilou.mono2.tp1.rd_pm_ih.repositories.UserRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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

@Component
@Scope("prototype")
@FxmlView("../vue/Review.fxml")
public class AvisController {


    private UserRepository BD;

    @Autowired
    public void setBD(UserRepository BD) {
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
    }

    public void VoirAvis(ActionEvent actionEvent) {
    }


    public void setStage(ConfigurableApplicationContext context) throws IOException {
        FxWeaver fxWeaver2 = context.getBean(FxWeaver.class);
        FxControllerAndView controllerAndView2 = fxWeaver2.load(EditeurEquationsController.class);
        Parent root2 = (Pane) controllerAndView2.getView().get();
        Scene scene2 = new Scene(root2);
        Stage secondaryStage = new Stage();
        secondaryStage.setScene(scene2);
        secondaryStage.show();

    }
}
