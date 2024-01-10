package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import dao.ParticipacionDao;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class EjercicioProyControllerParticipacion implements Initializable{
	
	private EjercicioProyControllerOlimpiadas ejProyControllerOlim;

	@FXML
    private Button btnAccion;

	@FXML
    private ComboBox<String> cbDeportista;

    @FXML
    private ComboBox<String> cbEquipo;

    @FXML
    private ComboBox<String> cbEvento;

    @FXML
    private ComboBox<String> cbParticipacion;

    @FXML
    private Label lbDeportista;

    @FXML
    private Label lbEdad;

    @FXML
    private Label lbParticipacion;

    @FXML
    private Label lbEvento;

    @FXML
    private Label lbMedalla;

    @FXML
    private Label txtCB;

    @FXML
    private TextField txtEdad;

    @FXML
    private TextField txtMedalla;

    @FXML
    private Label txtTitulo;
    
    private ParticipacionDao aD;
   
	 /**
	  * btn cancelar
	  * @param event
	  */
	@FXML
    void cancelar(ActionEvent event) {
    	//Cerrar ventana modal
    	//Me devuelve el elemento al que hice click
    	Node source = (Node) event.getSource();     
    	//Me devuelve la ventana donde se encuentra el elemento
    	Stage stage = (Stage) source.getScene().getWindow();    
    	stage.close();
    }
	
	/**
	 * aniadir producto
	 * @param event
	 */
	@FXML
    void aniadir(ActionEvent event) {
    	
    	//Alerta introducir todos los datos
		if (txtEdad.getText().toString().equals("") || txtMedalla.getText().toString().equals("")) {
    		String err = "";
			if (txtEdad.getText().toString().equals("") || txtMedalla.getText().toString().equals("")) {
				err="Rellenar todos los campos\n";
			}
    		
    		Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("TUS DATOS");
            alert.setHeaderText(null);
            alert.setContentText(err);
            alert.showAndWait();
		}else {
			if (ejProyControllerOlim.crearParticipacion(cbDeportista.getSelectionModel().getSelectedItem(),
					cbEvento.getSelectionModel().getSelectedItem(),
					cbEquipo.getSelectionModel().getSelectedItem(),
					Integer.parseInt(txtEdad.getText().toString()), txtMedalla.getText().toString())) {
				//Ventana de informacion
	        	Alert alert = new Alert(Alert.AlertType.INFORMATION);
	            alert.setTitle("Info");
	            alert.setHeaderText(null);
	            alert.setContentText("Participacion añadido correctamente");
	            alert.showAndWait();
	          
	            //Cerrar ventana modal
	        	//Me devuelve el elemento al que hice click
	        	Node source = (Node) event.getSource();     
	        	//Me devuelve la ventana donde se encuentra el elemento
	        	Stage stage = (Stage) source.getScene().getWindow();    
	        	stage.close();
			}else {
				//Alerta persona existe en la tabla
				Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("TUS DATOS");
                alert.setHeaderText(null);
                alert.setContentText("Participacion ya existe!");
                alert.showAndWait();
			}
		}
    }
	
	@FXML
    void cambiarParticipacion(ActionEvent event) {

    }
	
	public void setControlerL(EjercicioProyControllerOlimpiadas ej) {
    	this.ejProyControllerOlim= ej;
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		aD = new ParticipacionDao();
		//Contenido del comboBox
		cbDeportista.setItems(FXCollections.observableArrayList(ejProyControllerOlim.cargarDeportistasNombre()));
    	cbDeportista.getSelectionModel().selectFirst();
    	cbEvento.setItems(FXCollections.observableArrayList(ejProyControllerOlim.cargarEventosNombre()));
    	cbEvento.getSelectionModel().selectFirst();
    	cbEquipo.setItems(FXCollections.observableArrayList(ejProyControllerOlim.cargarEquiposNombre()));
    	cbEquipo.getSelectionModel().selectFirst();
		
	}
}
