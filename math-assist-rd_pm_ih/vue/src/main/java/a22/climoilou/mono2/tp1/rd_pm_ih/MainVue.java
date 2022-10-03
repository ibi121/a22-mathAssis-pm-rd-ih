package main.java.a22.climoilou.mono2.tp1.rd_pm_ih;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * Hello world!
 * :o)
 */
public class MainVue {


    @FXML
    private VBox vBox1;

    @FXML
    private Pane paneGauche;

    @FXML
    private HBox hBox1;

    @FXML
    private Button btnAPropos;

    @FXML
    private Pane paneDroit;

    @FXML
    private SplitPane splitPanePrincipal;

    @FXML
    private Button btnValiderSerie;

    @FXML
    private Button btnRandom;

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }

    @Override
    public Scene getScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("monoposte2FXML.fxml"));
        Parent root = fxmlLoader.load();
        return new Scene(root);
    }
}
