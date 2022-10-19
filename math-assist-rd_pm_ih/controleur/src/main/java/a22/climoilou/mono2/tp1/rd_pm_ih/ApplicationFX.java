package a22.climoilou.mono2.tp1.rd_pm_ih;

import a22.climoilou.mono2.tp1.rd_pm_ih.controleur.MainController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxControllerAndView;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class ApplicationFX extends Application {

    private ConfigurableApplicationContext context;

    @Override
    public void init() throws Exception {
        String[] args = getParameters().getRaw().toArray(new String[0]);
        this.context = new SpringApplicationBuilder().sources(MathAssistRdPmIhApplication.class).run(args);
    }

    @Override
    public void stop() throws Exception {
        this.context.close();
        Platform.exit();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FxWeaver fxWeaver = context.getBean(FxWeaver.class);
        FxControllerAndView controllerAndView = fxWeaver.load(MainController.class);
        Parent root = (SplitPane) controllerAndView.getView().get();
        MainController fxController = (MainController) controllerAndView.getController();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
