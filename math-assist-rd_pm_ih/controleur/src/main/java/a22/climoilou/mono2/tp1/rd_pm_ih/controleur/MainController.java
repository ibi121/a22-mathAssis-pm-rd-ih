package a22.climoilou.mono2.tp1.rd_pm_ih.controleur;

import a22.climoilou.mono2.tp1.rd_pm_ih.*;
import a22.climoilou.mono2.tp1.rd_pm_ih.repositories.CategorieService;
import a22.climoilou.mono2.tp1.rd_pm_ih.repositories.EquationService;
import a22.climoilou.mono2.tp1.rd_pm_ih.repositories.SerieService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
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
    private ArrayList<String> fichierLignes;
    private ArrayList<String> fichierString;
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
        fichierLignes = lectureFichierLignes();
        fichierString = lectureFichierStrings();
        creationDesCategories();
        createTreeView();
        //lectureFichierLignes();

        //initialisation des boutons

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
                        if(item.getValue() instanceof Serie){
                            serieService.SupprimerSerie((Serie) item.getValue());
                        }else if(item.getValue() instanceof Equations){
                            equationService.SupprimerEquations((Equations) item.getValue());
                        }else {
                            alert.setAlertType(Alert.AlertType.WARNING);
                            alert.setContentText("attention vous ne pouvez pas supprimer une cetégorie'!");
                            alert.show();
                        }
                    }
                }
            });
        }

        createTreeView();

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

    private void creationDesCategories() {
        this.categories = new ArrayList<>();
        Categorie root = new Categorie("");

        for (int i = 0; i < this.fichierString.size(); i++) {

            if (this.categories.isEmpty()) {
                root.setNom(fichierString.get(i));
                this.categories.add(root);
            } else {
                if (root.getNom().equals(fichierString.get(i))) {
                    if (root.getSousCategorie().isEmpty()) {
                        Categorie c = new Categorie(this.fichierString.get(i + 1));
                        root.setSousCategorie(c);
                    }
                }
            }

        }

        System.out.println(root.toString());

    }

    public void createTreeView() {

        Recursives recursives = new Recursives();

        Categorie categorie1 = new Categorie("Categorie1");
        Categorie categorie2 = new Categorie("Categorie2");
        Categorie categorie3 = new Categorie("Categorie3");
        Categorie categorieA = new Categorie("CategorieA");
        Categorie categorieB = new Categorie("CategorieB");

        categorie2.setSousCategorie(categorieA);
        categorie1.setSousCategorie(categorie2);
        categorie3.setSousCategorie(categorieB);
        categorie1.setSousCategorie(categorie3);

        //categorieService.saveCategorie(categorie1);


        TreeItem root = new TreeItem<>(categorie1);

        TreeItem sousCat2 = new TreeItem<>(categorie2);

        TreeItem sousCat3 = new TreeItem(categorie3);

        TreeItem sousCatA = new TreeItem(categorieA);

        TreeItem sousCatB = new TreeItem(categorieB);

        sousCat2.getChildren().add(sousCatA);
        sousCat3.getChildren().add(sousCatB);
        root.getChildren().addAll(sousCat2, sousCat3);

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


        root.setExpanded(true);
        this.treeViewCategories.setRoot(root);

        this.treeViewCategories.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }


    private ArrayList<String> lectureFichierLignes() {
        ArrayList<String> categoriesLignes = new ArrayList<>();
        try {
            // Le fichier d'entrée
            FileInputStream file = new FileInputStream("categories.txt");
            Scanner scanner = new Scanner(file);

            //scanner.useDelimiter("/|\\r?\\n");


            //Si il y a une prochaine ligne
            while (scanner.hasNextLine()) {

                String ligne = scanner.nextLine();

                categoriesLignes.add(ligne);
            }

            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return categoriesLignes;
    }

    private ArrayList<String> lectureFichierStrings() {
        ArrayList<String> categoriesElements = new ArrayList<>();
        try {
            // Le fichier d'entrée
            FileInputStream file = new FileInputStream("categories.txt");
            Scanner scanner = new Scanner(file);


            //Si il y a une prochaine ligne
          if(scanner.hasNextLine()){
              String root = scanner.nextLine();
              Categorie c1 = new Categorie(root);
              String prochaineLigne = scanner.nextLine();

              if(prochaineLigne.contains(root)){
                 List<String> listeDeStringSansCrochet = List.of(prochaineLigne.split("/"));

                  for (int i = 0; i < listeDeStringSansCrochet.size(); i++) {
                      if(listeDeStringSansCrochet.get(0).equals(c1.getNom())){
                          c1.setSousCategorie(new Categorie(String.valueOf(listeDeStringSansCrochet.get(1))));
                          if(!(listeDeStringSansCrochet.get(i).equals(c1.getSousCategorie().get(0).getNom()))){
                              System.out.println("j'ai la meme sous cat");
                          }

                      }

                  }



                  System.out.println(c1.getSousCategorie().toString());
              }

          }

            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return categoriesElements;
    }

}

