package a22.climoilou.mono2.tp1.rd_pm_ih.controleur;

import a22.climoilou.mono2.tp1.rd_pm_ih.Data;
import a22.climoilou.mono2.tp1.rd_pm_ih.Serie;
import a22.climoilou.mono2.tp1.rd_pm_ih.repositories.BD;
import a22.climoilou.mono2.tp1.rd_pm_ih.repositories.DataRepository;
import a22.climoilou.mono2.tp1.rd_pm_ih.repositories.SerieRepository;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@SpringBootApplication
public class MathAssistRdPmIhApplication extends Application implements CommandLineRunner {

	private MainController iMainController = null;

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
