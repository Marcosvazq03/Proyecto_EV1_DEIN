package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import dao.OlimpiadaDao;
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
import model.Olimpiada;


public class EjercicioProyControllerOlimpiada implements Initializable{
	
	private EjercicioProyControllerOlimpiadas ejProyControllerOlim;

	@FXML
    private Button btnAccion;

    @FXML
    private ComboBox<String> cbOlimpiada;

    @FXML
    private Label lbAnio;

    @FXML
    private Label lbCiudad;

    @FXML
    private Label lbNombre;

    @FXML
    private Label lbTemporada;

    @FXML
    private TextField txtAnio;

    @FXML
    private Label txtCB;

    @FXML
    private TextField txtCiudad;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtTemporada;

    @FXML
    private Label txtTitulo;
    
    private OlimpiadaDao aD;
   
	 
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
		if (txtNombre.getText().toString().equals("") || txtAnio.getText().toString().equals("") 
				|| txtTemporada.getText().toString().equals("") || txtCiudad.getText().toString().equals("")) {
    		String err = "";
			if (txtNombre.getText().toString().equals("") || txtAnio.getText().toString().equals("") 
				|| txtTemporada.getText().toString().equals("") || txtCiudad.getText().toString().equals("")) {
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
					ejProyControllerOlim.eliminarOlimpiada(cbOlimpiada.getSelectionModel().getSelectedItem());
					
					//Ventana de informacion
		        	Alert alert = new Alert(Alert.AlertType.INFORMATION);
		            alert.setTitle("Info");
		            alert.setHeaderText(null);
		            alert.setContentText("Olimpiada eliminado correctamente");
		            alert.showAndWait();
		          
		            //Cerrar ventana modal
		        	//Me devuelve el elemento al que hice click
		        	Node source = (Node) event.getSource();     
		        	//Me devuelve la ventana donde se encuentra el elemento
		        	Stage stage = (Stage) source.getScene().getWindow();    
		        	stage.close();
				}else {
					ejProyControllerOlim.modificarOlimpiada(cbOlimpiada.getSelectionModel().getSelectedItem(), txtNombre.getText().toString(), 
							Integer.parseInt(txtAnio.getText().toString()), txtTemporada.getText().toString(), txtCiudad.getText().toString());
					
					//Ventana de informacion
		        	Alert alert = new Alert(Alert.AlertType.INFORMATION);
		            alert.setTitle("Info");
		            alert.setHeaderText(null);
		            alert.setContentText("Olimpiada editado correctamente");
		            alert.showAndWait();
		          
		            //Cerrar ventana modal
		        	//Me devuelve el elemento al que hice click
		        	Node source = (Node) event.getSource();     
		        	//Me devuelve la ventana donde se encuentra el elemento
		        	Stage stage = (Stage) source.getScene().getWindow();    
		        	stage.close();
				}
			}else {
				if (ejProyControllerOlim.crearOlimpiada(txtNombre.getText().toString(), 
						Integer.parseInt(txtAnio.getText().toString()), txtTemporada.getText().toString(), 
						txtCiudad.getText().toString())) {
					//Ventana de informacion
		        	Alert alert = new Alert(Alert.AlertType.INFORMATION);
		            alert.setTitle("Info");
		            alert.setHeaderText(null);
		            alert.setContentText("Olimpiada añadido correctamente");
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
	                alert.setContentText("Olimpiada ya existe!");
	                alert.showAndWait();
				}
			}
		}
    }
	
	@FXML
    void cambiarOlimpiada(ActionEvent event) {
		//Contenido del comboBox
		Olimpiada d = aD.cargarOlimpiadaConNombre(cbOlimpiada.getSelectionModel().getSelectedItem());
		txtNombre.setText(d.getNombre());
		txtAnio.setText(d.getAnio()+"");
		txtTemporada.setText(d.getTemporada());
		txtCiudad.setText(d.getCiudad());
    }
	
	public void setControlerL(EjercicioProyControllerOlimpiadas ej) {
    	this.ejProyControllerOlim= ej;
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		aD = new OlimpiadaDao();
		if (ejProyControllerOlim!=null) {
    		if (ejProyControllerOlim.isModificar()) {
    			btnAccion.setText("Editar");
    			txtTitulo.setText("Editar Olimpiada");
    			cbOlimpiada.setVisible(true);
    			txtCB.setVisible(true);
    			
    			//Contenido del comboBox
    	    	cbOlimpiada.setItems(FXCollections.observableArrayList(ejProyControllerOlim.cargarOlimpiadasNombre()));
    	    	cbOlimpiada.getSelectionModel().selectFirst();
    	    	
    	    	Olimpiada d = aD.cargarOlimpiadaConNombre(cbOlimpiada.getSelectionModel().getSelectedItem());
				txtNombre.setText(d.getNombre());
				txtAnio.setText(d.getAnio()+"");
				txtTemporada.setText(d.getTemporada());
				txtCiudad.setText(d.getCiudad());
			}
    		
		}
		if (ejProyControllerOlim!=null) {
    		if (ejProyControllerOlim.isEliminar()) {
    			btnAccion.setText("Eliminar");
    			txtTitulo.setText("Eliminar Olimpiada");
    			cbOlimpiada.setVisible(true);
    			txtCB.setVisible(true);
    			txtNombre.setVisible(false);
    			lbNombre.setVisible(false);
    			txtAnio.setVisible(false);
    			lbAnio.setVisible(false);
    			txtTemporada.setVisible(false);
    			lbTemporada.setVisible(false);
    			txtCiudad.setVisible(false);
    			lbCiudad.setVisible(false);
    			
    			//Contenido del comboBox
    	    	cbOlimpiada.setItems(FXCollections.observableArrayList(ejProyControllerOlim.cargarOlimpiadasNombre()));
    	    	cbOlimpiada.getSelectionModel().selectFirst();
			}
    		
		}
	}
}
