package a22.climoilou.mono2.tp1.rd_pm_ih.controleur;

import a22.climoilou.mono2.tp1.rd_pm_ih.Serie;
import a22.climoilou.mono2.tp1.rd_pm_ih.services.UIAnimation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxControllerAndView;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@FxmlView("../vue/TreeViewBinaire.fxml")
public class TreeViewBinaireController implements Fonctionnalite{



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
