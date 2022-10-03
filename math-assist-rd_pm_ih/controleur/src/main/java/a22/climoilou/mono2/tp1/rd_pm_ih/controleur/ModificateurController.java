package a22.climoilou.mono2.tp1.rd_pm_ih.controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class ModificateurController {



    @FXML
    private Button addition;

    @FXML
    private Button cinq;

    @FXML
    private Button dataX;

    @FXML
    private Button dataY;

    @FXML
    private Button deux;

    @FXML
    private Button division;

    @FXML
    private Button entrer;

    @FXML
    private Button huit;

    @FXML
    private ListView<?> listData;

    @FXML
    private Button multiplication;

    @FXML
    private Button neuf;

    @FXML
    private Pane panneau;

    @FXML
    private Button parentaiseFermante;

    @FXML
    private Button parentaiseOuvrante;

    @FXML
    private Button point;

    @FXML
    private Button quatre;

    @FXML
    private Button sept;

    @FXML
    private Button six;

    @FXML
    private Button soustraction;

    @FXML
    private TextField textPanneau;

    @FXML
    private Button trois;

    @FXML
    private Button un;

    @FXML
    private Button zero;

    @FXML
    void onClickAddition(ActionEvent event) {

    }

    @FXML
    void onClickCinq(ActionEvent event) {

    }

    @FXML
    void onClickDeux(ActionEvent event) {

    }

    @FXML
    void onClickDivision(ActionEvent event) {

    }

    @FXML
    void onClickEntrer(ActionEvent event) {

    }

    @FXML
    void onClickHuit(ActionEvent event) {

    }

    @FXML
    void onClickMultiplication(ActionEvent event) {

    }

    @FXML
    void onClickNeuf(ActionEvent event) {

    }

    @FXML
    void onClickParentaiseOuvrante(ActionEvent event) {

    }

    @FXML
    void onClickParenthaiseFermante(ActionEvent event) {

    }

    @FXML
    void onClickPoint(ActionEvent event) {

    }

    @FXML
    void onClickQuatre(ActionEvent event) {

    }

    @FXML
    void onClickSept(ActionEvent event) {

    }

    @FXML
    void onClickSix(ActionEvent event) {

    }

    @FXML
    void onClickSoustraction(ActionEvent event) {

    }

    @FXML
    void onClickTrois(ActionEvent event) {

    }

    @FXML
    void onClickUn(ActionEvent event) {

    }

    @FXML
    void onClickX(ActionEvent event) {

    }

    @FXML
    void onClickY(ActionEvent event) {

    }

    @FXML
    void onClickZero(ActionEvent event) {

    }

    public void setStage() throws IOException {
        Stage secondaryStage = new Stage();
        secondaryStage.setTitle("Modification de séries");
        secondaryStage.setScene(getScene());
        secondaryStage.show();
    }


    public Scene getScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../vue/Modificateur.fxml"));
        Parent root = fxmlLoader.load();
        return new Scene(root);
    }
}
