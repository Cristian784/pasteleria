package Controller;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;



public class Sample2Noc extends UtilitariaNavegabilidad implements Initializable  {

    @FXML
    private Button btnCategoria;

    
    @FXML
    private Button btnClientes;

    @FXML
    private Button btnPasteles;

    @FXML
    private Button btnPedidos;

    @FXML
    private Button btnVentaPastel;

    @FXML
    private Button btnVentas;

    @FXML
    private MenuItem itemNocturno;

    @FXML
    private MenuButton menuopciones;

    @FXML
    private MenuItem itemCerrar;


 
   

    @FXML
    void cambiarVista(ActionEvent event) {
      
            Pane.MostrarPane(event, "/Vista/Sample2.fxml");
           

    }

   
   

  

   
    
    
    
    

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub

    }

    
    
    
}
