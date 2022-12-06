package a22.climoilou.mono2.tp1.rd_pm_ih.services;

import javafx.stage.Stage;
import javafx.util.Duration;

public class UIAnimation {

    public void deplacerFenetre(Stage stage, double deltaX, double deltaY) {
        WindowAnimationService windowAnimationService = new WindowAnimationService();

        windowAnimationService.setPeriod(Duration.seconds(0.03));

        windowAnimationService.lastValueProperty().addListener((c, o, n) -> {
            if (n != null) {
                stage.setX(n.getX());
                stage.setY(n.getY());
            }
        });

        WindowAnimationService.Location souhaite = new WindowAnimationService.Location(
                deltaX,
                deltaY

        );

        WindowAnimationService.Location depart = new WindowAnimationService.Location(
                stage.getX(),
                stage.getY()
        );

        windowAnimationService.setActuelle(depart);
        windowAnimationService.setSouhaitee(souhaite);
        windowAnimationService.restart();
    }
}
