package dao;


import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.ConexionBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Deportista;
import model.Evento;


public class EventoDao {
    private ConexionBD conexion;
    
    public void insertProducto(String codigo, String nombre, Double precio, int disponible, InputStream imagen) {
    	//Inserta objeto en la BBDD
    	try {
            conexion = new ConexionBD();        	
            
			String consulta = "INSERT INTO productos(codigo, nombre, precio, disponible, imagen) VALUES(?,?,?,?,?)";
			
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);  
        	
        	pstmt.setString(1, codigo);
        	pstmt.setString(2, nombre);
        	pstmt.setDouble(3, precio);
        	pstmt.setInt(4, disponible);
        	pstmt.setBinaryStream(5, imagen);
        	
			pstmt.execute();
			
	        conexion.CloseConexion();
	    } catch (SQLException e) {	    	
	    	System.out.println(e.getMessage());
	    }
    }

	public void modProducto(String codigo, String nombre, Double precio, int disponible, InputStream imagen) {
    	//Modifica objeto en la BBDD
    	try {
	        conexion = new ConexionBD();        	
        	String consulta = "UPDATE examen1.productos SET nombre = ?, precio = ?, disponible = ?, imagen = ? WHERE codigo = ?";
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);  
        	pstmt.setString(1, nombre);
        	pstmt.setDouble(2, precio);
        	pstmt.setInt(3, disponible);
        	pstmt.setBinaryStream(4, imagen);
        	pstmt.setString(5, codigo);
			pstmt.execute();
	        conexion.CloseConexion();
	    } catch (SQLException e) {	    	
	    	System.out.println(e.getMessage());
	    }
    }
	
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
