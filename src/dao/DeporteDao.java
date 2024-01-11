package dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.ConexionBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Deporte;


public class DeporteDao {
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
        	String consulta = "select id_deporte from Deporte";
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
        	ResultSet rs = pstmt.executeQuery();   
			 while (rs.next()) {
		            int idA = rs.getInt("id_deporte");
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
     */
    public void insertProducto(int codigo, String nombre) {
    	//Inserta objeto en la BBDD
    	try {
            conexion = new ConexionBD();        	
            
			String consulta = "INSERT INTO Deporte(id_deporte, nombre) VALUES(?,?)";
			
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);  
        	
        	pstmt.setInt(1, codigo);
        	pstmt.setString(2, nombre);
        	
			pstmt.execute();
			
	        conexion.CloseConexion();
	    } catch (SQLException e) {	    	
	    	System.out.println(e.getMessage());
	    }
    }
    
    /**
     * Cargar nombres
     * @return
     */
    public ObservableList<String> cargarDeportesNombre()  {
    	ObservableList<String> aeropuertos = FXCollections.observableArrayList();
        try {
        	conexion = new ConexionBD();        	
        	String consulta = "select * from Deporte";
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
     * Cargar Deporte
     * @param nombre
     * @return
     */
    public Deporte cargarDeporteConNombre(String nombre)  {
    	Deporte d = null;
        try {
        	conexion = new ConexionBD();        	
        	String consulta = "select * from Deporte where nombre ='"+nombre+"'";
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
        	ResultSet rs = pstmt.executeQuery();   
			 while (rs.next()) {
				 int id = rs.getInt("id_deporte");
				 
				 d = new Deporte(id, nombre);
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
     */
	public void modProducto(int codigo, String nombre) {
    	//Modifica objeto en la BBDD
    	try {
	        conexion = new ConexionBD();        	
        	String consulta = "UPDATE olimpiadas.Deporte SET nombre = ? WHERE id_deporte = ?";
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);  
        	pstmt.setString(1, nombre);
        	pstmt.setInt(2, codigo);
			pstmt.execute();
	        conexion.CloseConexion();
	    } catch (SQLException e) {	    	
	    	System.out.println(e.getMessage());
	    }
    }
	
	/**
	 * Eliminar producto
	 * @param codigo
	 */
	public void elimProducto(int codigo) {
    	//Eliminar objeto en la BBDD
    	try {
            conexion = new ConexionBD();        	
            String consulta = "DELETE FROM olimpiadas.Deporte WHERE id_deporte = "+codigo;
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
	public ObservableList<Deporte> cargarDeportes()  {
    	
    	ObservableList<Deporte> producto = FXCollections.observableArrayList();
        try {
            conexion = new ConexionBD();        	
        	String consulta = "select * from Deporte";
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
        	ResultSet rs = pstmt.executeQuery();   
			 while (rs.next()) {
				 int id = rs.getInt("id_deporte");
	             String nombre = rs.getString("nombre");
	         
                 Deporte a = new Deporte(id, nombre);
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
