package Controller;


import Model.Ventanas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import javafx.scene.Node;



public abstract class UtilitariaNavegabilidad {
	
	private Ventanas navegabilidad;
	
	
	@FXML
	protected Button btnMenu;

	@FXML
	protected Button btnEliminar;
	@FXML
	protected Button btnGuardar;
	@FXML
	protected Button btnActualizar;
	@FXML
	private Button btnRecargar;
	@FXML
	protected Button btnBuscar;
	
	  @FXML
	protected TextField TextFieldFecha1;
		@FXML
		protected TextField txtFolio;
	  
	  @FXML
		protected TextField TxtBusqueda;

	    @FXML
		protected TextField TextFieldHora1;
		Ventanas Pane = new Ventanas();
		
		  Ventanas Ventana = new   Ventanas();
	
		 // Crea una instancia del controlador de la nueva escena si es necesario
        ExtendedRegisterController extendedController = new ExtendedRegisterController();
        
        
        public UtilitariaNavegabilidad() {
            this.navegabilidad = new Ventanas();
        }

	public static void mostrarAlerta(String titulo, String mensaje) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(titulo);
		alert.setHeaderText(null);
		alert.setContentText(mensaje);
		alert.showAndWait();
	}

	 @FXML
	    void openWinOne(ActionEvent event) {
		
		 Pane.MostrarPane(event, "/Vista/Sample.fxml");

	    }
	
	@FXML
	public void openWintwo(ActionEvent event) {
		       Pane.MostrarPane(event, "/Vista/Sample2.fxml");

	}

	
	
	@FXML
	public void openWintwo2(ActionEvent event) {
		     Pane.MostrarPane(event,"/Vista/Empleado.fxml");
	}

	 @FXML
	    void openWinthree(ActionEvent event) {
	       
	            Pane.MostrarPane(event, "/Vista/Categorias.fxml");

	    }
	 
	 @FXML
	    void openWinFor(ActionEvent event) {
	     
	            Pane.MostrarPane(event, "/Vista/Cliente.fxml");

	    }

	 @FXML
	    void openWinFive(ActionEvent event) {
	      
	            Pane.MostrarPane(event, "/Vista/Principal.fxml");

	    }
	 

	    @FXML
	    void openWinSix(ActionEvent event) {
	    	Pane.MostrarPane(event, "/Vista/Pedido.fxml");
	    }
	 

	    @FXML
	    void openWinSeven(ActionEvent event) {
	       
	            Pane.MostrarPane(event, "/Vista/Ventas.fxml");
	    }

	  @FXML
	    void openWinOcho(ActionEvent event) {
	            Pane.MostrarPane(event, "/Vista/ventas_pasteles.fxml");

	    }
	  @FXML
	    void openCatalogos(ActionEvent event) {
	            Pane.MostrarPane(event, "/Vista/ventas_pasteles.fxml");

	    }
	
	@FXML
	void cambiarVista(ActionEvent event) {
    Pane.MostrarPane(event,"/Vista/EmpleadoNocturno.fxml");

}
	
	
	 @FXML
	    void openCaja(ActionEvent event) {
	    	
	            Pane.MostrarPane(event,"/Vista/Caja.fxml");
	    }
	 @FXML
	    void openReportes(ActionEvent event) {
	    	
	            Pane.MostrarPane(event,"/Vista/Reportes.fxml");
	    }
	 
	 
	 @FXML
	    void	 openReportesDeTendencia(ActionEvent event) {
	    	
	            Pane.MostrarPane(event,"/Vista/VistaReportes/ReportesDeTendencia.fxml");
	    }
	 
	 
	 @FXML
	    void openReportesDeVentas(ActionEvent event) {
	    	
	            Pane.MostrarPane(event,"/Vista/VistaReportes/ReportesVentas.fxml");
	    }
	 
	 @FXML
	    void openReportesDeVentasDiarias(ActionEvent event) {
	    	
	            Pane.MostrarPane(event,"/Vista/Diarias.fxml");
	    }
	 
	 		
	 @FXML
	    void openUsuarios(ActionEvent event) {

	    } 
	 
	 
	 
	@FXML
	void CerrarMenu(ActionEvent event) {
	    Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    navegabilidad.mostrarVentanaDesdeMenu(currentStage, "/Vista/Sample.fxml");
	}

	
	

    public void closeWindows(ActionEvent event) {
    	Pane.MostrarPane(event, "/Vista/Sample.fxml");
    }

	
	
	
	
}
