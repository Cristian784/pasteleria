package Controller;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ResourceBundle;

import DAO.VentaDAO;
import Model.Pasteles;
import Model.Ventas;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.scene.control.cell.TextFieldTableCell;

import javafx.util.Duration;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class VentaEmpleadoController extends UtilitariaNavegabilidad implements Initializable {
	 @FXML
		protected TextField TextFieldFecha1;

		    @FXML
			protected TextField TextFieldHora1;
	
	
	 @FXML
	    private TableView<Ventas> tblVentas;

	    @FXML
	    private TableColumn<Ventas,Integer> colFolio;

	    @FXML
	    private TableColumn<Ventas, String> colDescripcion;

	    @FXML
	    private TableColumn<Ventas, Integer> colCantidad;

	    @FXML
	    private TableColumn<Ventas, String> colTipo;

	    @FXML
	    private TableColumn<Ventas, Float> colPrecioUnitario;

	    @FXML
	    private TableColumn<Ventas, Float> colSubTotal;

	    @FXML
	    private TableColumn<Ventas, Float> colTotal;


	    @FXML
	    private Button btnMenu;

	    @FXML
	    private TextField TextFBuscar;

	    @FXML
	    private TextField TextFArticulo;

	    @FXML
	    private TextField TextFCodigodeBarras;

	    @FXML
	    private TextField TextFCategorias;

	   

	    @FXML
	    private TextField TextFFolio;

	    
	    @FXML
	    private Button btnVender;
	    @FXML
	    private Button btnAgregar;
	    
	    private VentaDAO ventaDAO; // Debes inicializar esto en algún lugar, posiblemente en el initialize()
	    
	    @FXML
	    private TextField TextFInventario;
	    private ObservableList<Ventas> listaVentas;
	    
	    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		

		 Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> MostrarHora1(event)));
	        Timeline timeline1 = new Timeline(new KeyFrame(Duration.seconds(1), event -> MostrarFecha1(event)));
	        timeline.setCycleCount(Timeline.INDEFINITE);
	        timeline.play();
	        timeline1.setCycleCount(Timeline.INDEFINITE);
	        timeline1.play();
		
		
		
		
		tblVentas.setEditable(true);
		colFolio.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		colDescripcion.setCellFactory(TextFieldTableCell.forTableColumn());
		colCantidad.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		colTipo.setCellFactory(TextFieldTableCell.forTableColumn());
		colPrecioUnitario.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
		colSubTotal.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
		colTotal.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
		
		 // Configurar las celdas de la tabla y otras inicializaciones
		
        // Inicializar el objeto ventaDAO (debes asegurarte de inicializarlo adecuadamente)
        ventaDAO = new VentaDAO();
     // Asignar la lista a la tabla
        // Crear una lista observable para almacenar las ventas
        listaVentas = FXCollections.observableArrayList();
        // Asignar la lista a la tabla
        tblVentas.setItems(listaVentas);
        colFolio.setCellValueFactory(dato -> dato.getValue().getFolio().asObject());
		colDescripcion.setCellValueFactory(dato -> dato.getValue().getProductos());
		colCantidad.setCellValueFactory(dato -> dato.getValue().getCantidadVendida().asObject());
		//colTipo.setCellValueFactory(dato -> dato.getValue().getDescripcion());
		//colPrecioUnitario.setCellValueFactory(dato -> dato.getValue().getPeso().asObject());
		colSubTotal.setCellValueFactory(dato -> dato.getValue().getSubtotal().asObject());
		colTotal.setCellValueFactory(dato -> dato.getValue().getTotal().asObject());

		

		
	}
	
	@FXML
	private void buscarArticulo() throws Exception {
	    // Obtener el texto de búsqueda
	    String textoBusqueda = TextFBuscar.getText();

	    // Realizar la búsqueda en la base de datos
	    Ventas ventaEncontrada = ventaDAO.buscarVentaPorNombreProducto(textoBusqueda);
	   Pasteles Pasti = ventaDAO.buscarVenta(textoBusqueda);

	    // Actualizar los campos de la interfaz con los datos encontrados
	    if (ventaEncontrada != null) {
	        // Convertir los valores de las propiedades a tipos primitivos y establecerlos en el modelo
	        int folio = ventaEncontrada.getFolio().get();
	        int cantidadVendida = ventaEncontrada.getCantidadVendida().get();
	        float subtotal = ventaEncontrada.getSubtotal().get();
	        float total = Pasti.getPrecio().get();

	        // Crear nuevas instancias de IntegerProperty y FloatProperty
	        IntegerProperty folioProperty = new SimpleIntegerProperty(folio);
	        IntegerProperty cantidadVendidaProperty = new SimpleIntegerProperty(cantidadVendida);
	        FloatProperty subtotalProperty = new SimpleFloatProperty(subtotal);
	        Pasti.setPrecio(new SimpleFloatProperty(Pasti.getPrecio().get()));
	      //  FloatProperty totalProperty = new SimpleFloatProperty(total);

	        ventaEncontrada.setFolio(folioProperty);
	        ventaEncontrada.setCantidadVendida(cantidadVendidaProperty);
	        ventaEncontrada.setSubtotal(subtotalProperty);
	      
	        
	        
	        TextFArticulo.setText(ventaEncontrada.getProductos().get());
	        TextFFolio.setText(String.valueOf(folio));
	        TextFCodigodeBarras.setText(ventaEncontrada.getCodigodebarras().get());
	        TextFCategorias.setText(ventaEncontrada.getCategorias().get());

	        // Implementa este método para obtener el inventario según el folio
	        TextFInventario.setText(obtenerInventarioSegunFolio(folio));
	    } else {
	        // Limpiar los campos si no se encuentra ninguna venta
	        limpiarCampos();
	    }
	}




	@FXML
	private void agregarVentaATabla() {
	    String textoBusqueda = TextFBuscar.getText();

	    try {
	        Ventas ventaEncontrada = ventaDAO.buscarVentaPorNombreProducto(textoBusqueda);
	        Ventas buscarVenta = ventaDAO.buscarVentaPorNombreProducto(textoBusqueda);

	        if (ventaEncontrada != null) {
	            // Crear una instancia de Venta con los datos de la Ventas encontrada
	            Ventas venta = new Ventas(
	                    ventaEncontrada.getFolio().get(),
	                    ventaEncontrada.getCantidadVendida().get(),
	                    ventaEncontrada.getSubtotal().get(),	
	                    ventaEncontrada.getTotal().get(),
	                    ventaEncontrada.getFechaDeVenta().get(),
	                   ventaEncontrada.getProductos().get()  // Nombre del pastel
	                   //ventaEncontrada.getCodigodebarras().get(),  // Código de barras del pastel
	                   //ventaEncontrada.getCategorias().get()  // Categoría del pastel
	            );
	            
	            
	            
	            /*
	            Pasteles pasti = new Pasteles(
	            		buscarVenta.getNombre().get(),
	            		buscarVenta.getDescripcion().get(),
	            		buscarVenta.getPrecio().get(),	
	            		buscarVenta.getPeso().get(),
	            		buscarVenta.getCantidad_en_refri().get(),
	            		buscarVenta.getFecha_de_elaboracion().get(),  // Nombre del pastel
	                   buscarVenta.getFecha_de_vencimiento().get(),  // Código de barras del pastel
	                   buscarVenta.getCategoria().get() ,
	                   buscarVenta.getCodigodeBarras().get() 
	            );
	            
	            
	            */
	            
				// Agregar la venta a la lista observable
	            listaVentas.add(venta);
	      
	            // Limpiar los campos de búsqueda
	            limpiarCampos();
	        } else {
	            // Manejar el caso en el que no se encuentra ninguna venta
	            System.out.println("No se encontró ninguna venta para el producto: " + textoBusqueda);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	

	
	// Método simulado para obtener el inventario según el folio (deberías implementarlo)
	private String obtenerInventarioSegunFolio(int folio) {
	    // Lógica para obtener el inventario según el folio
	    // ...

	    // En este ejemplo, devuelvo una cadena fija. Debes adaptarlo según tu lógica de negocio.
	    return "Inventario para el folio " + folio;
	}
	
	 private void limpiarCampos() {
	        TextFArticulo.clear();
	        TextFCodigodeBarras.clear();
	        TextFCategorias.clear();
	        TextFFolio.clear();
	        TextFInventario.clear();
	    }
	
	 @FXML
	    void MostrarFecha1(ActionEvent event) {
	    	// Obtener la fecha 
	        LocalDateTime currentDateTime = LocalDateTime.now();
	        // Formatear la fecha y hora según tus preferencias
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        String formattedDateTime = currentDateTime.format(formatter);
	        // Establecer el texto formateado en el TextField
	        TextFieldFecha1.setText(formattedDateTime);

	    }

	    @FXML
	    void MostrarHora1(ActionEvent event) {
	    	// Obtener la hora actual
	        LocalDateTime currentDateTime = LocalDateTime.now();
	        // Formatear la fecha y hora según tus preferencias
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	        String formattedDateTime = currentDateTime.format(formatter);
	        // Establecer el texto formateado en el TextField
	        TextFieldHora1.setText(formattedDateTime);
	    }

	 
	
	

}

   