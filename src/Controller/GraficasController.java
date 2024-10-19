package Controller;

import DAO.CategoriaVentaDAO;
import Model.CategoriaVenta;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class GraficasController implements Initializable {

   @FXML
   private PieChart pieChart;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
           // cargarDatosEnPieChart();
        } catch (Exception e) {
            e.printStackTrace();
            // Manejar la excepción según tus necesidades
        }
    }
    
    private void cargarDatosEnPieChart() throws Exception {
        CategoriaVentaDAO categoriaVentaDAO = new CategoriaVentaDAO();
        try {
            List<CategoriaVenta> ventasPorCategoria = categoriaVentaDAO.obtenerVentasPorCategoria();
            for (CategoriaVenta categoriaVenta : ventasPorCategoria) {
                PieChart.Data slice = new PieChart.Data(categoriaVenta.getCategoria(), categoriaVenta.getTotalVentas());
                pieChart.getData().add(slice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar la excepción según tus necesidades
        }
    }
    
    
    
    
}

