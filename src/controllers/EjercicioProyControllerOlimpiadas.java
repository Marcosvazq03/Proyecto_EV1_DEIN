package controllers;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import dao.OlimpiadaDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Olimpiada;

public class EjercicioProyControllerOlimpiadas implements Initializable{
    
    private EjercicioLControllerLogin ejLControllerLogin; 
    
    @FXML
    private TableColumn<Olimpiada, Integer> lsAno;

    @FXML
    private TableColumn<Olimpiada, String> lsCalle;

    @FXML
    private TableColumn<Olimpiada, Integer> lsCapacidad;

    @FXML
    private TableColumn<Olimpiada, String> lsCiudad;

    @FXML
    private TableColumn<Olimpiada, Integer> lsId;

    @FXML
    private TableColumn<Olimpiada, Integer> lsNSocios;

    @FXML
    private TableColumn<Olimpiada, String> lsNombre;

    @FXML
    private TableColumn<Olimpiada, Integer> lsNumero;
    
    @FXML
    private TableColumn<Olimpiada, Integer> lsFinanciacion;
    
    @FXML
    private TableColumn<Olimpiada, Integer> lsNTrabajadores;

    @FXML
    private TableColumn<Olimpiada, String> lsPais;

    @FXML
    private TableView<Olimpiada> tbAeropuerto;

    @FXML
    private TextField txtFiltro;
    
    @FXML
    private ToggleGroup gpPrivacidad;
    
    @FXML
    private RadioButton rbPrivados;

    @FXML
    private RadioButton rbPublicos;
    
    public RadioButton getRbPrivados() {
		return rbPrivados;
	}

	public RadioButton getRbPublicos() {
		return rbPublicos;
	}

	@FXML
    private MenuItem miAniadir;
    
    @FXML
    private MenuItem miEditar;
    
    private ObservableList<Olimpiada> o1;
    
    private boolean modificar;
    
    private boolean borrar;
    
    private OlimpiadaDao aD;
    
    public TableView<Olimpiada> getTbAeropuerto() {
		return tbAeropuerto;
	}

	// Crear un FilteredList respaldado por la lista de objetos
    private FilteredList<Olimpiada> filteredList;
    
    public boolean isModificar() {
		return modificar;
	}

    public boolean isBorrar() {
		return borrar;
	}

	/**
	 * Metodos de Aeropuerto
	 * @param nombre
	 * @param pais
	 * @param ciudad
	 * @param calle
	 * @param numero
	 * @param anio
	 * @param capacidad
	 * @param publico
	 * @param financiacion
	 * @param num_trab
	 * @param num_soc
	 * @return
	 */
 	public boolean crearAeropuerto(String nombre, String pais, String ciudad, String calle, int numero, int anio, int capacidad, boolean publico, int financiacion, int num_trab, int num_soc, InputStream imagen) {
     	Olimpiada p = new Olimpiada(aD.ultimoIDAer(), nombre, pais, ciudad, calle, numero, anio, capacidad, null);
     	boolean esta=false;
 		if (o1 !=null) {
 			//Comprobar si existe en la tabla
 			if (o1.contains(p)) {
 				esta=true;
 			}
 		}
 		if (esta) {
 			return false;
 		}else {
 			//Crear y añadirla a la tabla
 			aD.insertAeropuerto(aD.ultimoIDAer(), nombre, pais, ciudad, calle, numero, anio, capacidad, publico, financiacion, num_trab, num_soc, imagen);
 			if (publico) {
				p.setFinanciacion(financiacion);
	 			p.setNTrabajadores(num_trab);
			}else {
				p.setNSocios(num_soc);
			} 			
 			o1.add(p);
 			
 			return true;
 		}
     }
 	
     /**
      * Modificar objeto de la tabla
      * @param nombre
      * @param pais
      * @param ciudad
      * @param calle
      * @param numero
      * @param anio
      * @param capacidad
      * @param publico
      * @param financiacion
      * @param num_trab
      * @param num_soc
      */
     public void modificarAeropuerto(String nombre, String pais, String ciudad, String calle, int numero, int anio, int capacidad, boolean publico, int financiacion, int num_trab, int num_soc) {
     	//Modificar objeto de la tabla
     	Olimpiada p = new Olimpiada(tbAeropuerto.getSelectionModel().getSelectedItem().getId(), nombre, pais, ciudad, calle, numero, anio, capacidad, null);
     	for (int i = 0; i < o1.size(); i++) {
 			if (tbAeropuerto.getSelectionModel().getSelectedItem()==o1.get(i)) {
 				aD.modAeropuerto(tbAeropuerto.getSelectionModel().getSelectedItem().getId(),nombre, pais, ciudad, calle, numero, anio, capacidad, publico, financiacion, num_trab, num_soc);
 				if (publico) {
 					p.setFinanciacion(financiacion);
 		 			p.setNTrabajadores(num_trab);
 				}else {
 					p.setNSocios(num_soc);
 				}
 				o1.set(i, p);
 			}
 		}
     }
     
    // Metodos de Avion
     /**
      * 
      * @param modelo
      * @param numero_asiento
      * @param velocidad_maxima
      * @param activado
      * @param id_aeropuerto
      */
  	public void crearAvion(String modelo, int numero_asiento, int velocidad_maxima, int activado, int id_aeropuerto) {
		//Crear objeto
		aD.insertAvion(aD.ultimoIDAvi(), modelo, numero_asiento, velocidad_maxima, activado, id_aeropuerto);
	}
    
  	/**
  	 * 
  	 * @param nombre
  	 * @param activado
  	 */
	public void modificarAvion(String nombre, int activado) {
	  	//Modificar objeto de la tabla
	  	aD.modAvion(aD.buscarIDModelo(nombre),activado);
	}
	
	/**
	 * 
	 * @param nombre
	 */
	public void borrarAvion(String nombre) {
	  	//Modificar objeto de la tabla
	  	aD.elimAvion(aD.buscarIDModelo(nombre));
	}
    
	@FXML
    void clickPrivado(ActionEvent event) {
    	o1.setAll(aD.cargarAeropuertosPri());
		lsNSocios.setVisible(true);
		lsFinanciacion.setVisible(false);
		lsNTrabajadores.setVisible(false);
    }

    @FXML
    void clickPublico(ActionEvent event) {
    	o1.setAll(aD.cargarAeropuertosPub());
		lsNSocios.setVisible(false);
		lsFinanciacion.setVisible(true);
		lsNTrabajadores.setVisible(true);
    }
    
    @FXML
    void table_mouse_clicked(MouseEvent event) {
    	if(event.getButton().equals(MouseButton.PRIMARY)){
            if(event.getClickCount() == 2 && tbAeropuerto.getSelectionModel().getSelectedIndex() != -1 ){
                infoAeropuerto(null);
            }
        }
    }

    
    /**
     * 
     * @param event
     */
    @FXML
    void aniadirAeropuerto(ActionEvent event) {
    	//Abrir ventana modal
		FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/EjercicioLfxmlAniadirAeropuerto.fxml"));
    	Stage stage = new Stage();
    	EjercicioLControllerAniadirAeropuertos ejLC = new EjercicioLControllerAniadirAeropuertos();
    	loader.setController(ejLC);
    	Parent root;
		try {
			root = loader.load();
	    	EjercicioLControllerAniadirAeropuertos ejLC2 = loader.getController();
	    	ejLC2.setControlerL(this);
	        stage.setScene(new Scene(root,500,700));
	        stage.initOwner(this.tbAeropuerto.getScene().getWindow());
	        stage.setTitle("Añadir Aeropuerto");
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
   
    /**
     * 
     * @param event
     */
    @FXML
    void editarAeropuerto(ActionEvent event) {
    	//Comprobar que hay seleccionado un objeto en la tabla
    	if (tbAeropuerto.getSelectionModel().isEmpty()) {
    		//Ventana error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("No has seleccionado ningun aeropuerto de la tabla!");
            alert.showAndWait();
		}else {
			modificar=true;
			try {
				//Abrir ventana modal
				FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/EjercicioLfxmlAniadirAeropuerto.fxml"));
		    	Stage stage = new Stage();
		    	EjercicioLControllerAniadirAeropuertos ejLC = new EjercicioLControllerAniadirAeropuertos();
		    	loader.setController(ejLC);
		    	EjercicioLControllerAniadirAeropuertos ejLC2 = loader.getController();
		    	ejLC2.setControlerL(this);
		    	Parent root= loader.load();
		        stage.setScene(new Scene(root,500,700));
		        stage.initOwner(this.tbAeropuerto.getScene().getWindow());
		        stage.setTitle("Editar Aeropuerto");
		        stage.initModality(Modality.APPLICATION_MODAL);
		        stage.showAndWait();
	    	}catch (Exception e) {
	    		System.out.println(e.getMessage());
			}
		}
    }
    
    /**
     * 
     * @param event
     */
    @FXML
    void borrarAeropuerto(ActionEvent event) {
    	//Comprobar que hay seleccionado una persona en la tabla
    	if (tbAeropuerto.getSelectionModel().isEmpty()) {
    		//Ventana error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("No has seleccionado ningun aeropuerto de la tabla!");
            alert.showAndWait();
		}else {
			//Eliminar objeto de la tabla
	    	for (int i = 0; i < o1.size(); i++) {
				if (tbAeropuerto.getSelectionModel().getSelectedItem()==o1.get(i)) {
					aD.elimAeropuerto(tbAeropuerto.getSelectionModel().getSelectedItem().getId());
					o1.remove(i);
				}
			}
			
			//Ventana de informacion
	    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
	        alert.setTitle("Info");
	        alert.setHeaderText(null);
	        alert.setContentText("Aeropuerto eliminado correctamente");
	        alert.showAndWait();
	        
		}
    }
    
    /**
     * 
     * @param event
     */
    @FXML
    void infoAeropuerto(ActionEvent event) {
    	//Comprobar que hay seleccionado una persona en la tabla
    	if (tbAeropuerto.getSelectionModel().isEmpty()) {
    		//Ventana error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("No has seleccionado ningun aeropuerto de la tabla!");
            alert.showAndWait();
		}else {
			//Informacion objeto de la tabla
			String financiacion="";
			String nTrabajadores="";
			String nSocios="";
			String privacidad="Publico\n";
			if (rbPrivados.isSelected()) {
				privacidad="Privado\n";
				nSocios="Numero de socios: "+tbAeropuerto.getSelectionModel().getSelectedItem().getNSocios()+"\n";
			}else {
				financiacion="Financiacion: "+tbAeropuerto.getSelectionModel().getSelectedItem().getFinanciacion()+"\n";
				nTrabajadores="Numero de trabajadores: "+tbAeropuerto.getSelectionModel().getSelectedItem().getNTrabajadores()+"\n";
			}
			String aviones = aD.listarAviones(tbAeropuerto.getSelectionModel().getSelectedItem().getId());
	    	String mensaje="Nombre: "+tbAeropuerto.getSelectionModel().getSelectedItem().getNombre()+"\n"
	    			+ "Pais: "+tbAeropuerto.getSelectionModel().getSelectedItem().getPais()+"\n"
	    			+ "Direccion: C. "+tbAeropuerto.getSelectionModel().getSelectedItem().getCalle()+"\n"
	    			+ "Año de inaguracion: "+tbAeropuerto.getSelectionModel().getSelectedItem().getAno()+"\n"
	    			+ "Capacidad: "+tbAeropuerto.getSelectionModel().getSelectedItem().getCapacidad()+"\n"
	    			+ "Aviones: \n"
	    			+aviones
	    			+privacidad
	    			+financiacion
	    			+nTrabajadores
	    			+nSocios;
	    	
	    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
	        alert.setTitle("Informacion");
	        alert.setHeaderText(null);
	        alert.setContentText(mensaje);
	        alert.showAndWait();
	        
		}
    }
    
    /**
     * 
     * @param event
     */
    @FXML
    void aniadirAvion(ActionEvent event) {
    	//Abrir ventana modal
		FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/EjercicioLfxmlAniadirAvion.fxml"));
    	Stage stage = new Stage();
    	EjercicioLControllerAniadirAviones ejLC = new EjercicioLControllerAniadirAviones();
    	loader.setController(ejLC);
    	Parent root;
		try {
			root = loader.load();
	    	EjercicioLControllerAniadirAviones ejLC2 = loader.getController();
	    	ejLC2.setControlerL(this);
	        stage.setScene(new Scene(root,500,500));
	        stage.initOwner(this.tbAeropuerto.getScene().getWindow());
	        stage.setTitle("Añadir Avion");
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
     * 
     * @param event
     */
    @FXML
    void activarDesactivarAvion(ActionEvent event) {
    	modificar=true;
    	//Abrir ventana modal
		try {
			FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/EjercicioLfxmlActDesAvion.fxml"));
	    	Stage stage = new Stage();
	    	EjercicioLControllerAniadirAviones ejLC = new EjercicioLControllerAniadirAviones();
	    	loader.setController(ejLC);
	    	Parent root;
	    	EjercicioLControllerAniadirAviones ejLC2 = loader.getController();
	    	ejLC2.setControlerL(this);
			root = loader.load();
	        stage.setScene(new Scene(root,500,300));
	        stage.initOwner(this.tbAeropuerto.getScene().getWindow());
	        stage.setTitle("Activar/Desactivar Avion");
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
     * 
     * @param event
     */
    @FXML
    void borrarAvion(ActionEvent event) {
    	borrar=true;
    	//Abrir ventana modal
		try {
			FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/EjercicioLfxmlActDesAvion.fxml"));
	    	Stage stage = new Stage();
	    	EjercicioLControllerAniadirAviones ejLC = new EjercicioLControllerAniadirAviones();
	    	loader.setController(ejLC);
	    	ejLC.setControlerL(this);
			Parent root = loader.load();
	        stage.setScene(new Scene(root,500,300));
	        stage.initOwner(this.tbAeropuerto.getScene().getWindow());
	        stage.setTitle("Eliminar Avion");
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void initialize(URL location, ResourceBundle resources) {
    	//Valores de la columna de la tabla
    	lsId.setCellValueFactory(new PropertyValueFactory<Olimpiada, Integer>("id"));
    	lsNombre.setCellValueFactory(new PropertyValueFactory<Olimpiada, String>("nombre"));
    	lsPais.setCellValueFactory(new PropertyValueFactory<Olimpiada, String>("pais"));
    	lsCiudad.setCellValueFactory(new PropertyValueFactory<Olimpiada, String>("ciudad"));
    	lsCalle.setCellValueFactory(new PropertyValueFactory<Olimpiada, String>("calle"));
    	lsNumero.setCellValueFactory(new PropertyValueFactory<Olimpiada, Integer>("numero"));
    	lsAno.setCellValueFactory(new PropertyValueFactory<Olimpiada, Integer>("ano"));
    	lsCapacidad.setCellValueFactory(new PropertyValueFactory<Olimpiada, Integer>("capacidad"));
    	lsNSocios.setCellValueFactory(new PropertyValueFactory<Olimpiada, Integer>("nSocios"));
    	lsFinanciacion.setCellValueFactory(new PropertyValueFactory<Olimpiada, Integer>("financiacion"));
    	lsNTrabajadores.setCellValueFactory(new PropertyValueFactory<Olimpiada, Integer>("nTrabajadores"));
    	
    	o1 = FXCollections.observableArrayList();
    	
    	aD = new OlimpiadaDao();
		
		o1.setAll(aD.cargarAeropuertosPri());
		
    	modificar=false;
    	
    	txtFiltro.setPromptText("Buscar...");
    	
    	filteredList = new FilteredList<Olimpiada>(o1, b -> true);
    	
    	// Agregar un ChangeListener a la propiedad text del TextField
        txtFiltro.textProperty().addListener((observable, oldValue, newValue) -> {
            // Actualizar el predicado de filtrado con el nuevo valor del TextField
            filteredList.setPredicate(objeto -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true; // Mostrar todos los objetos si no hay texto en el TextField
                }
                // Comparar el valor del TextField con la propiedad del objeto
                return objeto.getNombre().contains(newValue);
            });
        });
        
        SortedList<Olimpiada> sortedData = new SortedList<Olimpiada>(filteredList);
    	sortedData.comparatorProperty().bind(tbAeropuerto.comparatorProperty());
    	
    	tbAeropuerto.setItems(sortedData);
    }
    
    public void setControlerL(EjercicioLControllerLogin ej) {
    	this.ejLControllerLogin = ej;
    }
    
}
