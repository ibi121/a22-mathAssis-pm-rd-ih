package a22.climoilou.mono2.tp1.rd_pm_ih.controleur;

import a22.climoilou.mono2.tp1.rd_pm_ih.Data;
import a22.climoilou.mono2.tp1.rd_pm_ih.Serie;
//import a22.climoilou.mono2.tp1.rd_pm_ih.origine.Fonctionnalite;
import a22.climoilou.mono2.tp1.rd_pm_ih.TreeItemI;
import a22.climoilou.mono2.tp1.rd_pm_ih.repositories.SerieService;
import a22.climoilou.mono2.tp1.rd_pm_ih.services.UIAnimation;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxControllerAndView;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@FxmlView("../vue/TableauValeurs.fxml")
public class TableauDesValeursController implements Fonctionnalite {

    @FXML
    public GridPane tableau;

    @FXML
    public TextField nomSerie;
    @FXML
    private Button btnChangeData;

    private SerieService serieService;

    private ArrayList<String> textFieldsDataX = new ArrayList<>();

    private ArrayList<String> textFieldsDataY = new ArrayList<>();


    @Autowired
    public void setSerieService(SerieService serieService) {
        this.serieService = serieService;
    }

    @FXML
    private void initialize() {
    }

    @Override
    public String getNom() {
        return "Tableau des valeurs";
    }


    public void setStage(ConfigurableApplicationContext context, Serie serie,  List<Serie> series) throws IOException {
        FxWeaver fxWeaver2 = context.getBean(FxWeaver.class);
        FxControllerAndView controllerAndView2 = fxWeaver2.load(TableauDesValeursController.class);
        Parent root2 = (Pane) controllerAndView2.getView().get();
        Scene scene2 = new Scene(root2);
        Stage secondaryStage = new Stage();
        secondaryStage.sizeToScene();
        secondaryStage.setResizable(false);
        secondaryStage.setTitle(getNom());
        secondaryStage.setScene(scene2);
        secondaryStage.show();
        nomSerie.setText(serie.getNomSerie());

        for (int i = 0; i < serie.getDonnees().size(); i++) {
            Text dataX = new Text();
            TextField dataY = new TextField();
            dataX.setText(String.valueOf(serie.getDonnees().get(i).getX()));
            dataY.setText(String.valueOf(serie.getDonnees().get(i).getY()));

            textFieldsDataX.add(dataX.getText());
            textFieldsDataY.add(dataY.getText());

            tableau.addColumn(i + 1);
            tableau.add(dataY, i + 1, 0);
            tableau.add(dataX, i + 1, 1);
        }

        UIAnimation ui = new UIAnimation();
        ui.deplacerFenetre(secondaryStage, 1, 1, 400, 400);

    }

    @FXML
    public void creationSerie(ActionEvent actionEvent) {
        Serie serie = new Serie();
        List<Data> donneesSerieInput = new ArrayList<Data>();

        serie.setNomSerie(nomSerie.getText());

        for (int i = 0, j = 0; i < textFieldsDataX.size() && j < textFieldsDataY.size(); i++,j++) {
            donneesSerieInput.add(new Data(Double.parseDouble(textFieldsDataX.get(i)), Double.parseDouble(textFieldsDataY.get(j))));
        }

        serie.setDonnees(donneesSerieInput);
        serie.setNomAuteur("inconnue");
        serie.setDateCreation(LocalDateTime.now());
        serieService.SaveSerie(serie);
    }
}
