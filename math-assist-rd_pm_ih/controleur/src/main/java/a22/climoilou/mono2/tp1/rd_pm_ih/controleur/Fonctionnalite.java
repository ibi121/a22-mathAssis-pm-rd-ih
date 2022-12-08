package a22.climoilou.mono2.tp1.rd_pm_ih.controleur;

import a22.climoilou.mono2.tp1.rd_pm_ih.Serie;
import a22.climoilou.mono2.tp1.rd_pm_ih.TreeItemI;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.List;

public interface Fonctionnalite {

    public String getNom();

    public void setStage(ConfigurableApplicationContext c, Serie serie, List<Serie> series) throws IOException;
}
