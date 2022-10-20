package a22.climoilou.mono2.tp1.rd_pm_ih.controleur;

import a22.climoilou.mono2.tp1.rd_pm_ih.Data;
import a22.climoilou.mono2.tp1.rd_pm_ih.Serie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxControllerAndView;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

@Component
@FxmlView("../vue/Modificateur.fxml")
public class ModificateurController {

    @FXML
    private ListView<Data> listData;

    @FXML
    private TextField textPanneau;

    private MainController mainController;

    @Autowired
    public void setMainController(@Lazy MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    void onClickAddition(ActionEvent event) {
        textPanneau.setText(textPanneau.getText() + "+");
    }

    @FXML
    void onClickCinq(ActionEvent event) {
        textPanneau.setText(textPanneau.getText() + "5");
    }

    @FXML
    void onClickClear(ActionEvent event) {
        textPanneau.setText("");
    }

    @FXML
    void onClickDeux(ActionEvent event) {
        textPanneau.setText(textPanneau.getText() + "2");
    }

    @FXML
    void onClickDivision(ActionEvent event) {
        textPanneau.setText(textPanneau.getText() + "/");
    }

    @FXML
    void onClickHuit(ActionEvent event) {
        textPanneau.setText(textPanneau.getText() + "8");
    }

    @FXML
    void onClickMultiplication(ActionEvent event) {
        textPanneau.setText(textPanneau.getText() + "*");
    }

    @FXML
    void onClickNeuf(ActionEvent event) {
        textPanneau.setText(textPanneau.getText() + "9");
    }

    @FXML
    void onClickParentaiseOuvrante(ActionEvent event) {
        textPanneau.setText(textPanneau.getText() + "(");
    }

    @FXML
    void onClickParenthaiseFermante(ActionEvent event) {
        textPanneau.setText(textPanneau.getText() + ")");
    }

    @FXML
    void onClickPoint(ActionEvent event) {
        textPanneau.setText(textPanneau.getText() + ".");
    }

    @FXML
    void onClickQuatre(ActionEvent event) {
        textPanneau.setText(textPanneau.getText() + "4");
    }

    @FXML
    void onClickSept(ActionEvent event) {
        textPanneau.setText(textPanneau.getText() + "7");
    }

    @FXML
    void onClickSix(ActionEvent event) {
        textPanneau.setText(textPanneau.getText() + "6");
    }

    @FXML
    void onClickSoustraction(ActionEvent event) {
        textPanneau.setText(textPanneau.getText() + "-");
    }

    @FXML
    void onClickTrois(ActionEvent event) {
        textPanneau.setText(textPanneau.getText() + "3");
    }

    @FXML
    void onClickUn(ActionEvent event) {
        textPanneau.setText(textPanneau.getText() + "1");
    }

    @FXML
    void onClickX(ActionEvent event) {
        textPanneau.setText(textPanneau.getText() + "x");
    }

    @FXML
    void onClickY(ActionEvent event) {
        textPanneau.setText(textPanneau.getText() + "y");
    }

    @FXML
    void onClickZero(ActionEvent event) {
        textPanneau.setText(textPanneau.getText() + "0");
    }

    @FXML
    void onClickEntrer(ActionEvent event) {
        // test de changement des y selectionner dans les Datas
        listData.getSelectionModel().getSelectedItems().forEach((data -> {
            data.setY(Double.parseDouble(textPanneau.getText()));
        }));
    }

    @FXML
    public void initialize() {
        listData.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void setStage(ConfigurableApplicationContext context) throws IOException {
        FxWeaver fxWeaver = context.getBean(FxWeaver.class);
        FxControllerAndView controllerAndView = fxWeaver.load(ModificateurController.class);
        Parent root = (Pane) controllerAndView.getView().get();
        Stage secondaryStage = new Stage();
        secondaryStage.setTitle("Modification de séries");
        secondaryStage.setScene(new Scene(root));
        secondaryStage.show();
    }
}
