package a22.climoilou.mono2.tp1.rd_pm_ih;

import javafx.scene.Scene;

import javax.swing.text.View;
import java.io.IOException;

public interface IVue {

    public Scene getScene() throws IOException;

    public View getVue();

}
