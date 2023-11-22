package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import dao.DeportistaDao;
import dao.EventoDao;
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
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import model.Deportista;
import model.Evento;

public class EjercicioProyControllerOlimpiadas implements Initializable{
    
	@FXML
    private ContextMenu ctMenu;

    @FXML
    private ToggleGroup gpOlimpiadas;

    @FXML
    private RadioButton rbDeportistas;

    @FXML
    private RadioButton rbEventos;

    @FXML
    private TableColumn<Deportista, Integer> lsAltura;

    @FXML
    private TableColumn<Evento, Integer> lsAnio;

    @FXML
    private TableColumn<Evento, String> lsCiudad;

    @FXML
    private TableColumn<Evento, String> lsDeporte;

    @FXML
    private TableColumn<Deportista, String> lsNombre;
    
    @FXML
    private TableColumn<Evento, String> lsNombre1;

    @FXML
    private TableColumn<Evento, String> lsOlimpiada;

    @FXML
    private TableColumn<Deportista, Integer> lsPeso;

    @FXML
    private TableColumn<Deportista, String> lsSexo;

    @FXML
    private TableColumn<Evento, String> lsTemporada;

    @FXML
    private TableView<Deportista> tbDeportistas;
    
    @FXML
    private TableView<Evento> tbEventos;

    @FXML
    private TextField txtFiltro;
    
    @FXML
    private TextField txtFiltro1;
    
    public RadioButton getRbDeportistas() {
		return rbDeportistas;
	}

	public RadioButton getRbEventos() {
		return rbEventos;
	}

	private ObservableList<Deportista> o1;
	private ObservableList<Evento> o2;
    
    private boolean modificar;
    
    private boolean borrar;
    
    private DeportistaDao aD;
    
    private EventoDao aE;
    
    public TableView<?> gettbDeportistas() {
		return tbDeportistas;
	}

	// Crear un FilteredList respaldado por la lista de objetos
    private FilteredList<Deportista> filteredList;
    private FilteredList<Evento> filteredList1;
    
    public boolean isModificar() {
		return modificar;
	}

    public boolean isBorrar() {
		return borrar;
	}

 	public boolean crearAeropuerto(String nombre, String pais, String ciudad, String calle, int numero, int anio, int capacidad, boolean publico, int financiacion, int num_trab, int num_soc, InputStream imagen) {
 		Deportista d = (Deportista) tbDeportistas.getSelectionModel().getSelectedItem();
 		Deportista p = new Deportista(aD.ultimoIDAer(), nombre, pais, ciudad, calle, numero, anio, capacidad, null);
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
 	
     public void modificarAeropuerto(String nombre, String pais, String ciudad, String calle, int numero, int anio, int capacidad, boolean publico, int financiacion, int num_trab, int num_soc) {
     	//Modificar objeto de la tabla
     	Deportista p = new Deportista(tbDeportistas.getSelectionModel().getSelectedItem().getId(), nombre, pais, ciudad, calle, numero, anio, capacidad, null);
     	for (int i = 0; i < o1.size(); i++) {
 			if (tbDeportistas.getSelectionModel().getSelectedItem()==o1.get(i)) {
 				aD.modAeropuerto(tbDeportistas.getSelectionModel().getSelectedItem().getId(),nombre, pais, ciudad, calle, numero, anio, capacidad, publico, financiacion, num_trab, num_soc);
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
  	public void crearAvion(String modelo, int numero_asiento, int velocidad_maxima, int activado, int id_aeropuerto) {
		//Crear objeto
		aD.insertAvion(aD.ultimoIDAvi(), modelo, numero_asiento, velocidad_maxima, activado, id_aeropuerto);
	}
    
	public void modificarAvion(String nombre, int activado) {
	  	//Modificar objeto de la tabla
	  	aD.modAvion(aD.buscarIDModelo(nombre),activado);
	}
	
	public void borrarAvion(String nombre) {
	  	//Modificar objeto de la tabla
	  	aD.elimAvion(aD.buscarIDModelo(nombre));
	}
    
	@FXML
    void about(ActionEvent event) {
		
		//Comprobar que hay seleccionado un objeto en la tabla
    	if (tbDeportistas.getSelectionModel().isEmpty()) {
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
				nSocios="Numero de socios: "+tbDeportistas.getSelectionModel().getSelectedItem().getNSocios()+"\n";
			}else {
				financiacion="Financiacion: "+tbDeportistas.getSelectionModel().getSelectedItem().getFinanciacion()+"\n";
				nTrabajadores="Numero de trabajadores: "+tbDeportistas.getSelectionModel().getSelectedItem().getNTrabajadores()+"\n";
			}
			String aviones = aD.listarAviones(tbDeportistas.getSelectionModel().getSelectedItem().getId());
	    	String mensaje="Nombre: "+tbDeportistas.getSelectionModel().getSelectedItem().getNombre()+"\n"
	    			+ "Pais: "+tbDeportistas.getSelectionModel().getSelectedItem().getPais()+"\n"
	    			+ "Direccion: C. "+tbDeportistas.getSelectionModel().getSelectedItem().getCalle()+"\n"
	    			+ "Año de inaguracion: "+tbDeportistas.getSelectionModel().getSelectedItem().getAno()+"\n"
	    			+ "Capacidad: "+tbDeportistas.getSelectionModel().getSelectedItem().getCapacidad()+"\n"
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

	@FXML
    void aniadirDeporte(ActionEvent event) {
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
	        stage.initOwner(this.tbDeportistas.getScene().getWindow());
	        stage.setTitle("Añadir Aeropuerto");
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void aniadirDeportista(ActionEvent event) {

    }

    @FXML
    void aniadirEquipo(ActionEvent event) {

    }

    @FXML
    void aniadirEvento(ActionEvent event) {

    }

    @FXML
    void aniadirOlimpiada(ActionEvent event) {

    }

    @FXML
    void aniadirParticipacion(ActionEvent event) {

    }
    
    @FXML
    void eliminarDeporte(ActionEvent event) {
    	//Comprobar que hay seleccionado una persona en la tabla
    	if (tbDeportistas.getSelectionModel().isEmpty()) {
    		//Ventana error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("No has seleccionado ningun aeropuerto de la tabla!");
            alert.showAndWait();
		}else {
			//Eliminar objeto de la tabla
	    	for (int i = 0; i < o1.size(); i++) {
				if (tbDeportistas.getSelectionModel().getSelectedItem()==o1.get(i)) {
					aD.elimAeropuerto(tbDeportistas.getSelectionModel().getSelectedItem().getId());
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

    @FXML
    void eliminarDeportista(ActionEvent event) {

    }

    @FXML
    void eliminarEquipo(ActionEvent event) {

    }

    @FXML
    void eliminarEvento(ActionEvent event) {

    }

    @FXML
    void eliminarOlimpiada(ActionEvent event) {

    }

    @FXML
    void eliminarParticipacion(ActionEvent event) {

    }

    @FXML
    void clickDeportistas(ActionEvent event) {
    	tbDeportistas.setVisible(true);
    	tbEventos.setVisible(false);
    	txtFiltro.setVisible(true);
    	txtFiltro1.setVisible(false);
    }

    @FXML
    void clickEventos(ActionEvent event) {
    	tbDeportistas.setVisible(false);
    	tbEventos.setVisible(true);
    	txtFiltro.setVisible(false);
    	txtFiltro1.setVisible(true);
    }
    
    @FXML
    void editarDeporte(ActionEvent event) {
    	//Comprobar que hay seleccionado un objeto en la tabla
    	if (tbDeportistas.getSelectionModel().isEmpty()) {
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
		        stage.initOwner(this.tbDeportistas.getScene().getWindow());
		        stage.setTitle("Editar Aeropuerto");
		        stage.initModality(Modality.APPLICATION_MODAL);
		        stage.showAndWait();
	    	}catch (Exception e) {
	    		System.out.println(e.getMessage());
			}
		}
    }

    @FXML
    void editarDeportista(ActionEvent event) {

    }

    @FXML
    void editarEquipo(ActionEvent event) {

    }

    @FXML
    void editarEvento(ActionEvent event) {

    }

    @FXML
    void editarOlimpiada(ActionEvent event) {

    }

    @FXML
    void editarParticipacion(ActionEvent event) {

    }

    @FXML
    void table_mouse_clicked(MouseEvent event) {
    	if(event.getButton().equals(MouseButton.PRIMARY)){
            if(event.getClickCount() == 2 && tbDeportistas.getSelectionModel().getSelectedIndex() != -1 ){
                about(null);
            }
        }
    }
    
    public void initialize(URL location, ResourceBundle resources) {
    	//Valores de la columna de la tabla
    	lsNombre.setCellValueFactory(new PropertyValueFactory<Deportista, String>("nombre"));
    	lsSexo.setCellValueFactory(new PropertyValueFactory<Deportista, String>("sexo"));
    	lsPeso.setCellValueFactory(new PropertyValueFactory<Deportista, Integer>("peso"));
    	lsAltura.setCellValueFactory(new PropertyValueFactory<Deportista, Integer>("altura"));
    	
    	lsNombre1.setCellValueFactory(new PropertyValueFactory<Evento, String>("nombre"));
    	lsOlimpiada.setCellValueFactory(new PropertyValueFactory<Evento, String>("olimpiada"));
    	lsAnio.setCellValueFactory(new PropertyValueFactory<Evento, Integer>("anio"));
    	lsTemporada.setCellValueFactory(new PropertyValueFactory<Evento, String>("temporada"));
    	lsCiudad.setCellValueFactory(new PropertyValueFactory<Evento, String>("ciudad"));
    	lsDeporte.setCellValueFactory(new PropertyValueFactory<Evento, String>("deporte"));
    	
    	
    	o1 = FXCollections.observableArrayList();
    	o2 = FXCollections.observableArrayList();
    	
    	aD = new DeportistaDao();
    	aE = new EventoDao();
		
		o1.setAll(aD.cargarDeportistas());
		o2.setAll(aE.cargarEventos());
		
    	modificar=false;
    	
    	txtFiltro.setPromptText("Buscar...");
    	txtFiltro1.setPromptText("Buscar...");
    	
    	filteredList = new FilteredList<Deportista>(o1, b -> true);
    	filteredList1 = new FilteredList<Evento>(o2, b -> true);
    	
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
        txtFiltro1.textProperty().addListener((observable, oldValue, newValue) -> {
            // Actualizar el predicado de filtrado con el nuevo valor del TextField
            filteredList1.setPredicate(objeto -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true; // Mostrar todos los objetos si no hay texto en el TextField
                }
                // Comparar el valor del TextField con la propiedad del objeto
                return objeto.getNombre().contains(newValue);
            });
        });
        
        SortedList<Deportista> sortedData = new SortedList<Deportista>(filteredList);
    	sortedData.comparatorProperty().bind(tbDeportistas.comparatorProperty());
    	SortedList<Evento> sortedData1 = new SortedList<Evento>(filteredList1);
    	sortedData1.comparatorProperty().bind(tbEventos.comparatorProperty());
    	
    	tbDeportistas.setItems(sortedData);
    	tbEventos.setItems(sortedData1);
    }
    
}
