package a22.climoilou.mono2.tp1.rd_pm_ih.controleur;

import a22.climoilou.mono2.tp1.rd_pm_ih.Serie;
import org.springframework.context.ConfigurableApplicationContext;

import javax.naming.Context;
import java.io.IOException;
import java.util.List;

public interface Fonctionnalite {

    public String getNom();

    public void setStage(ConfigurableApplicationContext c, Serie s,  List<Serie> series) throws IOException;
}
