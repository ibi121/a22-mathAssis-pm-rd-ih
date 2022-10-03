package a22.climoilou.mono2.tp1.rd_pm_ih;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AProposController implements IVue{

    public void setStage(Scene scene) throws IOException {
        Stage secondaryStage = new Stage();
        secondaryStage.setTitle("Des singeries");
        secondaryStage.setScene(scene);
        secondaryStage.show();
    }

    @Override
    public Scene getScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("AProposFXML.fxml"));
        Parent root = fxmlLoader.load();
        return new Scene(root);
    }
}

