package dad.calculadora.css.ui;

import dad.calculadora.css.Calculadora;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class CalcController {
	
	// model
	
	private Calculadora calculadora = new Calculadora();
	
	// view
	
	@FXML 
	private GridPane view;
	
	@FXML
	private TextField screenText;

	public CalcController() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Calculadora.fxml"));
        loader.setController(this);
        loader.load();
	}
	
	@FXML
	private void initialize() {
		
		screenText.textProperty().bind(calculadora.screenProperty());
		view.getStylesheets().add("/css/clasico.css");
		
		// Crear los elementos del menú
		MenuItem clasicoItem = new MenuItem("Estilo Clásico");
		MenuItem modernoItem = new MenuItem("Estilo Moderno");

		// Establecer las acciones de los elementos del menú
		clasicoItem.setOnAction(e -> cambiarEstilo("/css/clasico.css", "clasico"));
		modernoItem.setOnAction(e -> cambiarEstilo("/css/moderno.css", "moderno"));

		// Crear el menú contextual y agregar los elementos
		ContextMenu menu = new ContextMenu(clasicoItem, modernoItem);

		// Mostrar el menú contextual cuando se solicite
		view.setOnContextMenuRequested(e -> menu.show(view, e.getScreenX(), e.getScreenY()));

		
		
	}
	
	// Método para cambiar el estilo y mostrar un mensaje en la consola
	private void cambiarEstilo(String estilo, String mensaje) {
		view.getStylesheets().clear();
		view.getStylesheets().add(estilo);
		System.out.println(mensaje);
	}
	
	@FXML
	private void onOperationButtonHandle(ActionEvent e) {
		String texto = ((Button)e.getSource()).getText();
		if (texto.equals("CE")) {
			calculadora.cleanEverything();
		} else if (texto.equals("C")) {
			calculadora.clean();
		} else if (texto.equals("+/-")) {
			calculadora.negate();
		} else {
			calculadora.operate(texto.charAt(0));
		}
	}
	
	@FXML
	private void onCommaButtonHandle(ActionEvent e) {
		calculadora.insertComma();
	}
	
	@FXML
	private void onNumberButtonHandle(ActionEvent e) {
		String texto = ((Button)e.getSource()).getText();
		calculadora.insert(texto.charAt(0));
	}
	
	public GridPane getView() {
		return view;
	}

}
