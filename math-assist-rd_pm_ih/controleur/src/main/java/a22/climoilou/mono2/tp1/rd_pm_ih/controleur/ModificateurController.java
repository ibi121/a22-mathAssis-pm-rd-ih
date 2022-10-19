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
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ModificateurController {

    @FXML
    private Button addition;

    @FXML
    private Button cinq;

    @FXML
    private Button clear;

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
    private ListView<Data> listData;

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
    void onClickEntrer(ActionEvent event) {
        // test de changement des y selectionner dans les Datas
        listData.getSelectionModel().getSelectedItems().forEach((data -> {
            data.setY(Double.parseDouble(textPanneau.getText()));
        }));
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

    // ici temporairement
    @FXML
    public void initialize() {
        ObservableList<Data> dataSerie = FXCollections.observableArrayList(new Data(0, 0), new Data(1, 1), new Data(2, 2), new Data (3, 3));
        listData.setItems(dataSerie);

        // Selection multiple
        listData.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
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
