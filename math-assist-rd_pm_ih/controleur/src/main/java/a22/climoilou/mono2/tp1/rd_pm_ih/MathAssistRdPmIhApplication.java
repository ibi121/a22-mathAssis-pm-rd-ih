package a22.climoilou.mono2.tp1.rd_pm_ih;


import javafx.application.Application;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication
@EntityScan("a22.climoilou.mono2.tp1.rd_pm_ih")
public class MathAssistRdPmIhApplication{


	private BD bd;



	@Autowired
	public void setBd(BD bd) {
		this.bd = bd;
	}

	public static void main(String[] args) {
		Application.launch(ApplicationFX.class, args);
	}

}
