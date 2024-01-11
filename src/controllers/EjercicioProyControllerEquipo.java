package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import dao.EquipoDao;
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
import model.Equipo;


public class EjercicioProyControllerEquipo implements Initializable{
	
	private EjercicioProyControllerOlimpiadas ejProyControllerOlim;

	@FXML
    private Button btnAccion;

    @FXML
    private ComboBox<String> cbEquipo;

    @FXML
    private Label lbIniciales;

    @FXML
    private Label lbNombre;

    @FXML
    private Label txtCB;

    @FXML
    private TextField txtIniciales;

    @FXML
    private TextField txtNombre;
    
    @FXML
    private Label txtTitulo;
    
    private EquipoDao aD;
   
	 /**
	  * btn Cancelar
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
		if (txtNombre.getText().toString().equals("") || txtIniciales.getText().toString().equals("")) {
    		String err = "";
			if (txtNombre.getText().toString().equals("") || txtIniciales.getText().toString().equals("")) {
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
					ejProyControllerOlim.eliminarEquipo(cbEquipo.getSelectionModel().getSelectedItem());
					
					//Ventana de informacion
		        	Alert alert = new Alert(Alert.AlertType.INFORMATION);
		            alert.setTitle("Info");
		            alert.setHeaderText(null);
		            alert.setContentText("Equipo eliminado correctamente");
		            alert.showAndWait();
		          
		            //Cerrar ventana modal
		        	//Me devuelve el elemento al que hice click
		        	Node source = (Node) event.getSource();     
		        	//Me devuelve la ventana donde se encuentra el elemento
		        	Stage stage = (Stage) source.getScene().getWindow();    
		        	stage.close();
				}else {
					ejProyControllerOlim.modificarEquipo(cbEquipo.getSelectionModel().getSelectedItem(), txtNombre.getText().toString(), txtIniciales.getText().toString());
					
					//Ventana de informacion
		        	Alert alert = new Alert(Alert.AlertType.INFORMATION);
		            alert.setTitle("Info");
		            alert.setHeaderText(null);
		            alert.setContentText("Equipo editado correctamente");
		            alert.showAndWait();
		          
		            //Cerrar ventana modal
		        	//Me devuelve el elemento al que hice click
		        	Node source = (Node) event.getSource();     
		        	//Me devuelve la ventana donde se encuentra el elemento
		        	Stage stage = (Stage) source.getScene().getWindow();    
		        	stage.close();
				}
			}else {
				if (ejProyControllerOlim.crearEquipo(txtNombre.getText().toString(), txtIniciales.getText().toString())) {
					//Ventana de informacion
		        	Alert alert = new Alert(Alert.AlertType.INFORMATION);
		            alert.setTitle("Info");
		            alert.setHeaderText(null);
		            alert.setContentText("Equipo añadido correctamente");
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
	                alert.setContentText("Equipo ya existe!");
	                alert.showAndWait();
				}
			}
		}
    }
	
	/**
	 * Cambiar equipo
	 * @param event
	 */
	@FXML
    void cambiarEquipo(ActionEvent event) {
		//Contenido del comboBox
		Equipo d = aD.cargarEquipoConNombre(cbEquipo.getSelectionModel().getSelectedItem());
		txtNombre.setText(d.getNombre());
		txtIniciales.setText(d.getIniciales());
    }
	
	public void setControlerL(EjercicioProyControllerOlimpiadas ej) {
    	this.ejProyControllerOlim= ej;
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		aD = new EquipoDao();
		if (ejProyControllerOlim!=null) {
    		if (ejProyControllerOlim.isModificar()) {
    			btnAccion.setText("Editar");
    			txtTitulo.setText("Editar Equipo");
    			cbEquipo.setVisible(true);
    			txtCB.setVisible(true);
    			
    			//Contenido del comboBox
    	    	cbEquipo.setItems(FXCollections.observableArrayList(ejProyControllerOlim.cargarEquiposNombre()));
    	    	cbEquipo.getSelectionModel().selectFirst();
    	    	
    	    	Equipo d = aD.cargarEquipoConNombre(cbEquipo.getSelectionModel().getSelectedItem());
				txtNombre.setText(d.getNombre());
				txtIniciales.setText(d.getIniciales());
			}
    		
		}
		if (ejProyControllerOlim!=null) {
    		if (ejProyControllerOlim.isEliminar()) {
    			btnAccion.setText("Eliminar");
    			txtTitulo.setText("Eliminar Equipo");
    			cbEquipo.setVisible(true);
    			txtCB.setVisible(true);
    			txtNombre.setVisible(false);
    			lbNombre.setVisible(false);
    			txtIniciales.setVisible(false);
    			lbIniciales.setVisible(false);
    			
    			//Contenido del comboBox
    	    	cbEquipo.setItems(FXCollections.observableArrayList(ejProyControllerOlim.cargarEquiposNombre()));
    	    	cbEquipo.getSelectionModel().selectFirst();
			}
    		
		}
	}
}
