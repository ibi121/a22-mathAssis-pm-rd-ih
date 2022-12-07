package a22.climoilou.mono2.tp1.rd_pm_ih.controleur;

import a22.climoilou.mono2.tp1.rd_pm_ih.Categorie;
import a22.climoilou.mono2.tp1.rd_pm_ih.Equations;
import a22.climoilou.mono2.tp1.rd_pm_ih.Serie;
import a22.climoilou.mono2.tp1.rd_pm_ih.TreeItemI;
import a22.climoilou.mono2.tp1.rd_pm_ih.repositories.CategorieService;
import a22.climoilou.mono2.tp1.rd_pm_ih.repositories.EquationService;
import a22.climoilou.mono2.tp1.rd_pm_ih.repositories.SerieService;
import a22.climoilou.mono2.tp1.rd_pm_ih.services.UIAnimation;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxControllerAndView;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
@FxmlView("../vue/TreeViewFXML.fxml")
public class TreeViewController implements Fonctionnalite {

    @FXML
    private TreeView<TreeItemI> treeViewCategories;

    private ArrayList<String> fichierLignes;

    private ArrayList<String> fichierString;

    private ArrayList<Categorie> categories;

    private CategorieService categorieService;

    private SerieService serieService;

    private EquationService equationService;

    @Autowired
    public void setEquationService(EquationService equationService) {
        this.equationService = equationService;
    }

    @Autowired
    public void setSerieService(SerieService serieService) {
        this.serieService = serieService;
    }

    @Autowired
    public void setCategorieService(CategorieService categorieService) {
        this.categorieService = categorieService;
    }

    @FXML
    public void setStage(ConfigurableApplicationContext context, Serie s, List<Serie> series) throws IOException {

        FxWeaver fxWeaver = context.getBean(FxWeaver.class);
        FxControllerAndView controllerAndView = fxWeaver.load(TreeViewController.class);
        Parent root = (AnchorPane) controllerAndView.getView().get();
        Stage secondaryStage = new Stage();
        secondaryStage.setTitle("Arbre des catégories");
        secondaryStage.setScene(new Scene(root));
        secondaryStage.show();

        UIAnimation ui = new UIAnimation();
        ui.deplacerFenetre(secondaryStage, 1, 1, 400, 400);
    }

    @FXML
    private void initialize() {
        fichierLignes = lectureFichierLignes();
        fichierString = lectureFichierStrings();
        creationDesCategories();
        createTreeView();
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

            remplirArbreSeries(root, serieItem, s);
        }

        for (Equations e : equationService.GetAllEquations()
        ) {
            TreeItem<Equations> serieItem = new TreeItem<>(e);

            remplirArbreEquation(root, serieItem, e);
        }


        root.setExpanded(true);
        this.treeViewCategories.setRoot(root);

        this.treeViewCategories.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

/*
        treeView.setCellFactory((a) -> {
            CustomTreeCell retCell = new CustomTreeCell();
            return retCell;
        });

 */
    }

    private void remplirArbreSeries(TreeItem<TreeItemI> root, TreeItem item, Serie s) {
        remplirArbreSeriePureRec(root, item, s);
    }

    private void remplirArbreSeriePureRec(TreeItem<TreeItemI> actualNode, TreeItem item, Serie s) {

        String node = String.valueOf(actualNode.getValue().getNom());
        String cat = s.getCategorie().getNom();
        if (node.equals(cat)) {
            actualNode.getChildren().add(item);
        } else {

            if (actualNode.nextSibling() != null) {
                remplirArbreSeriePureRec(actualNode.nextSibling(), item, s);
            }

            if (!actualNode.isLeaf() && actualNode.getValue() instanceof Categorie) {
                remplirArbreSeriePureRec(actualNode.getChildren().get(0), item, s);
            }
        }

    }

    private void remplirArbreEquation(TreeItem<TreeItemI> root, TreeItem item, Equations e) {
        remplirArbreEquationPureRec(root, item, e);
    }

    private void remplirArbreEquationPureRec(TreeItem<TreeItemI> actualNode, TreeItem item, Equations e) {

        String node = String.valueOf(actualNode.getValue().getNom());
        String cat = e.getCategorie().getNom();
        if (node.equals(cat)) {
            actualNode.getChildren().add(item);
        } else {

            if (actualNode.nextSibling() != null) {
                remplirArbreEquationPureRec(actualNode.nextSibling(), item, e);
            }

            if (!actualNode.isLeaf() && actualNode.getValue() instanceof Categorie) {
                remplirArbreEquationPureRec(actualNode.getChildren().get(0), item, e);
            }
        }

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

            scanner.useDelimiter("/|\\r?\\n");


            //Si il y a une prochaine ligne
            while (scanner.hasNext()) {
                String element = scanner.next();

                categoriesElements.add(element);
            }

            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return categoriesElements;
    }

    @Override
    public String getNom() {
        return "TreeView";
    }
}

