package Model;

import java.io.IOException;


import javafx.scene.Node;
import Controller.ExtendedRegisterController;

import Controller.RegisterController;
import Controller.SampleController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;


import javafx.stage.Stage;

public class Ventanas {
	
	public String direccionFX="";

	@FXML
    private Button btnCategoria;
	

	 public void MostrarPane(ActionEvent event,String fxmlPath) {

	        try {	
	        	
	        	 Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
	            Parent root = loader.load();
	            Stage primaryStage = new Stage();

	            // Show the scene containing the root layout.
	            Scene scene = new Scene(root);
	            
	           // primaryStage.setFullScreen(true); // este sirve para poder hacer que se muestre en oantalla completa la ventana 
	            primaryStage.setScene(scene);
	            primaryStage.show();
	            
	            
	            currentStage.close();
	        } catch (IOException e) {
	            System.out.println(e.getMessage());
	        }
	    }
    
    
    public void MostrarPane2(String fxmlPath) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Stage primaryStage = new Stage();

            // Show the scene containing the root layout.
            Scene scene = new Scene(root);
          //primaryStage.setFullScreen(true);
            primaryStage.setScene(scene);
            primaryStage.show();
            
         
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    


    public void MostrarPane3(String ruta, RegisterController registerController) {
        try {
            // Carga el archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ruta));
            Parent root = loader.load();

            // Obtiene el controlador de la clase cargada
            ExtendedRegisterController controller = loader.getController();

            // Pasa la instancia de RegisterController
            controller.setRegisterController(registerController);

            // Configura la nueva escena
            Scene scene = new Scene(root);

            // Obtiene la Stage actual (ventana) del RegisterController
            Stage stage = registerController.getStage();

            // Cambia la escena en la Stage actual
            stage.setScene(scene);

            // Muestra la ventana
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


  


      public void mostrarVentana(String fxmlPath, String title) {
      try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
      Parent root = loader.load();
      
      Stage primaryStage = new Stage();
      primaryStage.setTitle(title);
      
      Scene scene = new Scene(root);
     primaryStage.setScene(scene);
      primaryStage.show();
      
      SampleController controlador = loader.getController();
   
     
      } catch (Exception e) {
      e.printStackTrace();
      }
      
      
      }
      
      public void mostrarVentanaDesdeMenu(Stage currentStage, String fxmlPath) {
    	    try {	
    	        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
    	        Parent root = loader.load();
    	        Stage primaryStage = new Stage();

    	        // Show the scene containing the root layout.
    	        Scene scene = new Scene(root);
    	        primaryStage.setScene(scene);
    	        primaryStage.show();
    	        
    	        // Cierra la ventana actual
    	        currentStage.close();
    	    } catch (IOException e) {
    	        System.out.println(e.getMessage());
    	    }
    	}

      
     

}
