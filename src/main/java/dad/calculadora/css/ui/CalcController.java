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
		
		// Menú
		MenuItem clasicoItem = new MenuItem("Estilo Clásico");
		MenuItem modernoItem = new MenuItem("Estilo Moderno");

		// Acciones menú
		clasicoItem.setOnAction(e -> cambiarEstilo("/css/clasico.css", "clasico"));
		modernoItem.setOnAction(e -> cambiarEstilo("/css/moderno.css", "moderno"));

		// Agregar manú
		ContextMenu menu = new ContextMenu(clasicoItem, modernoItem);

		// Mostrar al solicitarse
		view.setOnContextMenuRequested(e -> menu.show(view, e.getScreenX(), e.getScreenY()));
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
	private void onNumberButtonHandle(ActionEvent e) {
		String texto = ((Button)e.getSource()).getText();
		calculadora.insert(texto.charAt(0));
	}
	
	@FXML
	private void onCommaButtonHandle(ActionEvent e) {
		calculadora.insertComma();
	}
	
	public GridPane getView() {
		return view;
	}

	// Método cambiar estilo
	private void cambiarEstilo(String estilo, String mensaje) {
		view.getStylesheets().clear();
		view.getStylesheets().add(estilo);
		System.out.println(mensaje);
	}

}
