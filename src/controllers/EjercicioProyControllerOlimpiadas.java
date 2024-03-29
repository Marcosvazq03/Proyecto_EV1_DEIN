package controllers;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import dao.DeporteDao;
import dao.DeportistaDao;
import dao.EquipoDao;
import dao.EventoDao;
import dao.OlimpiadaDao;
import dao.ParticipacionDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Deporte;
import model.Deportista;
import model.Equipo;
import model.Evento;
import model.Olimpiada;

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
    
    private boolean eliminar;
    
    private boolean borrar;
    
    private DeportistaDao aD;
    private DeporteDao aD2;
    private EquipoDao aD3;
    private OlimpiadaDao aD4;
    private ParticipacionDao aD5;
    private EventoDao aE;
    
    public TableView<Deportista> gettbDeportistas() {
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

 	public boolean isEliminar() {
		return eliminar;
	}

 	//DEPORTISTA
	public boolean crearDeportista(String nombre, String sexo, int altura, int peso, InputStream imagen) {
 		Deportista p = new Deportista(aD.ultimoIDAer(), nombre, sexo, altura, peso, null);
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
 			aD.insertProducto(aD.ultimoIDAer(), nombre, sexo, altura, peso, imagen);
 						
 			o1.add(p);
 			
 			return true;
 		}
     }
 	
     public void modificarDeportista(String nombreB, String nombre, String sexo, int peso, int altura, InputStream imagen) {
     	//Modificar objeto de la tabla
		 Deportista pD = aD.cargarDeportistaConNombre(nombreB);
     	Deportista p = new Deportista(pD.getId(), nombre, sexo, peso, altura, imagen);
     	for (int i = 0; i < o1.size(); i++) {
 			if (pD.getId()==o1.get(i).getId()) {
 				aD.modProducto(pD.getId(), nombre, sexo, peso, altura, imagen);
 				
 				o1.set(i, p);
 			}
 		}
     }
     
     public void eliminarDeportista(String nombre) {
      	//Modificar objeto de la tabla
      	for (int i = 0; i < o1.size(); i++) {
      		Deportista d = aD.cargarDeportistaConNombre(nombre);
  			if (d.getId()==o1.get(i).getId()) {
  				aD.elimProducto(d.getId());
  				
  				o1.remove(i);
  			}
  		}
      }
     
     public ObservableList<String> cargarDeportistasNombre() {
       	return aD.cargarDeportistasNombre();
       }
     
     //DEPORTE
     public boolean crearDeporte(String nombre) {

		//Crear 
		aD2.insertProducto(aD2.ultimoIDAer(), nombre);
		
		return true;
  		
      }
  	
      public void modificarDeporte(String nombreB, String nombre) {
      	//Modificar objeto
 		Deporte pD = aD2.cargarDeporteConNombre(nombreB);

  		aD2.modProducto(pD.getId(), nombre);
  				
      }
      
      public void eliminarDeporte(String nombre) {
       	//Modificar objeto
   		Deporte d = aD2.cargarDeporteConNombre(nombre);
		
		aD2.elimProducto(d.getId());
   			
       }
     
     public ObservableList<String> cargarDeportesNombre() {
      	return aD2.cargarDeportesNombre();
      }
     
     //EQUIPO
     public boolean crearEquipo(String nombre, String iniciales) {

		//Crear 
		aD3.insertProducto(aD3.ultimoIDAer(), nombre, iniciales);
		
		return true;
  		
      }
  	
      public void modificarEquipo(String nombreB, String nombre, String iniciales) {
      	//Modificar objeto
 		Equipo pD = aD3.cargarEquipoConNombre(nombreB);

  		aD3.modProducto(pD.getId(), nombre, iniciales);
  				
      }
      
      public void eliminarEquipo(String nombre) {
       	//Modificar objeto
   		Equipo d = aD3.cargarEquipoConNombre(nombre);
		
		aD3.elimProducto(d.getId());
   			
       }
     
     public ObservableList<String> cargarEquiposNombre() {
      	return aD3.cargarEquiposNombre();
      }
     
     //OLIMPIADA
     public boolean crearOlimpiada(String nombre, int anio, String temporada, String ciudad) {

		//Crear 
		aD4.insertProducto(aD4.ultimoIDAer(), nombre, anio, temporada, ciudad);
		
		return true;
  		
      }
  	
      public void modificarOlimpiada(String nombreB, String nombre, int anio, String temporada, String ciudad) {
      	//Modificar objeto
    	Olimpiada pD = aD4.cargarOlimpiadaConNombre(nombreB);

  		aD4.modProducto(pD.getId(), nombre, anio, temporada, ciudad);
  				
      }
      
      public void eliminarOlimpiada(String nombre) {
       	//Modificar objeto
    	Olimpiada d = aD4.cargarOlimpiadaConNombre(nombre);
		
		aD4.elimProducto(d.getId());
   			
       }
     
     public ObservableList<String> cargarOlimpiadasNombre() {
      	
		return aD4.cargarOlimpiadasNombre();
		
      }
     
     //EVENTO
     public boolean crearEvento(String nombre, String olimpiadas, String deporte) {

		//Crear y añadirla a la tabla
		aE.insertProducto(aE.ultimoIDAer(), nombre, olimpiadas, deporte);
		Evento p = aE.cargarEventoConNombre(nombre);
		o2.add(p);
		
		return true;
	
      }
  	
      public void modificarEvento(String nombreB, String nombre, String olimpiadas, String deporte) {
      	//Modificar objeto de la tabla
    	  Evento pD = aE.cargarEventoConNombre(nombreB);
      	for (int i = 0; i < o2.size(); i++) {
  			if (pD.getId()==o2.get(i).getId()) {
  				aE.modProducto(pD.getId(), nombre, olimpiadas, deporte);
  				Evento p = aE.cargarEventoConNombre(nombre);
  				o2.set(i, p);
  			}
  		}
      }
      
      public void eliminarEvento(String nombre) {
       	//Modificar objeto de la tabla
       	for (int i = 0; i < o2.size(); i++) {
       		Evento d = aE.cargarEventoConNombre(nombre);
   			if (d.getId()==o2.get(i).getId()) {
   				aE.elimProducto(d.getId());
   				
   				o2.remove(i);
   			}
   		}
       }
     
     public ObservableList<String> cargarEventosNombre() {
      	return aE.cargarEventosNombre();
      }
     
     //PARTICIPACION
     public boolean crearParticipacion(String deportista, String evento, String equipo, int edad, String medalla) {

		//Crear 
		aD5.insertProducto(deportista, evento, equipo, edad, medalla);
		
		return true;
  		
      }
    
     
	@FXML
    void about(ActionEvent event) {
		if (rbDeportistas.isSelected()) {
			//Comprobar que hay seleccionado un objeto en la tabla
	    	if (tbDeportistas.getSelectionModel().isEmpty()) {
	    		//Ventana error
	            Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setHeaderText(null);
	            alert.setTitle("Error");
	            alert.setContentText("No has seleccionado ningun deportista de la tabla!");
	            alert.showAndWait();
			}else {
				//Informacion objeto de la tabla
				String participaciones = aD.listarParticipaciones(tbDeportistas.getSelectionModel().getSelectedItem().getId());
				if (participaciones.equals("")) {
					participaciones = "Ninguna";
				}
		    	String mensaje="Nombre: "+tbDeportistas.getSelectionModel().getSelectedItem().getNombre()+"\n"
		    			+"Participaciones: \n"
		    			+participaciones;
		    	
		    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
		        alert.setTitle("Informacion");
		        alert.setHeaderText(null);
		        alert.setContentText(mensaje);
		        alert.showAndWait();
		        
			}
		}else {
			
		}
		
    }

	@FXML
    void aniadirDeporte(ActionEvent event) {
		//Abrir ventana modal
		FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/EjercicioProyfxmlDeporte.fxml"));
    	Stage stage = new Stage();
    	EjercicioProyControllerDeporte ejLC = new EjercicioProyControllerDeporte();
    	loader.setController(ejLC);
    	Parent root;
		try {
			root = loader.load();
	    	EjercicioProyControllerDeporte ejLC2 = loader.getController();
	    	ejLC2.setControlerL(this);
	        stage.setScene(new Scene(root,400,300));
	        stage.initOwner(this.tbDeportistas.getScene().getWindow());
	        stage.setTitle("Añadir Deporte");
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void aniadirDeportista(ActionEvent event) {
    	//Abrir ventana modal
		FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/EjercicioProyfxmlDeportista.fxml"));
    	Stage stage = new Stage();
    	EjercicioProyControllerDeportista ejLC = new EjercicioProyControllerDeportista();
    	loader.setController(ejLC);
    	Parent root;
		try {
			root = loader.load();
	    	EjercicioProyControllerDeportista ejLC2 = loader.getController();
	    	ejLC2.setControlerL(this);
	        stage.setScene(new Scene(root,400,600));
	        stage.initOwner(this.tbDeportistas.getScene().getWindow());
	        stage.setTitle("Añadir Deportista");
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void aniadirEquipo(ActionEvent event) {
    	//Abrir ventana modal
		FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/EjercicioProyfxmlEquipo.fxml"));
    	Stage stage = new Stage();
    	EjercicioProyControllerEquipo ejLC = new EjercicioProyControllerEquipo();
    	loader.setController(ejLC);
    	Parent root;
		try {
			root = loader.load();
	    	EjercicioProyControllerEquipo ejLC2 = loader.getController();
	    	ejLC2.setControlerL(this);
	        stage.setScene(new Scene(root,400,400));
	        stage.initOwner(this.tbDeportistas.getScene().getWindow());
	        stage.setTitle("Añadir Equipo");
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void aniadirEvento(ActionEvent event) {
    	//Abrir ventana modal
		FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/EjercicioProyfxmlEvento.fxml"));
    	Stage stage = new Stage();
    	EjercicioProyControllerEvento ejLC = new EjercicioProyControllerEvento();
    	loader.setController(ejLC);
    	ejLC.setControlerL(this);
    	Parent root;
		try {
			root = loader.load();  	
	        stage.setScene(new Scene(root,400,600));
	        stage.initOwner(this.tbDeportistas.getScene().getWindow());
	        stage.setTitle("Añadir Equipo");
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void aniadirOlimpiada(ActionEvent event) {
    	//Abrir ventana modal
		FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/EjercicioProyfxmlOlimpiada.fxml"));
    	Stage stage = new Stage();
    	EjercicioProyControllerOlimpiada ejLC = new EjercicioProyControllerOlimpiada();
    	loader.setController(ejLC);
    	Parent root;
		try {
			root = loader.load();
	    	EjercicioProyControllerOlimpiada ejLC2 = loader.getController();
	    	ejLC2.setControlerL(this);
	        stage.setScene(new Scene(root,400,500));
	        stage.initOwner(this.tbDeportistas.getScene().getWindow());
	        stage.setTitle("Añadir Equipo");
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void aniadirParticipacion(ActionEvent event) {
    	//Abrir ventana modal
		FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/EjercicioProyfxmlParticipacion.fxml"));
    	Stage stage = new Stage();
    	EjercicioProyControllerParticipacion ejLC = new EjercicioProyControllerParticipacion();
    	loader.setController(ejLC);
    	ejLC.setControlerL(this);
    	Parent root;
		try {
			root = loader.load();
	        stage.setScene(new Scene(root,400,600));
	        stage.initOwner(this.tbDeportistas.getScene().getWindow());
	        stage.setTitle("Añadir Equipo");
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @FXML
    void eliminarDeporte(ActionEvent event) {
    	eliminar=true;
    	try {
			//Abrir ventana modal
			FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/EjercicioProyfxmlDeporte.fxml"));
	    	Stage stage = new Stage();
	    	EjercicioProyControllerDeporte ejLC = new EjercicioProyControllerDeporte();
	    	loader.setController(ejLC);
	    	ejLC = loader.getController();
	    	ejLC.setControlerL(this);
	    	Parent root= loader.load();
	        stage.setScene(new Scene(root,400,300));
	        stage.initOwner(this.tbDeportistas.getScene().getWindow());
	        stage.setTitle("Eliminar Deporte");
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.showAndWait();
    	}catch (Exception e) {
    		e.printStackTrace();
		}
    	eliminar=false;
    }

    @FXML
    void eliminarDeportista(ActionEvent event) {
    	eliminar=true;
    	try {
			//Abrir ventana modal
			FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/EjercicioProyfxmlDeportista.fxml"));
	    	Stage stage = new Stage();
	    	EjercicioProyControllerDeportista ejLC = new EjercicioProyControllerDeportista();
	    	loader.setController(ejLC);
	    	ejLC = loader.getController();
	    	ejLC.setControlerL(this);
	    	Parent root= loader.load();
	        stage.setScene(new Scene(root,400,300));
	        stage.initOwner(this.tbDeportistas.getScene().getWindow());
	        stage.setTitle("Eliminar Deportista");
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.showAndWait();
    	}catch (Exception e) {
    		e.printStackTrace();
		}
    	eliminar=false;
    }

    @FXML
    void eliminarEquipo(ActionEvent event) {
    	eliminar=true;
    	try {
			//Abrir ventana modal
			FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/EjercicioProyfxmlEquipo.fxml"));
	    	Stage stage = new Stage();
	    	EjercicioProyControllerEquipo ejLC = new EjercicioProyControllerEquipo();
	    	loader.setController(ejLC);
	    	ejLC = loader.getController();
	    	ejLC.setControlerL(this);
	    	Parent root= loader.load();
	        stage.setScene(new Scene(root,400,300));
	        stage.initOwner(this.tbDeportistas.getScene().getWindow());
	        stage.setTitle("Eliminar Equipo");
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.showAndWait();
    	}catch (Exception e) {
    		e.printStackTrace();
		}
    	eliminar=false;
    }

    @FXML
    void eliminarEvento(ActionEvent event) {
    	eliminar=true;
    	try {
			//Abrir ventana modal
			FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/EjercicioProyfxmlEvento.fxml"));
	    	Stage stage = new Stage();
	    	EjercicioProyControllerEvento ejLC = new EjercicioProyControllerEvento();
	    	loader.setController(ejLC);
	    	ejLC = loader.getController();
	    	ejLC.setControlerL(this);
	    	Parent root= loader.load();
	        stage.setScene(new Scene(root,400,300));
	        stage.initOwner(this.tbDeportistas.getScene().getWindow());
	        stage.setTitle("Eliminar Equipo");
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.showAndWait();
    	}catch (Exception e) {
    		e.printStackTrace();
		}
    	eliminar=false;
    }

    @FXML
    void eliminarOlimpiada(ActionEvent event) {
    	eliminar=true;
    	try {
			//Abrir ventana modal
			FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/EjercicioProyfxmlOlimpiada.fxml"));
	    	Stage stage = new Stage();
	    	EjercicioProyControllerOlimpiada ejLC = new EjercicioProyControllerOlimpiada();
	    	loader.setController(ejLC);
	    	ejLC = loader.getController();
	    	ejLC.setControlerL(this);
	    	Parent root= loader.load();
	        stage.setScene(new Scene(root,400,300));
	        stage.initOwner(this.tbDeportistas.getScene().getWindow());
	        stage.setTitle("Eliminar Equipo");
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.showAndWait();
    	}catch (Exception e) {
    		e.printStackTrace();
		}
    	eliminar=false;
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
    	modificar=true;
		try {
			//Abrir ventana modal
			FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/EjercicioProyfxmlDeporte.fxml"));
	    	Stage stage = new Stage();
	    	EjercicioProyControllerDeporte ejLC = new EjercicioProyControllerDeporte();
	    	loader.setController(ejLC);
	    	ejLC = loader.getController();
	    	ejLC.setControlerL(this);
	    	Parent root= loader.load();
	        stage.setScene(new Scene(root,500,400));
	        stage.initOwner(this.tbDeportistas.getScene().getWindow());
	        stage.setTitle("Editar Deporte");
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.showAndWait();
    	}catch (Exception e) {
    		System.out.println(e.getMessage());
		}
		modificar=false;
    }

    @FXML
    void editarDeportista(ActionEvent event) {
		modificar=true;
		try {
			//Abrir ventana modal
			FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/EjercicioProyfxmlDeportista.fxml"));
	    	Stage stage = new Stage();
	    	EjercicioProyControllerDeportista ejLC = new EjercicioProyControllerDeportista();
	    	loader.setController(ejLC);
	    	ejLC = loader.getController();
	    	ejLC.setControlerL(this);
	    	Parent root= loader.load();
	        stage.setScene(new Scene(root,500,700));
	        stage.initOwner(this.tbDeportistas.getScene().getWindow());
	        stage.setTitle("Editar Deportista");
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.showAndWait();
    	}catch (Exception e) {
    		System.out.println(e.getMessage());
		}
		modificar=false;
    }

    @FXML
    void editarEquipo(ActionEvent event) {
    	modificar=true;
		try {
			//Abrir ventana modal
			FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/EjercicioProyfxmlEquipo.fxml"));
	    	Stage stage = new Stage();
	    	EjercicioProyControllerEquipo ejLC = new EjercicioProyControllerEquipo();
	    	loader.setController(ejLC);
	    	ejLC = loader.getController();
	    	ejLC.setControlerL(this);
	    	Parent root= loader.load();
	        stage.setScene(new Scene(root,500,500));
	        stage.initOwner(this.tbDeportistas.getScene().getWindow());
	        stage.setTitle("Editar Equipo");
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.showAndWait();
    	}catch (Exception e) {
    		System.out.println(e.getMessage());
		}
		modificar=false;
    }

    @FXML
    void editarEvento(ActionEvent event) {
    	modificar=true;
		try {
			//Abrir ventana modal
			FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/EjercicioProyfxmlEvento.fxml"));
	    	Stage stage = new Stage();
	    	EjercicioProyControllerEvento ejLC = new EjercicioProyControllerEvento();
	    	loader.setController(ejLC);
	    	ejLC = loader.getController();
	    	ejLC.setControlerL(this);
	    	Parent root= loader.load();
	        stage.setScene(new Scene(root,500,600));
	        stage.initOwner(this.tbDeportistas.getScene().getWindow());
	        stage.setTitle("Editar Equipo");
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.showAndWait();
    	}catch (Exception e) {
    		System.out.println(e.getMessage());
		}
		modificar=false;
    }

    @FXML
    void editarOlimpiada(ActionEvent event) {
    	modificar=true;
		try {
			//Abrir ventana modal
			FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/EjercicioProyfxmlOlimpiada.fxml"));
	    	Stage stage = new Stage();
	    	EjercicioProyControllerOlimpiada ejLC = new EjercicioProyControllerOlimpiada();
	    	loader.setController(ejLC);
	    	ejLC = loader.getController();
	    	ejLC.setControlerL(this);
	    	Parent root= loader.load();
	        stage.setScene(new Scene(root,500,600));
	        stage.initOwner(this.tbDeportistas.getScene().getWindow());
	        stage.setTitle("Editar Equipo");
	        stage.initModality(Modality.APPLICATION_MODAL);
	        stage.showAndWait();
    	}catch (Exception e) {
    		System.out.println(e.getMessage());
		}
		modificar=false;
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
    	aD2 = new DeporteDao();
    	aD3 = new EquipoDao();
    	aD4 = new OlimpiadaDao();
    	aD5 = new ParticipacionDao();
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
