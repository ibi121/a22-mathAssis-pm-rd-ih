package a22.climoilou.mono2.tp1.rd_pm_ih.controleur;

import a22.climoilou.mono2.tp1.rd_pm_ih.Data;
import a22.climoilou.mono2.tp1.rd_pm_ih.Equations;
import a22.climoilou.mono2.tp1.rd_pm_ih.Serie;
import a22.climoilou.mono2.tp1.rd_pm_ih.origine.Fonctionnalite;
import a22.climoilou.mono2.tp1.rd_pm_ih.repositories.EquationService;
import a22.climoilou.mono2.tp1.rd_pm_ih.repositories.SerieService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.Function;
import org.mariuszgromada.math.mxparser.mXparser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;

@Component
@Scope("prototype")
@FxmlView("../vue/EditeurEquations.fxml")
public class EditeurEquationsController implements Fonctionnalite {

    public TextField nbrData;
    public TextField inputA;
    public TextField inputOpe1;
    public TextField inputX;
    public TextField inputOp2;
    public TextField inputB;
    public Button btnAjoutSerie;
    private EquationService equationService;

    private SerieService serieService;
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


    @Autowired
    public void setEquationService(EquationService equationService) {
        this.equationService = equationService;
    }

    @Autowired
    public void setSerieService(SerieService serieService) { this.equationService = equationService; }

    @FXML
    private void initialize() {

        for (Equations e : equationService.GetAllEquations()
        ) {
            this.listViewFonctions.getItems().add(e.toString());
        }
    }

    @FXML
    void ajouterEquation(ActionEvent event) {
        String equation = inputA.getText() + inputOpe1.getText() + inputX.getText() + inputOp2.getText() + inputB.getText();

        Equations equations = new Equations(inputA.getText(), inputOpe1.getText(),
                inputX.getText(), inputOp2.getText(), inputB.getText());
        listViewFonctions.getItems().add(equation);

        int nombreData = Integer.parseInt(nbrData.getText());

        System.out.println(equation);
        System.out.println(nombreData);

        Serie s = null;

        double y = calculerEquation(equation + " - y");

        if(inputA.getText().equals("") || inputX.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs incomplets!!!");
            alert.setContentText("Veuilles remplir TOUS les champs.");
            alert.show();
        }else{
            s = new Serie();
            s.setDonnees(new ArrayList<>());
            s.addData(new Data(Double.parseDouble(inputX.getText()), y));
            equationService.SaveEquation(equations);
            serieService.SaveSerie(s);
        }


        System.out.println(s.getDonnees());
    }

    @FXML
    void effacerEquation(ActionEvent event) {
        String itemDelete = listViewFonctions.getSelectionModel().getSelectedItem();
        listViewFonctions.getItems().remove(itemDelete);
    }

    @FXML
    void modifierEquation(ActionEvent event) {
        String item = listViewFonctions.getSelectionModel().getSelectedItem();
        //inputEquation.setText(item);


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

    private double calculerEquation(String expression){
        Expression e1 = new Expression("solve( " + expression + ", y, -10000, 10000 )");

        mXparser.consolePrint(e1.calculate());
        return e1.calculate();
    }

}
