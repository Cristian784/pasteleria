package Controller;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import javafx.scene.Node;


public class EmpleadoController extends UtilitariaNavEmpleado implements Initializable {
	@FXML
	private MenuButton menuopciones;
	private Stage primaryStage;
	
	
	
	
	public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }


	@FXML
	void openEstadoPedido(ActionEvent event) {

	}

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
	}

}
