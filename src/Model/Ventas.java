package Model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import Controller.UtilitariaNavegabilidad;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Ventas {
	private IntegerProperty folio;
	private String productos2;
	private StringProperty productos;
	private int cantidadVendida2;
	private IntegerProperty cantidadVendida;
	private FloatProperty subtotal;
	private FloatProperty total;
	private StringProperty fechaDeVenta;	
	 private StringProperty codigo;
	  private StringProperty empleadoVenta;
	private StringProperty Codigodebarras;
	private StringProperty Categorias;

	
	
	public Ventas(int folio, int cantidadVendida, float subtotal, float total, String fechaVenta, String productos, String codigo, String empleadoVenta) {
        super();
        this.folio = new SimpleIntegerProperty(folio);
        this.cantidadVendida = new SimpleIntegerProperty(cantidadVendida);
        this.subtotal = new SimpleFloatProperty(subtotal);
        this.total = new SimpleFloatProperty(total);
        this.fechaDeVenta = new SimpleStringProperty(fechaVenta);
        this.productos = new SimpleStringProperty(productos);
        this.codigo = new SimpleStringProperty(codigo);
        this.empleadoVenta = new SimpleStringProperty(empleadoVenta);
    }
	public Ventas(int folio, int cantidadVendida, float subtotal, float total, String fechaVenta, String productos) {
		super();
		this.folio = new SimpleIntegerProperty(folio);
		this.cantidadVendida = new SimpleIntegerProperty(cantidadVendida);
		this.subtotal = new SimpleFloatProperty(subtotal);
		this.total = new SimpleFloatProperty(total);
		this.fechaDeVenta = new SimpleStringProperty(fechaVenta);
		this.productos = new SimpleStringProperty(productos);
	}
	
	
	
	public Ventas( int cantidadVendida2, String productos2) {
		super();
		
		this.cantidadVendida = cantidadVendida;
		
		this.productos = productos;
		
	}
	
	
	
	
	// Getters y Setters para las nuevas propiedades

    public StringProperty getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo.set(codigo);
    }

    public StringProperty getEmpleadoVenta() {
        return empleadoVenta;
    }

    public void setEmpleadoVenta(String empleadoVenta) {
        this.empleadoVenta.set(empleadoVenta);
    }


	public String getProductos2() {
		return productos2;
	}

	public void setProductos2(String productos2) {
		this.productos2 = productos2;
	}

	public int getCantidadVendida2() {
		return cantidadVendida2;
	}

	public void setCantidadVendida2(int cantidadVendida2) {
		this.cantidadVendida2 = cantidadVendida2;
	}

	public StringProperty getCodigodebarras() {
		return Codigodebarras;
	}

	public void setCodigodebarras(StringProperty codigodebarras) {
		Codigodebarras = codigodebarras;
	}

	public StringProperty getCategorias() {
		return Categorias;
	}

	public void setCategorias(StringProperty categorias) {
		Categorias = categorias;
	}

	public Ventas() {
		// TODO Auto-generated constructor stub
	}

	public IntegerProperty getFolio() {
		return folio;
	}

	public void setFolio(IntegerProperty folio) {
		this.folio = folio;
	}

	public IntegerProperty getCantidadVendida() {
		return cantidadVendida;
	}

	public void setCantidadVendida(IntegerProperty cantidadVendida) {
		this.cantidadVendida = cantidadVendida;
	}

	public FloatProperty getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(FloatProperty subtotal) {
		this.subtotal = subtotal;
	}

	public FloatProperty getTotal() {
		return total;
	}

	public void setTotal(FloatProperty total) {
		this.total = total;
	}

	public StringProperty getFechaDeVenta() {
		return fechaDeVenta;
	}

	public void setFechaVenta(StringProperty fechaVenta) {
		this.fechaDeVenta = fechaVenta;
	}

	public StringProperty getProductos() {
		return productos;
	}

	public void setProductos(StringProperty productos) {
		this.productos = productos;
	}

	
	
	
	
	
	
		public static boolean validarCampos(String folioVentaText, String cantidadText, String subtotalText,
				String totalText,
				String fechaVenta, String productos) {

			// Validar que se hayan ingresado todos los campos requeridos
			if (folioVentaText.isEmpty() || cantidadText.isEmpty() || subtotalText.isEmpty() || totalText.isEmpty() ||
					fechaVenta.isEmpty() || productos.isEmpty()) {
				UtilitariaNavegabilidad.mostrarAlerta("Error", "Por favor, complete todos los campos requeridos.");
				return false;
			}
			/*
			* if (!folioVentaText.matches("0+$")) {
			* // El número de teléfono contiene caracteres no válidos
			* Utilitaria.mostrarAlerta("Error", "El folio sólo debe contener numeros.");
			* return false;
			* } else if (!cantidadText.matches("0+$")) {
			* // El número de teléfono no tiene al menos 10 dígitos
			* Utilitaria.mostrarAlerta("Error", "La cantidad debe ser de tipo numérico.");
			* return false;
			* }
			* 
			* 
			* 
			* 
			* 
			*/

			LocalDate fecha;

			try {
				fecha = LocalDate.parse(fechaVenta);

			} catch (DateTimeParseException e) {
				UtilitariaNavegabilidad.mostrarAlerta("Error", "Por favor, ingrese las fechas en el formato correcto (YYYY-MM-DD).");
				return false;
			}

			int folioVenta;
			int cantidad;
			double subtotal;
			double total;

			try {
				folioVenta = Integer.parseInt(folioVentaText);
				cantidad = Integer.parseInt(cantidadText);
				subtotal = Double.parseDouble(subtotalText);
				total = Double.parseDouble(totalText);
			} catch (NumberFormatException e) {
				UtilitariaNavegabilidad.mostrarAlerta("Error", "Por favor, ingrese valores numéricos válidos.");
				return false;
			}

			return true;
		}

		
		public static boolean validarCampoBusqueda(String folioVentaText) {
			if (folioVentaText.isEmpty()) {
					UtilitariaNavegabilidad.mostrarAlerta("Error", "Por favor, complete todos los campos requeridos.");
			return false;	
			}
			int folioVenta;
			
			try {
				folioVenta = Integer.parseInt(folioVentaText);
			} catch (NumberFormatException e) {
				UtilitariaNavegabilidad.mostrarAlerta("Error", "Por favor, ingrese valores numéricos válidos.");
				return false;
			}
			
			return true;
			
			
			
			
		}
		
		
}