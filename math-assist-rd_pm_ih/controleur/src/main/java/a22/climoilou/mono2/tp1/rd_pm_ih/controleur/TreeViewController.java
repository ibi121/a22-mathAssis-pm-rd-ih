package a22.climoilou.mono2.tp1.rd_pm_ih.controleur;

import a22.climoilou.mono2.tp1.rd_pm_ih.Categorie;
import a22.climoilou.mono2.tp1.rd_pm_ih.Serie;
import a22.climoilou.mono2.tp1.rd_pm_ih.vue.TraceurGraphique;
import javafx.fxml.FXML;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxControllerAndView;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@FxmlView("../vue/TreeViewFXML.fxml")
public class TreeViewController implements Fonctionnalite {

    @FXML
    private TreeView<String> treeViewCategories;

    @FXML
    public void setStage(ConfigurableApplicationContext context) throws IOException {

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
        createTreeView();
    }

    public void createTreeView() {

        Categorie sousCategorie1 = new Categorie("test1", null);
        Categorie test1 = new Categorie("test2", sousCategorie1);

        TreeItem root = new TreeItem<String>(test1.getNom());

        TreeItem sousCat = new TreeItem<String>(test1.getSousCategorie().getNom());


        root.getChildren().addAll(sousCat);
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
    @Override
    public String getNom() {
        return null;
    }
}

