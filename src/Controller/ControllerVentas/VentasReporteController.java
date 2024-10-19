package Controller.ControllerVentas;

import Model.Ventas;
import javafx.scene.control.TextField;

import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;

import Controller.UtilitariaNavegabilidad;
import DAO.VentaDAO;
import Factory.ConnectionFactory;

public class VentasReporteController extends UtilitariaNavegabilidad implements Initializable {

	@FXML
	TableView<Ventas> tblVentas;
	@FXML
	private Button btnDiarias;
	
	@FXML
	private Button btnImprimir;

	@FXML
	private TextField txtCantidadVendida;
	@FXML
	private TextField txtSubtotal;
	@FXML
	private TextField txtTotal;
	@FXML
	private TextField txtFechaDeVenta;
	@FXML
	private TextField txtProductos;

	@FXML
	TableColumn<Ventas, Integer> colFolio;
	@FXML
	TableColumn<Ventas, Integer> colCantidadVendida;
	@FXML
	TableColumn<Ventas, Float> colSubtotal;
	@FXML
	TableColumn<Ventas, Float> colTotal;
	@FXML
	TableColumn<Ventas, String> colFechaDeVenta;
	@FXML
	TableColumn<Ventas, String> colEmpleadoVenta;
	@FXML
	TableColumn<Ventas, String> colCodigo;
	@FXML
	TableColumn<Ventas, String> colProductos;
	
	
	
	

	
	 @FXML
	   public PieChart pieChart;
	 
	private ObservableList<Ventas> listaVentas;

	public void initialize(URL arg0, ResourceBundle arg1) {
		listaVentas = FXCollections.observableArrayList();

		try {
		//	PieChartVentasDia();
			ResultSet resVentas = ConnectionFactory.executeQuery("SELECT * FROM ventas");// devuelve un objeto

			// aquí se ingresan los nombres de los campos de la bd
			while (resVentas.next()) {
				listaVentas.add(new Ventas(
						resVentas.getInt("Folio"),
						resVentas.getInt("CantidadVendida"),
						resVentas.getFloat("Subtotal"),
						resVentas.getFloat("Total"),
						resVentas.getString("FechaDeVenta"),
						resVentas.getString("Productos")));
			}
		} catch (Exception ex) {
			System.err.println("ERROR: " + ex.getMessage());
		}

		tblVentas.setEditable(true);
		colFolio.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		colCantidadVendida.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		colSubtotal.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
		colTotal.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
		colFechaDeVenta.setCellFactory(TextFieldTableCell.forTableColumn());
		colProductos.setCellFactory(TextFieldTableCell.forTableColumn());
		// establesco y muestro los datos de mi base datos en la pantalla de fx
		colFolio.setCellValueFactory(dato -> dato.getValue().getFolio().asObject());
		
		colCantidadVendida.setCellValueFactory(dato -> dato.getValue().getCantidadVendida().asObject());
		colSubtotal.setCellValueFactory(dato -> dato.getValue().getSubtotal().asObject());
		colTotal.setCellValueFactory(dato -> dato.getValue().getTotal().asObject());
		colFechaDeVenta.setCellValueFactory(dato -> dato.getValue().getFechaDeVenta());
		colProductos.setCellValueFactory(dato -> dato.getValue().getProductos());
		tblVentas.setItems(listaVentas);

	}

	/*private void PieChartVentasDia() throws Exception {
	    VentaDAO ventaDAO = new VentaDAO();
	    try {
	        List<Ventas> ventasPorDia = ventaDAO.obtenerVentas();
	        for (Ventas ventas : ventasPorDia) {
	            String etiqueta = ventas.getProductos2(); // Utilizamos el producto como etiqueta del segmento
	            int cantidad = ventas.getCantidadVendida2(); // Utilizamos la cantidad vendida como valor del segmento
	            PieChart.Data slice = new PieChart.Data(etiqueta, cantidad);
	            pieChart.getData().add(slice);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Manejar la excepción según tus necesidades
	    }
	}

*/
}