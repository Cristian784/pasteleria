package Controller;

import Model.Ventanas;
import Controller.Main;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		
		
		Ventanas Pane = new Ventanas();
		//Pane.MostrarPane2("/Vista/Principal.fxml");
		//Pane.MostrarPane2("/Vista/Sample.fxml");
		//Pane.MostrarPane2("/Vista/Sample2.fxml");
		//Pane.MostrarPane2("/Vista/Diarias.fxml");
		Pane.MostrarPane2("/Vista/Empleado.fxml");
		//Pane.MostrarPane("/Vista/Empleado.fxml");
		//Pane.MostrarPane("/Vista/VentaEmpleado.fxml");
	
	
	}

	public static void main(String[] args) {
		launch(args);
	}

}
