package Controller;

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
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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

import DAO.VentaDAO;
import Factory.ConnectionFactory;

public class VentasController extends UtilitariaNavegabilidad implements Initializable {

    @FXML
    TableView<Ventas> tblVentas;

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
    private TextField txtCodigo;
    @FXML
    private TextField txtEmpleadoVenta;
    @FXML
    private TextField txtFolio;
    @FXML
    private TextField TxtBusqueda;

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
    TableColumn<Ventas, String> colProductos;
    @FXML
    TableColumn<Ventas, String> colCodigo;
    @FXML
    TableColumn<Ventas, String> colEmpleadoVenta;
    
    @FXML
    private AreaChart<String, Number> areaChart;

    private ObservableList<Ventas> listaVentas;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        listaVentas = FXCollections.observableArrayList();
        tblVentas.setEditable(true);
        cargarColumnasTabla();
        try {
			cargarDatosTabla();
			// Ejemplo de datos para el AreaChart
	        XYChart.Series<String, Number> series = new XYChart.Series<>();
	        series.setName("Ventas Diarias");
	        series.getData().add(new XYChart.Data<>("Producto 1", 30));
	        series.getData().add(new XYChart.Data<>("Producto 2", 20));
	        series.getData().add(new XYChart.Data<>("Producto 3", 50));
	        areaChart.getData().add(series);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
            cargarVentasDelDiaActual();
         // Ejemplo de datos para el AreaChart
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("Ventas Diarias");
            series.getData().add(new XYChart.Data<>("Producto 1", 30));
            series.getData().add(new XYChart.Data<>("Producto 2", 20));
            series.getData().add(new XYChart.Data<>("Producto 3", 50));
            areaChart.getData().add(series);
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo cargar las ventas del día actual.");
        }
    }

    private void cargarColumnasTabla() {
        colFolio.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colCantidadVendida.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colSubtotal.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        colTotal.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        colFechaDeVenta.setCellFactory(TextFieldTableCell.forTableColumn());
        colEmpleadoVenta.setCellFactory(TextFieldTableCell.forTableColumn());
        colCodigo.setCellFactory(TextFieldTableCell.forTableColumn());
        colProductos.setCellFactory(TextFieldTableCell.forTableColumn());

        colFolio.setCellValueFactory(dato -> dato.getValue().getFolio().asObject());
        colCantidadVendida.setCellValueFactory(dato -> dato.getValue().getCantidadVendida().asObject());
        colSubtotal.setCellValueFactory(dato -> dato.getValue().getSubtotal().asObject());
        colTotal.setCellValueFactory(dato -> dato.getValue().getTotal().asObject());
        colFechaDeVenta.setCellValueFactory(dato -> dato.getValue().getFechaDeVenta());
        colEmpleadoVenta.setCellValueFactory(dato -> dato.getValue().getEmpleadoVenta());
        colCodigo.setCellValueFactory(dato -> dato.getValue().getCodigo());
        colProductos.setCellValueFactory(dato -> dato.getValue().getProductos());
    }

    private void cargarDatosTabla() throws Exception {
        try {
            ResultSet resVentas = ConnectionFactory.executeQuery("SELECT * FROM ventas");
            while (resVentas.next()) {
                listaVentas.add(new Ventas(
                        resVentas.getInt("Folio"),
                        resVentas.getInt("CantidadVendida"),
                        resVentas.getFloat("Subtotal"),
                        resVentas.getFloat("Total"),
                        resVentas.getString("FechaDeVenta"),
                        resVentas.getString("Users_FK"),
                        resVentas.getString("Codigo_Producto"),
                        resVentas.getString("Productos")));
            }
            tblVentas.setItems(listaVentas);
        } catch (SQLException ex) {
            mostrarAlerta("Error", "No se pudo cargar los datos de la base de datos: " + ex.getMessage());
        }
    }

    @FXML
    private void guardarVenta(ActionEvent event) throws Exception {
        if (validarCampos()) {
            Ventas venta = new Ventas(
                    Integer.parseInt(txtFolio.getText()),
                    Integer.parseInt(txtCantidadVendida.getText()),
                    Float.parseFloat(txtSubtotal.getText()),
                    Float.parseFloat(txtTotal.getText()),
                    txtFechaDeVenta.getText(),
                    txtEmpleadoVenta.getText(),
                    txtCodigo.getText(),
                    txtProductos.getText());

            listaVentas.add(venta);

            try {
                String query = "INSERT INTO ventas (Folio, CantidadVendida, Subtotal, Total, FechaDeVenta, Users_FK, Codigo_Producto, Productos) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(query)) {
                    ps.setInt(1, venta.getFolio().get());
                    ps.setInt(2, venta.getCantidadVendida().get());
                    ps.setFloat(3, venta.getSubtotal().get());
                    ps.setFloat(4, venta.getTotal().get());
                    ps.setString(5, venta.getFechaDeVenta().get());
                    ps.setString(6, venta.getEmpleadoVenta().get());
                    ps.setString(7, venta.getCodigo().get());
                    ps.setString(8, venta.getProductos().get());
                    ps.executeUpdate();
                }
                mostrarAlerta("Éxito", "Registro guardado correctamente en la base de datos.");
            } catch (SQLException ex) {
                mostrarAlerta("Error", "Hubo un error al guardar el registro: " + ex.getMessage());
            }
        } else {
            mostrarAlerta("Advertencia", "Por favor, complete todos los campos.");
        }
    }

    @FXML
    private void eliminarVenta() throws Exception {
        Ventas selectedVenta = tblVentas.getSelectionModel().getSelectedItem();
        if (selectedVenta != null) {
            listaVentas.remove(selectedVenta);
            try {
                String query = "DELETE FROM ventas WHERE Folio = ?";
                try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(query)) {
                    ps.setInt(1, selectedVenta.getFolio().get());
                    ps.executeUpdate();
                }
                mostrarAlerta("Éxito", "Registro eliminado correctamente de la base de datos.");
            } catch (SQLException ex) {
                mostrarAlerta("Error", "Hubo un error al eliminar el registro: " + ex.getMessage());
            }
        } else {
            mostrarAlerta("Advertencia", "Por favor, seleccione una venta para eliminar.");
        }
    }

    @FXML
    private void actualizarVenta() throws Exception {
        Ventas selectedVenta = tblVentas.getSelectionModel().getSelectedItem();
        if (selectedVenta != null) {
            try {
                String query = "UPDATE ventas SET CantidadVendida = ?, Subtotal = ?, Total = ?, FechaDeVenta = ?, Productos = ? WHERE Folio = ?";
                try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(query)) {
                    ps.setInt(1, selectedVenta.getCantidadVendida().get());
                    ps.setFloat(2, selectedVenta.getSubtotal().get());
                    ps.setFloat(3, selectedVenta.getTotal().get());
                    ps.setString(4, selectedVenta.getFechaDeVenta().get());
                    ps.setString(5, selectedVenta.getProductos().get());
                    ps.setInt(6, selectedVenta.getFolio().get());
                    ps.executeUpdate();
                }
                mostrarAlerta("Éxito", "Registro actualizado correctamente en la base de datos.");
            } catch (SQLException ex) {
                mostrarAlerta("Error", "Hubo un error al actualizar el registro: " + ex.getMessage());
            }
        } else {
            mostrarAlerta("Advertencia", "Por favor, seleccione una venta para actualizar.");
        }
    }

    @FXML
    private void buscarVenta(ActionEvent event) throws Exception {
        String folioVenta = TxtBusqueda.getText();
        if (!folioVenta.isEmpty()) {
            listaVentas.clear();
            try {
                String query = "SELECT * FROM ventas WHERE Folio = ?";
                try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(query)) {
                    ps.setString(1, folioVenta);
                    ResultSet resVentas = ps.executeQuery();
                    while (resVentas.next()) {
                        listaVentas.add(new Ventas(
                                resVentas.getInt("Folio"),
                                resVentas.getInt("CantidadVendida"),
                                resVentas.getFloat("Subtotal"),
                                resVentas.getFloat("Total"),
                                resVentas.getString("FechaDeVenta"),
                                resVentas.getString("Users_FK"),
                                resVentas.getString("Codigo_Producto"),
                                resVentas.getString("Productos")));
                    }
                    tblVentas.setItems(listaVentas);
                }
            } catch (SQLException ex) {
                mostrarAlerta("Error", "Hubo un error al buscar el registro: " + ex.getMessage());
            }
        } else {
            cargarDatosTabla();
        }
    }

    private void cargarVentasDelDiaActual() throws Exception {
        VentaDAO ventaDAO = new VentaDAO();
        try {
            List<Ventas> ventas = ventaDAO.obtenerVentasDelDiaActual();
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("Ventas del día actual");
            for (Ventas venta : ventas) {
                series.getData().add(new XYChart.Data<>(venta.getProductos().get(), venta.getCantidadVendida().get()));
            }
            areaChart.getData().add(series);
        } catch (SQLException e) {
            mostrarAlerta("Error", "No se pudo cargar las ventas del día actual.");
        }
    }
/*
    private void mostrarAlerta(String titulo, String contenido) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
*/
    private boolean validarCampos() {
        return !txtFolio.getText().isEmpty()
                && !txtProductos.getText().isEmpty()
                && !txtCantidadVendida.getText().isEmpty()
                && !txtSubtotal.getText().isEmpty()
                && !txtTotal.getText().isEmpty()
                && !txtFechaDeVenta.getText().isEmpty()
                && !txtEmpleadoVenta.getText().isEmpty()
                && !txtCodigo.getText().isEmpty();
    }
}
