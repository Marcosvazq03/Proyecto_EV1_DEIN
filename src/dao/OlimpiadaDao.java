package dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.ConexionBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Olimpiada;


public class OlimpiadaDao {
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
        	String consulta = "select id_olimpiada from Olimpiada";
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
        	ResultSet rs = pstmt.executeQuery();   
			 while (rs.next()) {
		            int idA = rs.getInt("id_olimpiada");
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
     * @param anio
     * @param temporada
     * @param ciudad
     */
    public void insertProducto(int codigo, String nombre, int anio, String temporada, String ciudad) {
    	//Inserta objeto en la BBDD
    	try {
            conexion = new ConexionBD();        	
            
			String consulta = "INSERT INTO Olimpiada(id_olimpiada, nombre, anio, temporada, ciudad) VALUES(?,?,?,?,?)";
			
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);  
        	
        	pstmt.setInt(1, codigo);
        	pstmt.setString(2, nombre);
        	pstmt.setInt(3, anio);
        	pstmt.setString(4, temporada);
        	pstmt.setString(5, ciudad);
        	
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
    public ObservableList<String> cargarOlimpiadasNombre()  {
    	ObservableList<String> aeropuertos = FXCollections.observableArrayList();
        try {
        	conexion = new ConexionBD();        	
        	String consulta = "select * from Olimpiada";
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
     * Cargar Olimpiada
     * @param nombre
     * @return
     */
    public Olimpiada cargarOlimpiadaConNombre(String nombre)  {
    	Olimpiada d = null;
        try {
        	conexion = new ConexionBD();        	
        	String consulta = "select * from Olimpiada where nombre ='"+nombre+"'";
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
        	ResultSet rs = pstmt.executeQuery();   
			 while (rs.next()) {
				 int id = rs.getInt("id_olimpiada");
				 int anio = rs.getInt("anio");
				 String temporada = rs.getString("temporada");
				 String ciudad = rs.getString("ciudad");
				 
				 d = new Olimpiada(id, nombre, anio, temporada, ciudad);
			 }
			 rs.close();       
	         conexion.CloseConexion();
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    }    
        return d;    
    }

    /**
     * modificar producto
     * @param codigo
     * @param nombre
     * @param anio
     * @param temporada
     * @param ciudad
     */
	public void modProducto(int codigo, String nombre, int anio, String temporada, String ciudad) {
    	//Modifica objeto en la BBDD
    	try {
	        conexion = new ConexionBD();        	
        	String consulta = "UPDATE olimpiadas.Olimpiada SET nombre = ?, anio = ?, temporada = ?, ciudad = ? WHERE id_olimpiada = ?";
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);  
        	pstmt.setString(1, nombre);
        	pstmt.setInt(2, anio);
        	pstmt.setString(3, temporada);
        	pstmt.setString(4, ciudad);
        	pstmt.setInt(5, codigo);
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
            String consulta = "DELETE FROM olimpiadas.Olimpiada WHERE id_olimpiada = "+codigo;
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
	public ObservableList<Olimpiada> cargarOlimpiadas()  {
    	
    	ObservableList<Olimpiada> producto = FXCollections.observableArrayList();
        try {
            conexion = new ConexionBD();        	
        	String consulta = "select * from Olimpiada";
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
        	ResultSet rs = pstmt.executeQuery();   
			 while (rs.next()) {
				 int id = rs.getInt("id_olimpiada");
	             String nombre = rs.getString("nombre");
	             int anio = rs.getInt("anio");
	             String temporada = rs.getString("temporada");
	             String ciudad = rs.getString("ciudad");
	         
                 Olimpiada a = new Olimpiada(id, nombre, anio, temporada, ciudad);
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
