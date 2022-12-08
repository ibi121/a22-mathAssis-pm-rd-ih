package a22.climoilou.mono2.tp1.rd_pm_ih.controleur;

import a22.climoilou.mono2.tp1.rd_pm_ih.*;
import a22.climoilou.mono2.tp1.rd_pm_ih.repositories.CategorieService;
import a22.climoilou.mono2.tp1.rd_pm_ih.repositories.EquationService;
import a22.climoilou.mono2.tp1.rd_pm_ih.repositories.SerieService;
import a22.climoilou.mono2.tp1.rd_pm_ih.services.UIAnimation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxControllerAndView;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.Function;
import org.mariuszgromada.math.mxparser.mXparser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
@Scope("prototype")
@FxmlView("../vue/EditeurEquations.fxml")
public class EditeurEquationsController implements Fonctionnalite {

    @FXML
    public TextField nbrData;
    @FXML
    public TextField inputEquation;

    @FXML
    private EquationService equationService;
    @FXML
    private ListView<String> listViewFonctions;

    @FXML
    private SerieService serieService;

    @FXML
    private Spinner<Categorie> spinnerCategorieEquation;

    @FXML
    private CategorieService bdCategorie;

    private List<Categorie> categoriesEnBD;

    @Autowired
    public void setBdCategorie(CategorieService bd) {
        this.bdCategorie = bd;
    }


    @Autowired
    public void setEquationService(EquationService equationService) {
        this.equationService = equationService;
    }

    @Autowired
    public void setSerieService(SerieService serieService) {
        this.serieService = serieService;
    }

    @FXML
    private void initialize() {

        for (Equations e : equationService.GetAllEquations()
        ) {
            this.listViewFonctions.getItems().add(e.getEquation());
        }

        categoriesEnBD = new ArrayList<>();

        categoriesEnBD = bdCategorie.GetAllSousCatgeorie();


        ObservableList<Categorie> categories = FXCollections.observableArrayList(//
                categoriesEnBD);

        SpinnerValueFactory<Categorie> valueFactory = //
                new SpinnerValueFactory.ListSpinnerValueFactory<Categorie>(categories);

        valueFactory.setValue(categories.get(0));

        spinnerCategorieEquation.setValueFactory(valueFactory);


    }

    @FXML
    void ajouterEquation(ActionEvent event) {
        String equation = inputEquation.getText();
        List<String> listeViewTemp = new ArrayList<>();

        listeViewTemp.addAll(listViewFonctions.getItems());


        if (listeViewTemp.contains(equation)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Impossible d'ajouter la fontion à la liste!");
            alert.setContentText("La fonction que vous voulez ajouter existe déjà! Veuillez en faire une autre.");
            alert.show();
        } else {
            Equations equations = new Equations(equation);
            equations.setCategorie(spinnerCategorieEquation.getValue());
            listViewFonctions.getItems().add(equation);

            equationService.SaveEquation(equations);

            System.out.println(equationService.GetAllEquations());
        }

    }

    @FXML
    void effacerEquation(ActionEvent event) {
        String itemDelete = listViewFonctions.getSelectionModel().getSelectedItem();

        if(itemDelete == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucune equation selectionnee");
            alert.setContentText("Pour supprimer une fonction, vous devez la sélectionner!");
            alert.show();
        }else{
            listViewFonctions.getItems().remove(itemDelete);
            for (Equations e : equationService.GetAllEquations()
            ) {
                if (itemDelete.equals(e.getEquation())) {
                    equationService.SupprimerEquations(e);
                }
            }
        }



    }

    @FXML
    void modifierEquation(ActionEvent event) {
        String item = listViewFonctions.getSelectionModel().getSelectedItem();
        inputEquation.setText(item);
    }

    @FXML
    public void ajouterSerie(ActionEvent actionEvent) {
        String equation = inputEquation.getText();
        Equations equationsTemp = new Equations(equation);

        int nombreData = Integer.parseInt(nbrData.getText());

        Serie s = null;

        if (inputEquation.getText().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champ incomplet!!!");
            alert.setContentText("Veuillez entrer une fonction au format f(x)=a*x+b");
            alert.show();
        } else {
            s = new Serie();
            ArrayList<Data> donnesAjouter = new ArrayList<>();

            for (int i = 0; i < nombreData; i++) {
                double y = equationsTemp.calculerEquation(equation, i);

                donnesAjouter.add(new Data(i, y));

            }

            s.setDonnees(donnesAjouter);
            s.setNomSerie(equation);
            s.setNomAuteur("inconnue");
            s.setDateCreation(LocalDateTime.now());
            s.setCategorie(spinnerCategorieEquation.getValue());
            serieService.SaveSerie(s);
        }


        System.out.println(s.getDonnees());
    }

    @Override
    public String getNom() {
        return "Editeur  de fonctions";
    }

    public void setStage(ConfigurableApplicationContext context, Serie serie,  List<Serie> series) throws IOException {
        FxWeaver fxWeaver2 = context.getBean(FxWeaver.class);
        FxControllerAndView controllerAndView2 = fxWeaver2.load(EditeurEquationsController.class);
        Parent root2 = (Pane) controllerAndView2.getView().get();
        Scene scene2 = new Scene(root2);
        Stage secondaryStage = new Stage();
        secondaryStage.sizeToScene();
        secondaryStage.resizableProperty().set(false);
        secondaryStage.setTitle(getNom());
        secondaryStage.setScene(scene2);
        secondaryStage.show();

        UIAnimation ui = new UIAnimation();
        ui.deplacerFenetre(secondaryStage, 1, 200, 600, 425);
    }


}
