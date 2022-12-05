package a22.climoilou.mono2.tp1.rd_pm_ih.controleur;

import a22.climoilou.mono2.tp1.rd_pm_ih.Categorie;
import a22.climoilou.mono2.tp1.rd_pm_ih.Serie;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxControllerAndView;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
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
    private TreeView<String> treeViewCategories;

    private ArrayList<String> fichierLut;

    @FXML
    public void setStage(ConfigurableApplicationContext context, Serie s,  List<Serie> series) throws IOException {

        FxWeaver fxWeaver = context.getBean(FxWeaver.class);
        FxControllerAndView controllerAndView = fxWeaver.load(TreeViewController.class);
        Parent root = (AnchorPane) controllerAndView.getView().get();
        Stage secondaryStage = new Stage();
        secondaryStage.setTitle("Arbre des catégories");
        secondaryStage.setScene(new Scene(root));
        secondaryStage.show();
    }

    @FXML
    private void initialize(){
        fichierLut = lectureFichierCategories();
        createTreeView();
    }

    public void createTreeView() {

        ArrayList<Categorie> listeCategories = new ArrayList<>();

        for(int i = 0; i < fichierLut.size(); i++){
            Categorie c = new Categorie(fichierLut.get(i));
            listeCategories.add(c);
        }

        System.out.println(listeCategories);

        //Categorie sousCategorie1 = new Categorie("test1", null);
        //Categorie test1 = new Categorie("test2", sousCategorie1);

        //TreeItem root = new TreeItem<String>(test1.getNom());

        //TreeItem sousCat = new TreeItem<String>(test1.getSousCategorie().getNom());


        //root.getChildren().addAll(sousCat);
        //root.setExpanded(true);
        //this.treeViewCategories.setRoot(root);

        this.treeViewCategories.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

/*
        treeView.setCellFactory((a) -> {
            CustomTreeCell retCell = new CustomTreeCell();
            return retCell;
        });

 */
    }

    private ArrayList<String> lectureFichierCategories(){
        ArrayList<String> listeCategories = new ArrayList<>();

        try
        {
            // Le fichier d'entrée
            FileInputStream file = new FileInputStream("categories.txt");
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter("/");
            Categorie categorie;

            //renvoie true s'il y a une autre ligne à lire

            categorie = new Categorie(scanner.next());

            categorie.getSousCategorie().add(new Categorie(scanner.next()));
            System.out.println(categorie);

            scanner.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }


        return listeCategories;
    }

    @Override
    public String getNom() {
        return "TreeView";
    }
}

