package dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.ConexionBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Equipo;


public class EquipoDao {
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
        	String consulta = "select id_equipo from Equipo";
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
        	ResultSet rs = pstmt.executeQuery();   
			 while (rs.next()) {
		            int idA = rs.getInt("id_equipo");
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
     * @param iniciales
     */
    public void insertProducto(int codigo, String nombre, String iniciales) {
    	//Inserta objeto en la BBDD
    	try {
            conexion = new ConexionBD();        	
            
			String consulta = "INSERT INTO Equipo(id_equipo, nombre, iniciales) VALUES(?,?,?)";
			
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);  
        	
        	pstmt.setInt(1, codigo);
        	pstmt.setString(2, nombre);
        	pstmt.setString(3, iniciales);
        	
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
    public ObservableList<String> cargarEquiposNombre()  {
    	ObservableList<String> aeropuertos = FXCollections.observableArrayList();
        try {
        	conexion = new ConexionBD();        	
        	String consulta = "select * from Equipo";
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
     * Cargar Equipo
     * @param nombre
     * @return
     */
    public Equipo cargarEquipoConNombre(String nombre)  {
    	Equipo d = null;
        try {
        	conexion = new ConexionBD();        	
        	String consulta = "select * from Equipo where nombre ='"+nombre+"'";
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
        	ResultSet rs = pstmt.executeQuery();   
			 while (rs.next()) {
				 int id = rs.getInt("id_equipo");
				 String iniciales = rs.getString("iniciales");
				 
				 d = new Equipo(id, nombre, iniciales);
			 }
			 rs.close();       
	         conexion.CloseConexion();
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    }    
        return d;    
    }

    /**
     * Modificar Producto
     * @param codigo
     * @param nombre
     * @param iniciales
     */
	public void modProducto(int codigo, String nombre, String iniciales) {
    	//Modifica objeto en la BBDD
    	try {
	        conexion = new ConexionBD();        	
        	String consulta = "UPDATE olimpiadas.Equipo SET nombre = ?, iniciales = ? WHERE id_equipo = ?";
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);  
        	pstmt.setString(1, nombre);
        	pstmt.setString(2, iniciales);
        	pstmt.setInt(3, codigo);
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
            String consulta = "DELETE FROM olimpiadas.Equipo WHERE id_equipo = "+codigo;
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
	public ObservableList<Equipo> cargarEquipos()  {
    	
    	ObservableList<Equipo> producto = FXCollections.observableArrayList();
        try {
            conexion = new ConexionBD();        	
        	String consulta = "select * from Equipo";
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
        	ResultSet rs = pstmt.executeQuery();   
			 while (rs.next()) {
				 int id = rs.getInt("id_equipo");
	             String nombre = rs.getString("nombre");
	             String iniciales = rs.getString("iniciales");
	         
                 Equipo a = new Equipo(id, nombre, iniciales);
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
