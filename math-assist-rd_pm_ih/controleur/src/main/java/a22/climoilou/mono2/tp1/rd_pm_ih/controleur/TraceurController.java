package a22.climoilou.mono2.tp1.rd_pm_ih.controleur;

import a22.climoilou.mono2.tp1.rd_pm_ih.Serie;
import a22.climoilou.mono2.tp1.rd_pm_ih.vue.TraceurGraphique;
import a22.climoilou.mono2.tp1.rd_pm_ih.vue.TraceurI;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Component
public class TraceurController implements TraceurI {
    private TraceurGraphique traceurGraphique;

    @Autowired
    public void setTraceurGraphique(TraceurGraphique traceurGraphique) {
        this.traceurGraphique = traceurGraphique;
    }

    public void setStage(ConfigurableApplicationContext context) throws IOException {
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

