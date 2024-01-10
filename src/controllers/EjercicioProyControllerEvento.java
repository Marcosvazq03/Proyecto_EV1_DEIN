package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import dao.EventoDao;
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
import model.Evento;


public class EjercicioProyControllerEvento implements Initializable{
	
	private EjercicioProyControllerOlimpiadas ejProyControllerOlim;

	@FXML
    private Button btnAccion;

	@FXML
    private ComboBox<String> cbDeporte;

    @FXML
    private ComboBox<String> cbEvento;

    @FXML
    private ComboBox<String> cbOlimpiada;

    @FXML
    private Label lbDeporte;

    @FXML
    private Label lbNombre;

    @FXML
    private Label lbOlimpiada;

    @FXML
    private Label txtCB;

    @FXML
    private TextField txtNombre;

    @FXML
    private Label txtTitulo;
    
    private EventoDao aD;
   
	 
	@FXML
    void cancelar(ActionEvent event) {
    	//Cerrar ventana modal
    	//Me devuelve el elemento al que hice click
    	Node source = (Node) event.getSource();     
    	//Me devuelve la ventana donde se encuentra el elemento
    	Stage stage = (Stage) source.getScene().getWindow();    
    	stage.close();
    }
	
	@FXML
    void aniadir(ActionEvent event) {
    	
    	//Alerta introducir todos los datos
		if (txtNombre.getText().toString().equals("")) {
    		String err = "";
			if (txtNombre.getText().toString().equals("")) {
				err="Rellenar todos los campos\n";
			}
    		
    		Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("TUS DATOS");
            alert.setHeaderText(null);
            alert.setContentText(err);
            alert.showAndWait();
		}else {
			if (ejProyControllerOlim.isModificar() || ejProyControllerOlim.isEliminar()) {
				if (ejProyControllerOlim.isEliminar()) {
					ejProyControllerOlim.eliminarEvento(cbEvento.getSelectionModel().getSelectedItem());
					
					//Ventana de informacion
		        	Alert alert = new Alert(Alert.AlertType.INFORMATION);
		            alert.setTitle("Info");
		            alert.setHeaderText(null);
		            alert.setContentText("Evento eliminado correctamente");
		            alert.showAndWait();
		          
		            //Cerrar ventana modal
		        	//Me devuelve el elemento al que hice click
		        	Node source = (Node) event.getSource();     
		        	//Me devuelve la ventana donde se encuentra el elemento
		        	Stage stage = (Stage) source.getScene().getWindow();    
		        	stage.close();
				}else {
					ejProyControllerOlim.modificarEvento(cbEvento.getSelectionModel().getSelectedItem(), 
							txtNombre.getText().toString(), cbOlimpiada.getSelectionModel().getSelectedItem(),
							cbDeporte.getSelectionModel().getSelectedItem());
					
					//Ventana de informacion
		        	Alert alert = new Alert(Alert.AlertType.INFORMATION);
		            alert.setTitle("Info");
		            alert.setHeaderText(null);
		            alert.setContentText("Evento editado correctamente");
		            alert.showAndWait();
		          
		            //Cerrar ventana modal
		        	//Me devuelve el elemento al que hice click
		        	Node source = (Node) event.getSource();     
		        	//Me devuelve la ventana donde se encuentra el elemento
		        	Stage stage = (Stage) source.getScene().getWindow();    
		        	stage.close();
				}
			}else {
				if (ejProyControllerOlim.crearEvento(txtNombre.getText().toString(), cbOlimpiada.getSelectionModel().getSelectedItem(), cbDeporte.getSelectionModel().getSelectedItem())) {
					//Ventana de informacion
		        	Alert alert = new Alert(Alert.AlertType.INFORMATION);
		            alert.setTitle("Info");
		            alert.setHeaderText(null);
		            alert.setContentText("Evento añadido correctamente");
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
	                alert.setContentText("Evento ya existe!");
	                alert.showAndWait();
				}
			}
		}
    }
	
	@FXML
    void cambiarEvento(ActionEvent event) {
		//Contenido del comboBox
		Evento d = aD.cargarEventoConNombre(cbEvento.getSelectionModel().getSelectedItem());
		txtNombre.setText(d.getNombre());
		cbOlimpiada.getSelectionModel().select(d.getOlimpiada());
		cbDeporte.getSelectionModel().select(d.getDeporte());
    }
	
	public void setControlerL(EjercicioProyControllerOlimpiadas ej) {
    	this.ejProyControllerOlim= ej;
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		aD = new EventoDao();
		if (ejProyControllerOlim!=null) {
			//Contenido del comboBox
			cbOlimpiada.setItems(FXCollections.observableArrayList(ejProyControllerOlim.cargarOlimpiadasNombre()));
	    	cbOlimpiada.getSelectionModel().selectFirst();
	    	cbDeporte.setItems(FXCollections.observableArrayList(ejProyControllerOlim.cargarDeportesNombre()));
	    	cbDeporte.getSelectionModel().selectFirst();
    		if (ejProyControllerOlim.isModificar()) {
    			btnAccion.setText("Editar");
    			txtTitulo.setText("Editar Evento");
    			cbEvento.setVisible(true);
    			txtCB.setVisible(true);
    			
    			//Contenido del comboBox
    	    	cbEvento.setItems(FXCollections.observableArrayList(ejProyControllerOlim.cargarEventosNombre()));
    	    	cbEvento.getSelectionModel().selectFirst();
    	    	
    	    	Evento d = aD.cargarEventoConNombre(cbEvento.getSelectionModel().getSelectedItem());
				txtNombre.setText(d.getNombre());
				cbOlimpiada.getSelectionModel().select(d.getOlimpiada());
				cbDeporte.getSelectionModel().select(d.getDeporte());
			}
    		
		}
		if (ejProyControllerOlim!=null) {
    		if (ejProyControllerOlim.isEliminar()) {
    			btnAccion.setText("Eliminar");
    			txtTitulo.setText("Eliminar Evento");
    			cbEvento.setVisible(true);
    			txtCB.setVisible(true);
    			txtNombre.setVisible(false);
    			lbNombre.setVisible(false);
    			cbOlimpiada.setVisible(false);
    			lbOlimpiada.setVisible(false);
    			cbDeporte.setVisible(false);
    			lbDeporte.setVisible(false);
    			
    			//Contenido del comboBox
    	    	cbEvento.setItems(FXCollections.observableArrayList(ejProyControllerOlim.cargarEventosNombre()));
    	    	cbEvento.getSelectionModel().selectFirst();
			}
    		
		}
	}
}
