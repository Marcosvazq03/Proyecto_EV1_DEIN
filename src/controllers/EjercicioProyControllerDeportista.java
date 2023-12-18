package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import dao.DeportistaDao;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Deportista;
import javafx.stage.FileChooser.ExtensionFilter;

public class EjercicioProyControllerDeportista implements Initializable{
	
	private EjercicioProyControllerOlimpiadas ejProyControllerOlim;

	@FXML
    private Button btnAccion;

    @FXML
    private Button btnImagen;

    @FXML
    private ComboBox<String> cbDeportista;

    @FXML
    private ImageView imageSelected;

    @FXML
    private Label lbAltura;

    @FXML
    private Label lbNombre;

    @FXML
    private Label lbPeso;

    @FXML
    private Label lbSexo;

    @FXML
    private TextField txtAltura;

    @FXML
    private Label txtCB;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPeso;

    @FXML
    private TextField txtSexo;

    @FXML
    private Label txtTitulo;
    
    private DeportistaDao aD;
	
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
	
	private boolean esNumero(TextField txt) {
    	boolean esNum = true;
    	try {
            Integer.parseInt(txt.getText().toString());
        } catch (NumberFormatException excepcion) {
            esNum = false;
        }
    	return esNum;
    }
	
	@FXML
    void aniadir(ActionEvent event) {
		//Comprobar que en un numero
		boolean esNumAltura = esNumero(txtAltura);
    	boolean esNumPeso = esNumero(txtPeso);
    	
    	//Alerta introducir todos los datos
		if (txtNombre.getText().toString().equals("") || txtSexo.getText().toString().equals("") 
				|| txtAltura.getText().toString().equals("") || txtPeso.getText().toString().equals("") 
				|| esNumAltura==false || esNumPeso==false) {
    		String err = "";
			if (txtNombre.getText().toString().equals("") || txtSexo.getText().toString().equals("") 
					|| txtAltura.getText().toString().equals("") || txtPeso.getText().toString().equals("")) {
				err="Rellenar todos los campos\n";
			}
			String err2 = "";
			if (esNumAltura==false || esNumPeso==false) {
				err2="Los campos no tienen el correcto formato";
			}
    		
    		Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("TUS DATOS");
            alert.setHeaderText(null);
            alert.setContentText(err+err2);
            alert.showAndWait();
		}else {
			if (ejProyControllerOlim.isModificar() || ejProyControllerOlim.isEliminar()) {
				if (ejProyControllerOlim.isEliminar()) {
					ejProyControllerOlim.eliminarDeportista(cbDeportista.getSelectionModel().getSelectedItem());
					
					//Ventana de informacion
		        	Alert alert = new Alert(Alert.AlertType.INFORMATION);
		            alert.setTitle("Info");
		            alert.setHeaderText(null);
		            alert.setContentText("Deportista eliminado correctamente");
		            alert.showAndWait();
		          
		            //Cerrar ventana modal
		        	//Me devuelve el elemento al que hice click
		        	Node source = (Node) event.getSource();     
		        	//Me devuelve la ventana donde se encuentra el elemento
		        	Stage stage = (Stage) source.getScene().getWindow();    
		        	stage.close();
				}else {
					ejProyControllerOlim.modificarDeportista(txtNombre.getText().toString(), txtSexo.getText().toString(),
						Integer.parseInt(txtPeso.getText().toString()), Integer.parseInt(txtAltura.getText().toString()), imageBinary);
					
					//Ventana de informacion
		        	Alert alert = new Alert(Alert.AlertType.INFORMATION);
		            alert.setTitle("Info");
		            alert.setHeaderText(null);
		            alert.setContentText("Deportista editado correctamente");
		            alert.showAndWait();
		          
		            //Cerrar ventana modal
		        	//Me devuelve el elemento al que hice click
		        	Node source = (Node) event.getSource();     
		        	//Me devuelve la ventana donde se encuentra el elemento
		        	Stage stage = (Stage) source.getScene().getWindow();    
		        	stage.close();
				}
			}else {
				if (ejProyControllerOlim.crearDeportista(txtNombre.getText().toString(), txtSexo.getText().toString(), 
						Integer.parseInt(txtAltura.getText().toString()), Integer.parseInt(txtPeso.getText().toString()), imageBinary)) {
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
    }
	
	@FXML
    void cambiarDeportista(ActionEvent event) {
		//Contenido del comboBox
		Deportista d = aD.cargarDeportistaConNombre(cbDeportista.getSelectionModel().getSelectedItem());
		txtNombre.setText(d.getNombre());
		txtSexo.setText(d.getSexo());
		txtPeso.setText(d.getPeso()+"");
		txtAltura.setText(d.getAltura()+"");
		if(d.getImage() != null) {
			imageSelected.setImage(new Image(d.getImage()));
		}else {
			imageSelected.setImage(null);
		}
    }
	
	public void setControlerL(EjercicioProyControllerOlimpiadas ej) {
    	this.ejProyControllerOlim= ej;
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		aD = new DeportistaDao();
		if (ejProyControllerOlim!=null) {
    		if (ejProyControllerOlim.isModificar()) {
    			btnAccion.setText("Editar");
    			txtTitulo.setText("Editar deportista");
    			cbDeportista.setVisible(true);
    			txtCB.setVisible(true);
    			
    			//Contenido del comboBox
    	    	cbDeportista.setItems(FXCollections.observableArrayList(ejProyControllerOlim.cargarDeportistasNombre()));
    	    	cbDeportista.getSelectionModel().selectFirst();
    	    	
    	    	Deportista d = aD.cargarDeportistaConNombre(cbDeportista.getSelectionModel().getSelectedItem());
				txtNombre.setText(d.getNombre());
				txtSexo.setText(d.getSexo());
				txtPeso.setText(d.getPeso()+"");
				txtAltura.setText(d.getAltura()+"");
				if(d.getImage() != null) {
					imageSelected.setImage(new Image(d.getImage()));
				}
			}
    		
		}
		if (ejProyControllerOlim!=null) {
    		if (ejProyControllerOlim.isEliminar()) {
    			btnAccion.setText("Eliminar");
    			txtTitulo.setText("Eliminar deportista");
    			cbDeportista.setVisible(true);
    			txtCB.setVisible(true);
    			txtNombre.setVisible(false);
    			txtSexo.setVisible(false);
    			txtPeso.setVisible(false);
    			txtAltura.setVisible(false);
    			lbNombre.setVisible(false);
    			lbSexo.setVisible(false);
    			lbPeso.setVisible(false);
    			lbAltura.setVisible(false);
    			btnImagen.setVisible(false);
    			imageSelected.setVisible(false);
    			
    			//Contenido del comboBox
    	    	cbDeportista.setItems(FXCollections.observableArrayList(ejProyControllerOlim.cargarDeportistasNombre()));
    	    	cbDeportista.getSelectionModel().selectFirst();
			}
    		
		}
	}
}
