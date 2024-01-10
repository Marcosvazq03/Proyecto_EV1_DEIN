package dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.ConexionBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Evento;


public class EventoDao {
    private ConexionBD conexion;
    
    /**
     * Ultimo ID
     * @return
     */
    public int ultimoIDAer() {
    	//Saca el ultimo id
    	int id=-1;
    	try {
            conexion = new ConexionBD();        	
        	String consulta = "select id_evento from Evento";
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
        	ResultSet rs = pstmt.executeQuery();   
			 while (rs.next()) {
		            int idA = rs.getInt("id_evento");
		            if (idA>id) {
						id=idA;
					}
			 }             
			rs.close();       
	        conexion.CloseConexion();
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    }    
    	id=id+1;
    	return id;
    }
    
    /**
     * Insertar producto
     * @param codigo
     * @param nombre
     * @param olimpiada
     * @param deporte
     */
    public void insertProducto(int codigo, String nombre, String olimpiada, String deporte) {
    	//Inserta objeto en la BBDD
    	try {
             conexion = new ConexionBD();        	
             String consulta = "select * from Olimpiada where nombre = '"+olimpiada+"'";
        	 PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
        	 ResultSet rs = pstmt.executeQuery();   
			 rs.next();
	         int id_olimpiada = rs.getInt("id_olimpiada");
	         
	         consulta = "select * from Deporte where nombre = '"+deporte+"'";
        	 pstmt = conexion.getConexion().prepareStatement(consulta);      
        	 rs = pstmt.executeQuery();   
			 rs.next();
	         int id_deporte = rs.getInt("id_deporte");
			 
			consulta = "INSERT INTO Evento(id_evento, nombre, id_olimpiada, id_deporte) VALUES(?,?,?,?)";
			
        	pstmt = conexion.getConexion().prepareStatement(consulta);  
        	
        	pstmt.setInt(1, codigo);
        	pstmt.setString(2, nombre);
        	pstmt.setInt(3, id_olimpiada);
        	pstmt.setInt(4, id_deporte);
        	
			pstmt.execute();
			
	        conexion.CloseConexion();
	    } catch (SQLException e) {	    	
	    	System.out.println(e.getMessage());
	    }
    }
    
    /**
     * Cargar nombre
     * @return
     */
    public ObservableList<String> cargarEventosNombre()  {
    	ObservableList<String> aeropuertos = FXCollections.observableArrayList();
        try {
        	conexion = new ConexionBD();        	
        	String consulta = "select * from Evento";
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
        	ResultSet rs = pstmt.executeQuery();   
			 while (rs.next()) {
				 String nombre = rs.getString("nombre");
				 aeropuertos.add(nombre);
			 }
			 rs.close();       
	         conexion.CloseConexion();
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    }    
        return aeropuertos;    
    }
    
    /**
     * Cargar Evento
     * @param nombre
     * @return
     */
    public Evento cargarEventoConNombre(String nombre)  {
    	Evento d = null;
        try {
        	conexion = new ConexionBD();        	
        	String consulta = "select * from Evento where nombre ='"+nombre+"'";
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
        	ResultSet rs = pstmt.executeQuery();   
			 while (rs.next()) {
				 int id = rs.getInt("id_evento");
				 int id_olimpiada = rs.getInt("id_olimpiada");
				 int id_deporte = rs.getInt("id_deporte");
				 consulta = "select * from Olimpiada where id_olimpiada = "+id_olimpiada;
	         	 pstmt = conexion.getConexion().prepareStatement(consulta);      
	         	 rs = pstmt.executeQuery();   
	 			 rs.next();
	 	         String olimpiada = rs.getString("nombre");
	 	         int anio = rs.getInt("anio");
	 	         String temporada = rs.getString("temporada");
	 	         String ciudad = rs.getString("ciudad");
	 	         
	 	         consulta = "select * from Deporte where id_deporte = "+id_deporte;
	         	 pstmt = conexion.getConexion().prepareStatement(consulta);      
	         	 rs = pstmt.executeQuery();   
	 			 rs.next();
	 	         String deporte = rs.getString("nombre");
	 			 
                 d = new Evento(id, nombre, olimpiada, anio, temporada, ciudad, deporte);
			 }
			 rs.close();       
	         conexion.CloseConexion();
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    }    
        return d;    
    }

    /**
     * Modificar producto
     * @param codigo
     * @param nombre
     * @param olimpiada
     * @param deporte
     */
	public void modProducto(int codigo, String nombre, String olimpiada, String deporte) {
    	//Modifica objeto en la BBDD
    	try {
	        conexion = new ConexionBD();
	        String consulta = "select * from Olimpiada where nombre = '"+olimpiada+"'";
       	 	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
       	 	ResultSet rs = pstmt.executeQuery();   
			 rs.next();
	         int id_olimpiada = rs.getInt("id_olimpiada");
	         
	         consulta = "select * from Deporte where nombre = '"+deporte+"'";
	         pstmt = conexion.getConexion().prepareStatement(consulta);      
	         rs = pstmt.executeQuery();   
			 rs.next();
	         int id_deporte = rs.getInt("id_deporte");
        	consulta = "UPDATE olimpiadas.Evento SET nombre = ?, id_olimpiada = ?, id_deporte = ? WHERE id_evento = ?";
        	pstmt = conexion.getConexion().prepareStatement(consulta);  
        	pstmt.setString(1, nombre);
        	pstmt.setInt(2, id_olimpiada);
        	pstmt.setInt(3, id_deporte);
        	pstmt.setInt(4, codigo);
			pstmt.execute();
	        conexion.CloseConexion();
	    } catch (SQLException e) {	    	
	    	System.out.println(e.getMessage());
	    }
    }
	
	/**
	 * Eliminar Producto
	 * @param codigo
	 */
	public void elimProducto(int codigo) {
    	//Eliminar objeto en la BBDD
    	try {
            conexion = new ConexionBD();        	
            String consulta = "DELETE FROM olimpiadas.Evento WHERE id_evento = "+codigo;
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);  
			pstmt.execute();
	        conexion.CloseConexion();
	    } catch (SQLException e) {	    	
	    	System.out.println(e.getMessage());
	    }
    }
	
	/**
	 * Cargar Productos
	 * @return
	 */
	public ObservableList<Evento> cargarEventos()  {
    	
    	ObservableList<Evento> producto = FXCollections.observableArrayList();
        try {
            conexion = new ConexionBD();        	
        	String consulta = "select * from Evento";
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
        	ResultSet rs = pstmt.executeQuery();   
			 while (rs.next()) {
				 int id = rs.getInt("id_evento");
	             String nombre = rs.getString("nombre");
	             int id_olimpiada = rs.getInt("id_olimpiada");
	             int id_deporte = rs.getInt("id_deporte");
	             
	             consulta = "select * from Olimpiada where id_olimpiada = "+id_olimpiada;
	         	 pstmt = conexion.getConexion().prepareStatement(consulta);      
	         	 rs = pstmt.executeQuery();   
	 			 rs.next();
	 	         String olimpiada = rs.getString("nombre");
	 	         int anio = rs.getInt("anio");
	 	         String temporada = rs.getString("temporada");
	 	         String ciudad = rs.getString("ciudad");
	 	         
	 	         consulta = "select * from Deporte where id_deporte = "+id_deporte;
	         	 pstmt = conexion.getConexion().prepareStatement(consulta);      
	         	 rs = pstmt.executeQuery();   
	 			 rs.next();
	 	         String deporte = rs.getString("nombre");
	 			 
                 Evento a = new Evento(id, nombre, olimpiada, anio, temporada, ciudad, deporte);
                 producto.add(a);
			 }             
			rs.close();       
	        conexion.CloseConexion();
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    }    
        return producto;    
    }
    
}
