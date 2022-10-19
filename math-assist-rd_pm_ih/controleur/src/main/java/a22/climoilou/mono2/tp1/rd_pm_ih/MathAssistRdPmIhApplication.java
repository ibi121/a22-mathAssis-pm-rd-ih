package a22.climoilou.mono2.tp1.rd_pm_ih;


import a22.climoilou.mono2.tp1.rd_pm_ih.controleur.GenerateurController;
import a22.climoilou.mono2.tp1.rd_pm_ih.controleur.MainController;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
@EntityScan("a22.climoilou.mono2.tp1.rd_pm_ih")
public class MathAssistRdPmIhApplication implements CommandLineRunner {

	public static void main(String[] args) {
		Application.launch(FXController.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
