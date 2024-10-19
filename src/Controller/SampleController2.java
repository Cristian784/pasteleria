
package Controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.TextField;
//import java.awt.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import Controller.SampleController;
import Model.Ventanas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.util.Duration;

public class SampleController2 extends UtilitariaNavegabilidad implements Initializable {
	
	 /* @FXML
	    private TextField TextFieldHora;
	  @FXML
	    private Button btnFecha;*/

	@FXML
    private TextField TextFieldFH;
	@FXML
    private Button btnReporte;
	@FXML
    private Button btnHoraFecha;
	
	@FXML
    private Button btnCaja;

    @FXML
    private Button btnCatalogo;

    @FXML
    private Button btnCategoria;

    @FXML
    private Button btnClientes;

    @FXML
    private Button btnPasteles;

    @FXML
    private Button btnPedidos;

    @FXML
    private Button btnUsuarios;

    @FXML
    private Button btnVentaPastel;

    @FXML
    private Button btnVentas;

    @FXML
    private MenuItem itemCerrar;

    @FXML
    private MenuItem itemNocturno;

    @FXML
    private MenuButton menuopciones;
   

   


    

    
   /* public void RegistroSig(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/Register2.fxml"));

            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();

            Stage myStage = (Stage) this.btnCategoria.getScene().getWindow();
            myStage.close();

        } catch (IOException ex) {
            Logger.getLogger(ExtendedRegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }*/    
    
   
  

  

    

    

    
    @FXML
 // Método para mostrar la fecha y hora actual en el TextField
    void mostrarHoraFecha(ActionEvent event) {
    	// Obtener la fecha y hora actual
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Formatear la fecha y hora según tus preferencias
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd    HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);

        // Establecer el texto formateado en el TextField
        TextFieldFH.setText(formattedDateTime);

    } 
    
    
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
    	// Crear un Timeline para actualizar automáticamente la hora cada segundo
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> mostrarHoraFecha(event)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }
    
    
    
    
    
    
}
