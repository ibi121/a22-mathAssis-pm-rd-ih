package a22.climoilou.mono2.tp1.rd_pm_ih.controleur;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MathAssistRdPmIhApplication extends Application {

	private MainController iMainController = null;




	public static void main(String[] args) {
		//SpringApplication.run(MathAssistRdPmIhApplication.class, args);
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.iMainController = new MainController();
		primaryStage.setTitle("Des singeries");
		primaryStage.setScene(iMainController.getScene());
		primaryStage.show();
	}
}
