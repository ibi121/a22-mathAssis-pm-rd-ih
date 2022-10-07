package a22.climoilou.mono2.tp1.rd_pm_ih.controleur;

import a22.climoilou.mono2.tp1.rd_pm_ih.repositories.DataRepository;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MathAssistRdPmIhApplication extends Application implements CommandLineRunner {

	private MainController iMainController = null;

	private DataRepository dataRepository;




	public static void main(String[] args) {
		SpringApplication.run(MathAssistRdPmIhApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
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
