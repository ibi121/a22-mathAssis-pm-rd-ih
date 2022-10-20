package a22.climoilou.mono2.tp1.rd_pm_ih.controleur;

import a22.climoilou.mono2.tp1.rd_pm_ih.origine.Fonctionnalite;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxControllerAndView;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@FxmlView("../vue/TableauValeurs.fxml")
public class TableauDesValeursController implements Fonctionnalite {

    @FXML
    private Button btnChangeData;

    @FXML
    private Text serieChoisie;

    @FXML
    private TableView<?> tableFonctions;

    @FXML
    void changeData(ActionEvent event) {

    }

    @Override
    public String getNom() {
        return "Graphique à bandes";
    }

    public void setStage(ConfigurableApplicationContext context) {
        FxWeaver fxWeaver2 = context.getBean(FxWeaver.class);
        FxControllerAndView controllerAndView2 = fxWeaver2.load(TableauDesValeursController.class);
        Parent root2 = (Pane) controllerAndView2.getView().get();
        Scene scene2 = new Scene(root2);
        Stage secondaryStage = new Stage();
        secondaryStage.setTitle(getNom());
        secondaryStage.setScene(scene2);
        secondaryStage.show();
    }
}
