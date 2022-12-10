package a22.climoilou.mono2.tp1.rd_pm_ih.controleur;

import a22.climoilou.mono2.tp1.rd_pm_ih.*;
import a22.climoilou.mono2.tp1.rd_pm_ih.repositories.CategorieService;
import a22.climoilou.mono2.tp1.rd_pm_ih.repositories.EquationService;
import a22.climoilou.mono2.tp1.rd_pm_ih.repositories.SerieService;
import a22.climoilou.mono2.tp1.rd_pm_ih.services.TempsPasseDansAppService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Component
@Scope("prototype")
@FxmlView("../vue/monoposte2FXML.fxml")
public class MainController {

    @FXML
    private VBox vBoxBtn;

    @FXML
    private Text textTempsPasse;
    private ConfigurableApplicationContext context;
    private AProposController aProposController;
    private GenerateurController generateurController;
    private ModificateurController modificateurController;
    private EditeurEquationsController editeurEquationsController;
    private TraceurController traceurController;
    private TronqueurController tronqueurController;
    private TableauDesValeursController tableauDesValeursController;
    private AvisController avisController;
    private StatistiquesController statistiquesController;
    private TreeViewBinaireController treeViewBinaireController;
    private SerieService serieService;
    private ArrayList<Categorie> categories;
    private CategorieService categorieService;
    private EquationService equationService;

    @FXML
    private TreeView<TreeItemI> treeViewCategories;

    @Autowired
    public void setSerieService(SerieService serieService) {
        this.serieService = serieService;
    }

    public void setContext(ConfigurableApplicationContext context) {
        this.context = context;
    }

    @Autowired
    public void setEquationService(EquationService equationService) {
        this.equationService = equationService;
    }

    @Autowired
    public void setCategorieService(CategorieService categorieService) {
        this.categorieService = categorieService;
    }

    void ouvrirFenetre(ActionEvent event, Fonctionnalite f) throws IOException {
        Serie serie = getSelectedSerie();
        List<Serie> series = getAllSeries();
        f.setStage(this.context, serie, series);
    }

    @FXML
    private void initialize() {
        lectureFichierStrings();
        ArrayList<Fonctionnalite> fonctionnalites = new ArrayList<>();
        fonctionnalites.add(aProposController);
        fonctionnalites.add(avisController);
        fonctionnalites.add(editeurEquationsController);
        fonctionnalites.add(generateurController);
        fonctionnalites.add(modificateurController);
        fonctionnalites.add(statistiquesController);
        fonctionnalites.add(tableauDesValeursController);
        fonctionnalites.add(traceurController);
        fonctionnalites.add(tronqueurController);
        fonctionnalites.add(treeViewBinaireController);


        for (Fonctionnalite f : fonctionnalites
        ) {
            Button b = new Button(f.getNom());
            b.setStyle("-fx-background-color: darkorchid; -fx-text-fill: white");
            vBoxBtn.getChildren().add(b);
            b.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        ouvrirFenetre(event, f);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }

        Button btnSupprimer = new Button("Supprimer Série");
        btnSupprimer.setStyle("-fx-background-color: darkorchid; -fx-text-fill: white");
        vBoxBtn.getChildren().add(btnSupprimer);
        btnSupprimer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                supprimerItem();
            }
        });

        tempsPasseDansApp();

    }

    private void tempsPasseDansApp() {
        TempsPasseDansAppService service = new TempsPasseDansAppService();

        service.valueProperty().addListener((c, o, n) -> {
            String temps = "";
            int numberOfHours;
            int numberOfMinutes;
            int numberOfSeconds;

            numberOfHours = (n % 86400) / 3600;
            numberOfMinutes = ((n % 86400) % 3600) / 60;
            numberOfSeconds = ((n % 86400) % 3600) % 60;

            if (numberOfHours < 10) {
                temps += "0" + numberOfHours;
            } else if (numberOfHours < 60) {
                temps += numberOfHours;
            }

            temps += ":";

            if (numberOfMinutes < 10) {
                temps += "0" + numberOfMinutes;
            } else if (numberOfMinutes < 60) {
                temps += numberOfMinutes;
            }

            temps += ":";

            if (numberOfSeconds < 10) {
                temps += "0" + numberOfSeconds;
            } else if (numberOfSeconds < 60) {
                temps += numberOfSeconds;
            }

            textTempsPasse.setText(temps);
            service.setLastValue(n);

        });
        service.start();

    }

    @Autowired
    public void setAvisController(AvisController avisController) {
        this.avisController = avisController;
    }

    /**
     * Prends un ou des elements de la liste de serie et les supprime de ls BD
     */

    @FXML
    public void supprimerItem() {
        Alert alert = new Alert(Alert.AlertType.NONE);

        ObservableList<TreeItem<TreeItemI>> listeItems = this.treeViewCategories.getSelectionModel().getSelectedItems();


        if (listeItems.isEmpty()) {
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setContentText("attention vous n'avez pas selectionné d'item'!");
            alert.show();
        } else {
            alert.setAlertType(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Etes vous sure de vouloir supprimer l'item' : ");
            alert.showAndWait().ifPresent(reponse -> {
                if (reponse == ButtonType.OK) {
                    for (TreeItem<TreeItemI> item : listeItems) {
                        if (item.getValue() instanceof Serie) {
                            serieService.SupprimerSerie((Serie) item.getValue());
                        } else if (item.getValue() instanceof Equations) {
                            equationService.SupprimerEquations((Equations) item.getValue());
                        } else {
                            alert.setAlertType(Alert.AlertType.WARNING);
                            alert.setContentText("attention vous ne pouvez pas supprimer une cetégorie'!");
                            alert.show();
                        }
                    }
                }
            });
        }

        lectureFichierStrings();

    }


    @FXML
    void refreshTable(MouseEvent event) {
        treeViewCategories.refresh();
    }


    /**
     * Prend toutes les séries sélectionnée
     *
     * @return la premiere des séries sélectionnées
     */
    public Serie getSelectedSerie() {
        Serie s = null;
        ObservableList<TreeItem<TreeItemI>> listeItems = treeViewCategories.getSelectionModel().getSelectedItems();

        if (listeItems.size() != 0) {
            if (listeItems.get(0).getValue() instanceof Serie) {
                s = (Serie) treeViewCategories.getSelectionModel().getSelectedItems().get(0).getValue();
            }
        } else {
            return null;
        }

        return s;
    }

    /**
     * Prend toutes les séries sélectionnés
     *
     * @return toutes les séries.
     */
    public List<Serie> getAllSeries() {
        ObservableList<TreeItem<TreeItemI>> listeItem = treeViewCategories.getSelectionModel().getSelectedItems();
        List<Serie> liste = new ArrayList<>();

        if (listeItem.size() != 0) {
            for (TreeItem<TreeItemI> i : listeItem) {
                if (i.getValue() instanceof Serie) {
                    liste.add((Serie) i.getValue());
                } else {
                    liste.clear();
                    break;
                }
            }
        }

        if (liste.size() == 0) {
            return null;
        }

        return liste;
    }

    @Autowired
    public void setAProposController(AProposController aProposController) {
        this.aProposController = aProposController;
    }

    @Autowired
    public void setGenerateurController(GenerateurController generateurController) {
        this.generateurController = generateurController;
    }

    @Autowired
    public void setModificateurController(ModificateurController modificateurController) {
        this.modificateurController = modificateurController;
    }

    @Autowired
    public void setEditeurController(EditeurEquationsController editeurEquationsController) {
        this.editeurEquationsController = editeurEquationsController;
    }

    @Autowired
    public void setTraceurController(TraceurController traceurController) {
        this.traceurController = traceurController;
    }

    @Autowired
    public void setTronqueurController(TronqueurController tronqueurController) {
        this.tronqueurController = tronqueurController;
    }

    @Autowired
    public void setTableauDesValeursController(TableauDesValeursController tableauDesValeursController) {
        this.tableauDesValeursController = tableauDesValeursController;
    }

    @Autowired
    public void setStatistiquesController(StatistiquesController statistiquesController) {
        this.statistiquesController = statistiquesController;
    }

    @Autowired
    public void setTreeViewBinaireController(TreeViewBinaireController treeViewBinaireController) {
        this.treeViewBinaireController = treeViewBinaireController;
    }

    public void createTreeView(TreeItem root) {

        Recursives recursives = new Recursives();

        for (Serie s : serieService.GetAllSerie()
        ) {
            TreeItem<Serie> serieItem = new TreeItem<Serie>(s);

            recursives.remplirArbreSeries(root, serieItem, s);
        }

        for (Equations e : equationService.GetAllEquations()
        ) {
            TreeItem<Equations> serieItem = new TreeItem<>(e);

            recursives.remplirArbreEquation(root, serieItem, e);
        }


    }


    private void lectureFichierStrings() {
        List<Categorie> listeDeCategorie = new ArrayList<>();
        List<TreeItem> listeDarbre = new ArrayList<>();

        try {
            // Le fichier d'entrée
            FileInputStream file = new FileInputStream("categories.txt");
            Scanner scanner = new Scanner(file);

            //Si il y a une prochaine ligne
            while (scanner.hasNextLine()) {

                if (scanner.hasNext()) {
                    //String prochaineLigne = scanner.nextLine();
                    int indexParent = 0;
                    int indexEnfant = 0;

                    List<String> listeDeStringSansCrochet = List.of(scanner.nextLine().split("/"));
                    Categorie c = new Categorie(listeDeStringSansCrochet.get(listeDeStringSansCrochet.size() - 1));
                    TreeItem<TreeItemI> iAmRoot = new TreeItem<>(c);
                    listeDeCategorie.add(c);
                    listeDarbre.add(iAmRoot);

                    if (listeDeStringSansCrochet.size() != 1) {
                        String parent = listeDeStringSansCrochet.get(listeDeStringSansCrochet.size() - 2);
                        String enfant = listeDeStringSansCrochet.get(listeDeStringSansCrochet.size() - 1);

                        for (int i = 0; i < listeDeCategorie.size(); i++) {
                            if (listeDeCategorie.get(i).getNom().equals(parent)) {
                                indexParent = i;
                            }

                            if (listeDeCategorie.get(i).getNom().equals(enfant)) {
                                indexEnfant = i;
                            }

                        }

                        listeDeCategorie.get(indexParent).setSousCategorie(listeDeCategorie.get(indexEnfant));
                        listeDarbre.get(indexParent).getChildren().add(listeDarbre.get(indexEnfant));


                        listeDarbre.get(0).setExpanded(true);
                        this.treeViewCategories.setRoot(listeDarbre.get(0));

                        this.treeViewCategories.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

                        this.treeViewCategories.setCellFactory((a) -> {
                            CustomTreeCell retCell = new CustomTreeCell();
                            return retCell;
                        });

                    }
                }
            }

/*
            for (Categorie c : listeDeCategorie
            ) {
                categorieService.saveCategorie(c);
            }
*/

            createTreeView(listeDarbre.get(0));
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

