package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import dao.DeporteDao;
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
import model.Deporte;


public class EjercicioProyControllerDeporte implements Initializable{
	
	private EjercicioProyControllerOlimpiadas ejProyControllerOlim;

	@FXML
    private Button btnAccion;

    @FXML
    private ComboBox<String> cbDeporte;

    @FXML
    private Label lbNombre;

    @FXML
    private Label txtCB;

    @FXML
    private TextField txtNombre;
    
    @FXML
    private Label txtTitulo;
    
    private DeporteDao aD;
   
	 
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
					ejProyControllerOlim.eliminarDeporte(cbDeporte.getSelectionModel().getSelectedItem());
					
					//Ventana de informacion
		        	Alert alert = new Alert(Alert.AlertType.INFORMATION);
		            alert.setTitle("Info");
		            alert.setHeaderText(null);
		            alert.setContentText("Deporte eliminado correctamente");
		            alert.showAndWait();
		          
		            //Cerrar ventana modal
		        	//Me devuelve el elemento al que hice click
		        	Node source = (Node) event.getSource();     
		        	//Me devuelve la ventana donde se encuentra el elemento
		        	Stage stage = (Stage) source.getScene().getWindow();    
		        	stage.close();
				}else {
					ejProyControllerOlim.modificarDeporte(cbDeporte.getSelectionModel().getSelectedItem(), txtNombre.getText().toString());
					
					//Ventana de informacion
		        	Alert alert = new Alert(Alert.AlertType.INFORMATION);
		            alert.setTitle("Info");
		            alert.setHeaderText(null);
		            alert.setContentText("Deporte editado correctamente");
		            alert.showAndWait();
		          
		            //Cerrar ventana modal
		        	//Me devuelve el elemento al que hice click
		        	Node source = (Node) event.getSource();     
		        	//Me devuelve la ventana donde se encuentra el elemento
		        	Stage stage = (Stage) source.getScene().getWindow();    
		        	stage.close();
				}
			}else {
				if (ejProyControllerOlim.crearDeporte(txtNombre.getText().toString())) {
					//Ventana de informacion
		        	Alert alert = new Alert(Alert.AlertType.INFORMATION);
		            alert.setTitle("Info");
		            alert.setHeaderText(null);
		            alert.setContentText("Deporte añadido correctamente");
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
	                alert.setContentText("Deporte ya existe!");
	                alert.showAndWait();
				}
			}
		}
    }
	
	@FXML
    void cambiarDeporte(ActionEvent event) {
		//Contenido del comboBox
		Deporte d = aD.cargarDeporteConNombre(cbDeporte.getSelectionModel().getSelectedItem());
		txtNombre.setText(d.getNombre());
    }
	
	public void setControlerL(EjercicioProyControllerOlimpiadas ej) {
    	this.ejProyControllerOlim= ej;
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		aD = new DeporteDao();
		if (ejProyControllerOlim!=null) {
    		if (ejProyControllerOlim.isModificar()) {
    			btnAccion.setText("Editar");
    			txtTitulo.setText("Editar deporte");
    			cbDeporte.setVisible(true);
    			txtCB.setVisible(true);
    			
    			//Contenido del comboBox
    	    	cbDeporte.setItems(FXCollections.observableArrayList(ejProyControllerOlim.cargarDeportesNombre()));
    	    	cbDeporte.getSelectionModel().selectFirst();
    	    	
    	    	Deporte d = aD.cargarDeporteConNombre(cbDeporte.getSelectionModel().getSelectedItem());
				txtNombre.setText(d.getNombre());
			}
    		
		}
		if (ejProyControllerOlim!=null) {
    		if (ejProyControllerOlim.isEliminar()) {
    			btnAccion.setText("Eliminar");
    			txtTitulo.setText("Eliminar deporte");
    			cbDeporte.setVisible(true);
    			txtCB.setVisible(true);
    			txtNombre.setVisible(false);
    			lbNombre.setVisible(false);
    			
    			//Contenido del comboBox
    	    	cbDeporte.setItems(FXCollections.observableArrayList(ejProyControllerOlim.cargarDeportesNombre()));
    	    	cbDeporte.getSelectionModel().selectFirst();
			}
    		
		}
	}
}
