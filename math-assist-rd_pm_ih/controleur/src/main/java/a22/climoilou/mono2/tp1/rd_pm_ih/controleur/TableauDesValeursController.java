package a22.climoilou.mono2.tp1.rd_pm_ih.controleur;

import a22.climoilou.mono2.tp1.rd_pm_ih.Data;
import a22.climoilou.mono2.tp1.rd_pm_ih.Serie;
import a22.climoilou.mono2.tp1.rd_pm_ih.origine.Fonctionnalite;
import a22.climoilou.mono2.tp1.rd_pm_ih.repositories.SerieService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
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

@Component
@Scope("prototype")
@FxmlView("../vue/TableauValeurs.fxml")
public class TableauDesValeursController implements Fonctionnalite {

    public GridPane tableau;
    @FXML
    private Button btnChangeData;

    @FXML
    private Text serieChoisie;


    private SerieService serieService;

    private long serieSelectionnee = 88;

    @Autowired
    public void setSerieService(SerieService serieService) {
        this.serieService = serieService;
    }

    @FXML
    private void initialize(){
       serieChoisie.setText(serieService.FindSerieById(serieSelectionnee).getNomSerie());

        System.out.println(serieService.FindSerieById(serieSelectionnee));

    /*    for(int i = 0; i < serieService.FindSerieById(serieSelectionnee).getDonnees().size(); i++){
            Text dataX = new Text();
            Text dataY = new Text();
            dataX.setText(String.valueOf(serieService.FindSerieById(serieSelectionnee).getDonnees().get(i).getX()));
            dataY.setText(String.valueOf(serieService.FindSerieById(serieSelectionnee).getDonnees().get(i).getY()));
            tableau.addColumn(i+1);
            tableau.add(dataX, i+1, 0);
            tableau.add(dataY, i+1, 1);
        }
*/
    }
    @FXML
    void changeData(ActionEvent event) {

    }

    @Override
    public String getNom() {
        return "Tableau des valeurs";
    }

    public void setStage(ConfigurableApplicationContext context) {
        FxWeaver fxWeaver2 = context.getBean(FxWeaver.class);
        FxControllerAndView controllerAndView2 = fxWeaver2.load(TableauDesValeursController.class);
        Parent root2 = (Pane) controllerAndView2.getView().get();
        Scene scene2 = new Scene(root2);
        Stage secondaryStage = new Stage();
        secondaryStage.setTitle(getNom());
        secondaryStage.setScene(scene2);
        secondaryStage.show();
    }
}
