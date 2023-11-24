package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class EjercicioProyControllerDeportista {
	
	private EjercicioProyControllerOlimpiadas ejProyControllerOlim;

    @FXML
    private ImageView imageSelected;

    @FXML
    private TextField txtAltura;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPeso;

    @FXML
    private TextField txtSexo;
	
    private InputStream imageBinary = null;
	
	/**
	  * Seleccionar imagen
	  * @return
	*/
   protected InputStream seleccionarImagen() {
   	InputStream imageBinary = null;
   	FileChooser fileChooser = new FileChooser();
   	Stage stage = new Stage();
   	fileChooser.setTitle("Seleccionar Imagen ");
   	ExtensionFilter jpgFilter = new ExtensionFilter("Imagen JPG (*.jpg)", "*.jpg");
   	fileChooser.getExtensionFilters().add(jpgFilter);
   	File imageFile = fileChooser.showOpenDialog(stage);
   	if(imageFile != null) {
   		try {
   			Image img = new Image(imageFile.toURI().toString());
				imageBinary = new FileInputStream(imageFile);
				imageSelected.setVisible(true);
	    		imageSelected.setImage(img);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
   	}else {
  		imageSelected.setVisible(false);
  		imageSelected.setImage(null);
   	}
   	return imageBinary;
   }
   
   /**
    * Seleccionar imagen
    * @param event
    */
	 @FXML
	 void click_select_imagen(ActionEvent event) {
		imageBinary = seleccionarImagen();
	 }
	 
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
		//Comprobar que en un numero
		boolean esNumNumero = esNumero(txtNumero);
    	boolean esNumAnio = esNumero(txtAnio);
    	boolean esNumCapacidad = esNumero(txtCapacidad);
    	boolean esNumNTrabajadores = true;
    	boolean esNumNSocios = true;
    	boolean esNumFinanciacion=true;
    	
    	
    	//Alerta introducir todos los datos
    	boolean publico=true;
    	boolean mal = false;
    	
		if (txtNombre.getText().toString().equals("") || txtPais.getText().toString().equals("") 
				|| txtCiudad.getText().toString().equals("") || txtCalle.getText().toString().equals("") 
				|| esNumNumero==false || esNumAnio==false || esNumCapacidad==false || esNumFinanciacion==false 
				|| esNumNTrabajadores==false || esNumNSocios==false || mal) {
    		String err = "";
			if (txtNombre.getText().toString().equals("") || txtPais.getText().toString().equals("") 
				|| txtCiudad.getText().toString().equals("") || txtCalle.getText().toString().equals("") || mal) {
				err="Rellenar todos los campos\n";
			}
			String err2 = "";
			if (esNumNumero==false || esNumAnio==false || esNumCapacidad==false || esNumFinanciacion==false 
    				|| esNumNTrabajadores==false || esNumNSocios==false) {
				err2="Los campos no tienen el correcto formato";
			}
    		
    		Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("TUS DATOS");
            alert.setHeaderText(null);
            alert.setContentText(err+err2);
            alert.showAndWait();
		}else {
			if (ejLControllerAeropuerto.crearAeropuerto(txtNombre.getText().toString(), txtPais.getText().toString(), txtCiudad.getText().toString(), 
					txtCalle.getText().toString(), Integer.parseInt(txtNumero.getText().toString()), Integer.parseInt(txtAnio.getText().toString()), 
					Integer.parseInt(txtCapacidad.getText().toString()), publico, Integer.parseInt(txtFinanciacion.getText().toString()), 
					Integer.parseInt(txtNTrabajadores.getText().toString()), Integer.parseInt(txtNSocio.getText().toString()), imageBinary)) {
				//Ventana de informacion
	        	Alert alert = new Alert(Alert.AlertType.INFORMATION);
	            alert.setTitle("Info");
	            alert.setHeaderText(null);
	            alert.setContentText("Deportista añadido correctamente");
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
                alert.setContentText("Deportista ya existe!");
                alert.showAndWait();
			}
		}
    }
	
	public void setControlerL(EjercicioProyControllerOlimpiadas ej) {
    	this.ejProyControllerOlim= ej;
    }
}
