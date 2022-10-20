package a22.climoilou.mono2.tp1.rd_pm_ih.origine;

import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.List;

public interface Fonctionnalite {
    public String getNom();

    public void setStage(ConfigurableApplicationContext context)throws IOException;
}
