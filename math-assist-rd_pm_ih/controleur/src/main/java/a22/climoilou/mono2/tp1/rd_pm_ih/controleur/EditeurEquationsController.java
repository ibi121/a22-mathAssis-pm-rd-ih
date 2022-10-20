package a22.climoilou.mono2.tp1.rd_pm_ih.controleur;

import a22.climoilou.mono2.tp1.rd_pm_ih.Equations;
import a22.climoilou.mono2.tp1.rd_pm_ih.Serie;
import a22.climoilou.mono2.tp1.rd_pm_ih.origine.Fonctionnalite;
import a22.climoilou.mono2.tp1.rd_pm_ih.repositories.EquationService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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
@FxmlView("../vue/EditeurEquations.fxml")
public class EditeurEquationsController implements Fonctionnalite {

    private EquationService equationService;
    @FXML
    private Text textSaisie;

    @FXML
    private VBox vbox2;

    @FXML
    private Button btnEffacer;

    @FXML
    private VBox vbox1;

    @FXML
    private Button btnModifier;

    @FXML
    private ListView<String> listViewFonctions; //Aller chercher en base de données;

    @FXML
    private HBox hbox1;

    @FXML
    private Button btnAjout;

    @FXML
    private Pane paneBase;

    @FXML
    private TextField inputEquation;

    @FXML
    private void initialize() {

        for (Equations e : equationService.GetAllEquations()
        ) {
            this.listViewFonctions.getItems().add(e.toString());
        }


    }

    @FXML
    void ajouterEquation(ActionEvent event) {
        String equation = inputEquation.getText();
        listViewFonctions.getItems().add(equation);

        System.out.println(equation);
        //listViewFonctions.setOrientation(Orientation.VERTICAL);

    }

    @FXML
    void effacerEquation(ActionEvent event) {
        String itemDelete = listViewFonctions.getSelectionModel().getSelectedItem();
        listViewFonctions.getItems().remove(itemDelete);
    }

    @FXML
    void modifierEquation(ActionEvent event) {
        String item = listViewFonctions.getSelectionModel().getSelectedItem();
        inputEquation.setText(item);


    }

    @Override
    public String getNom() {
        return "Editeur  de fonctions";
    }

    public void setStage(ConfigurableApplicationContext context) throws IOException {
        FxWeaver fxWeaver2 = context.getBean(FxWeaver.class);
        FxControllerAndView controllerAndView2 = fxWeaver2.load(EditeurEquationsController.class);
        Parent root2 = (Pane) controllerAndView2.getView().get();
        Scene scene2 = new Scene(root2);
        Stage secondaryStage = new Stage();
        secondaryStage.setTitle(getNom());
        secondaryStage.setScene(scene2);
        secondaryStage.show();

    }

}
