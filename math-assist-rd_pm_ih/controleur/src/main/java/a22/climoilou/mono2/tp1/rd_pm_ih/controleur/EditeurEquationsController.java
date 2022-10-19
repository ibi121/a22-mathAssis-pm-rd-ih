package a22.climoilou.mono2.tp1.rd_pm_ih.controleur;

import a22.climoilou.mono2.tp1.rd_pm_ih.origine.Fonctionnalite;
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
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class EditeurEquationsController implements Fonctionnalite {

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
    private void initialize(){
        //get
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

    public void setStage() throws IOException {
        Stage secondaryStage = new Stage();
        secondaryStage.setTitle("Editeur d'equations");
        secondaryStage.setScene(getScene());
        secondaryStage.show();
    }


    public Scene getScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../vue/EditeurEquations.fxml"));
        Parent root = fxmlLoader.load();
        return new Scene(root);
    }
}
