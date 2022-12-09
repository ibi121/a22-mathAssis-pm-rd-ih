package a22.climoilou.mono2.tp1.rd_pm_ih.controleur;

import a22.climoilou.mono2.tp1.rd_pm_ih.Data;
import a22.climoilou.mono2.tp1.rd_pm_ih.Serie;
import a22.climoilou.mono2.tp1.rd_pm_ih.TreeItemI;
import a22.climoilou.mono2.tp1.rd_pm_ih.repositories.DataService;
import a22.climoilou.mono2.tp1.rd_pm_ih.repositories.SerieService;
import a22.climoilou.mono2.tp1.rd_pm_ih.services.UIAnimation;
import javafx.collections.ObservableList;
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
import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Component
@FxmlView("../vue/Modificateur.fxml")
public class ModificateurController implements Fonctionnalite {

    @FXML
    private ListView<Data> listData;

    @FXML
    private TextField textPanneau;

    private Serie serie;
    private SerieService serieService;
    private DataService dataService;

    private Stage secondaryStage;

    @Autowired
    public void setSerieService(SerieService serieService) {
        this.serieService = serieService;
    }

    @Autowired
    public void setDataService(DataService dataService) {
        this.dataService = dataService;
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
        if (listData.getSelectionModel().getSelectedItems().size() > 0) {
            String modif = textPanneau.getText();
            Function f1 = valideFonction(modif);
            Expression expression = new Expression(modif);
            Expression expressionX = expressionX(modif, listData.getSelectionModel().getSelectedItems().get(0).getX());
            Expression expressionY = expressionY(modif, listData.getSelectionModel().getSelectedItems().get(0).getY());
            Expression expressionXY = expressionXY(modif,
                    listData.getSelectionModel().getSelectedItems().get(0).getX(),
                    listData.getSelectionModel().getSelectedItems().get(0).getY());

            if (f1 != null) {
                listData.getSelectionModel().getSelectedItems().forEach((data -> {
                    data.setY(expressionFonction(f1, data.getX()));
                    dataService.saveData(data);
                    serie.setDateDerniereModification(LocalDateTime.now());
                    serieService.SaveSerie(serie);
                }));
                listData.refresh();
            } else if (expressionXY != null) {
                listData.getSelectionModel().getSelectedItems().forEach((data -> {
                    data.setY(expressionXY(modif, data.getX(), data.getY()).calculate());
                    dataService.saveData(data);
                    serie.setDateDerniereModification(LocalDateTime.now());
                    serieService.SaveSerie(serie);
                }));
                listData.refresh();
            } else if (expressionX != null) {
                listData.getSelectionModel().getSelectedItems().forEach((data -> {
                    data.setY(expressionX(modif, data.getX()).calculate());
                    dataService.saveData(data);
                    serie.setDateDerniereModification(LocalDateTime.now());
                    serieService.SaveSerie(serie);
                }));
                listData.refresh();
            } else if (expressionY != null) {
                listData.getSelectionModel().getSelectedItems().forEach((data -> {
                    data.setY(expressionY(modif, data.getY()).calculate());
                    dataService.saveData(data);
                    serie.setDateDerniereModification(LocalDateTime.now());
                    serieService.SaveSerie(serie);
                }));
                listData.refresh();
            } else if (expression.checkSyntax()) {
                listData.getSelectionModel().getSelectedItems().forEach((data -> {
                    data.setY(expression.calculate());
                    dataService.saveData(data);
                    serie.setDateDerniereModification(LocalDateTime.now());
                    serieService.SaveSerie(serie);
                }));
                listData.refresh();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Expression non valide");
                alert.setContentText("Veuillez entrer un chiffre, une expression ou une fonction[f(x)=...]");
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Aucune selection");
            alert.setContentText("Veuillez sellectionner un ou plusieurs champs de la liste");
            alert.show();
        }
    }

    private Expression expressionX(String modif, double x) {
        Argument argX = new Argument("x", x);
        Expression e = new Expression(modif, argX);
        return e.checkSyntax() ? e : null;
    }

    private Expression expressionY(String modif, double y) {
        Argument argY = new Argument("y", y);
        Expression e = new Expression(modif, argY);
        return e.checkSyntax() ? e : null;
    }

    private Expression expressionXY(String modif, double x, double y) {
        Argument argX = new Argument("x", x);
        Argument argY = new Argument("y", y);
        Expression e = new Expression(modif, argX, argY);
        return e.checkSyntax() ? e : null;
    }

    private double expressionFonction(Function f1, double x) {
        Expression expression = new Expression(f1.getFunctionName() + "(" + x + ")", f1);
        return expression.calculate();
    }

    public Function valideFonction(String fonction) {
        Function function = new Function(fonction);
        return function.checkSyntax() ? function : null;
    }

    @FXML
    public void initialize() {
        listData.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void remplirList(Serie serie) {
        listData.getItems().clear();
        listData.getItems().addAll(serie.getDonnees());
    }

    public void setStage(ConfigurableApplicationContext context, Serie serie,  List<Serie> series) throws IOException {
        if (serie != null) {
            if (secondaryStage == null) {
                FxWeaver fxWeaver = context.getBean(FxWeaver.class);
                FxControllerAndView controllerAndView = fxWeaver.load(ModificateurController.class);
                Parent root = (Pane) controllerAndView.getView().get();
                secondaryStage = new Stage();
                secondaryStage.setTitle("Modification de séries");
                secondaryStage.setScene(new Scene(root));
                secondaryStage.setResizable(false);
                UIAnimation ui = new UIAnimation();
                ui.deplacerFenetre(secondaryStage, 1, 1, 400, 400);
            }
            secondaryStage.show();
            remplirList(serie);
            UIAnimation ui = new UIAnimation();
            ui.deplacerFenetre(secondaryStage, 1, 1, 400, 400);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Modificateur information");
            alert.setHeaderText("Selection");
            alert.setContentText("Veuillez selectionner une serie de la liste");
            alert.show();
        }

        UIAnimation ui = new UIAnimation();
        ui.deplacerFenetre(secondaryStage, 1, 1, secondaryStage.getWidth(), secondaryStage.getHeight());
    }

    @Override
    public String getNom() {
        return "Modificateur";
    }

}
