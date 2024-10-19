package Controller;


import java.io.IOException;

import Model.Ventanas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

import javafx.scene.control.Alert.AlertType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;



public abstract class UtilitariaNavEmpleado {
	private Ventanas navegabilidad;
    @FXML
    private Button btnVentas2;
	
	@FXML
	private MenuItem itemClaro;
	@FXML
	private MenuButton menuopciones;
	
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
	private Button btnVentas;

	@FXML
	private Button btnPedidos;

	@FXML
	private Button btnEstadoPedido;

	@FXML
	private MenuItem itemNocturno;

	@FXML
	private MenuItem itemVentas2;

	@FXML
	private MenuItem itemCerrar;
	
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

	public static void mostrarAlerta(String titulo, String mensaje) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(titulo);
		alert.setHeaderText(null);
		alert.setContentText(mensaje);
		alert.showAndWait();
	}

	@FXML
	void openPedidosEmpleado(ActionEvent event) {
	
		Pane.MostrarPane(event, "/Vista/Pedido2.fxml");
	
	}

	@FXML
	void openVentasEmpleado(ActionEvent event) {

		Pane.MostrarPane(event,"/Vista/VentaEmpleado.fxml");
	}
	
	@FXML
	void openWinSeven(ActionEvent event) {

		Pane.MostrarPane(event,"/Vista/Factura.fxml");

	}

	@FXML
	void openWinSix2(ActionEvent event) {

		Pane.MostrarPane(event,"/Vista/Pedido2.fxml");

	}

	
	 @FXML
	    void openWinOne(ActionEvent event) {
	        try {

	            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/Sample.fxml"));
	            Parent root = loader.load();

	            SampleController controlador = loader.getController();

	            Scene scene = new Scene(root);
	            Stage stage = new Stage();
	            stage.setScene(scene);
	            stage.setTitle("Primera Ventana");

	            // Llama al método initialize() de SampleController2
	            controlador.initialize(null, null);

	            stage.show();

	            // Cierra la ventana actual
	            Stage myStage = (Stage) btnVentas2.getScene().getWindow();
	            myStage.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	
	
	@FXML
	void cambiarVista(ActionEvent event) {
    Pane.MostrarPane(event,"/Vista/EmpleadoNocturno.fxml");

}
	@FXML
	void cambiarTemaClaro(ActionEvent event) {

		Pane.MostrarPane(event, "/Vista/Empleado.fxml");

	}
	
	
	@FXML
	void CerrarMenu(ActionEvent event) {
	    // Obtener la ventana actual desde cualquier elemento gráfico (como botones)
	    Node sourceNode = null;
	    
	    if (event.getSource() instanceof Node) {
	        // Si el evento proviene de un Node, podemos usarlo para obtener el Stage
	        sourceNode = (Node) event.getSource();
	    } else {
	        // Si proviene de un MenuItem, debemos buscar otra forma de obtener el Stage
	        // Por ejemplo, podemos usar un botón que esté visible en la escena
	        sourceNode = btnMenu;  // Usar btnMenu o cualquier otro botón como referencia para obtener el Stage
	    }

	    if (sourceNode != null) {
	        Stage currentStage = (Stage) sourceNode.getScene().getWindow();
	        navegabilidad.mostrarVentanaDesdeMenu(currentStage, "/Vista/Sample.fxml");
	    }
	}

	
	

    public void closeWindows(ActionEvent event) {
    	Pane.MostrarPane(event, "/Vista/Sample.fxml");
    }

	
    
	
	
	
	
}
