package a22.climoilou.mono2.tp1.rd_pm_ih.controleur;

import a22.climoilou.mono2.tp1.rd_pm_ih.vue.TraceurGraphique;
import a22.climoilou.mono2.tp1.rd_pm_ih.vue.TraceurI;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class TraceurController implements TraceurI {

    private TraceurGraphique traceurGraphique;

    public TraceurController() {
        traceurGraphique = new TraceurGraphique();
        traceurGraphique.setTraceurI(this);
    }

    public TraceurGraphique getTraceurGraphique() {
        return traceurGraphique;
    }

    public void setStage() throws IOException {
        Stage secondaryStage = new Stage();
        secondaryStage.setTitle("Traceur de séries");
        secondaryStage.setScene(traceurGraphique.getScene());
        secondaryStage.show();
    }

    /**
     * //HashMap en attendant d'avoir les modeles
     * @return
     */
    @Override
    public HashMap<String, Integer> getSeries() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("0", 4);
        map.put("1", 3);
        map.put("2", 2);
        map.put("3", 1);
        map.put("4", 0);
        return map;
    }
}

