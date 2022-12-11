package a22.climoilou.mono2.tp1.rd_pm_ih.controleur;

import a22.climoilou.mono2.tp1.rd_pm_ih.Categorie;
import a22.climoilou.mono2.tp1.rd_pm_ih.Serie;
import a22.climoilou.mono2.tp1.rd_pm_ih.TreeItemI;
import a22.climoilou.mono2.tp1.rd_pm_ih.repositories.CategorieService;
import a22.climoilou.mono2.tp1.rd_pm_ih.services.UIAnimation;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxControllerAndView;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@FxmlView("../vue/TreeViewFXML.fxml")
public class TreeViewBinaireController implements Fonctionnalite{

    private CategorieService categorieService;

    @FXML
    private TreeView<TreeItemI> treeViewBinaire;

    @Autowired
    public void setCategorieService(CategorieService categorieService) {
        this.categorieService = categorieService;
    }


    @FXML
    private void initialize() {
        List<Categorie> categories = new ArrayList<>();
        List<TreeItem> treeItems = new ArrayList<>();


        categories = categorieService.GetAllSousCatgeorie().stream().collect(Collectors.toList());
        treeItems = categorieService.GetAllSousCatgeorie().stream().map(TreeItem::new).collect(Collectors.toList());

        for (int i = 0; i < categories.size(); i++) {
            if(!categories.get(i).getSousCategorie().isEmpty()){
                if(categories.get(i).getSousCategorie().size() == 1){
                    treeItems.get(i).getChildren().add(treeItems.get(i+1));
                }else{
                    for (int j = 0; j < categories.get(i).getSousCategorie().size(); j++) {
                        if(j == 0){
                            treeItems.get(i).getChildren().add(treeItems.get(i + 1));
                        }else{
                            treeItems.get(i + 1).getChildren().add(treeItems.get(categories.indexOf(categories.get(i).getSousCategorie().get(j))));
                        }

                    }
                }
            }
        }

        for (TreeItem item:treeItems
        ) {
            item.setExpanded(true);
        }

        this.treeViewBinaire.setRoot(treeItems.get(0));
        this.treeViewBinaire.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


    }


    @Override
    public String getNom() {
        return "Arbre binaire dérivé";
    }

    @Override
    public void setStage(ConfigurableApplicationContext c, Serie serie, List<Serie> series) throws IOException {
        FxWeaver fxWeaver2 = c.getBean(FxWeaver.class);
        FxControllerAndView controllerAndView2 = fxWeaver2.load(TreeViewBinaireController.class);
        Parent root2 = (AnchorPane) controllerAndView2.getView().get();
        Scene scene2 = new Scene(root2);
        Stage secondaryStage = new Stage();
        secondaryStage.sizeToScene();
        secondaryStage.setResizable(false);
        secondaryStage.setTitle(getNom());
        secondaryStage.setScene(scene2);
        secondaryStage.show();

        UIAnimation ui = new UIAnimation();
        ui.deplacerFenetre(secondaryStage, 1, 1, 400, 400);
    }
}
