package dao;


import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.ConexionBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Deportista;


public class DeportistaDao {
    private ConexionBD conexion;
    
    public int ultimoIDAer() {
    	//Saca el ultimo id
    	int id=-1;
    	try {
            conexion = new ConexionBD();        	
        	String consulta = "select id from Deportista";
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
        	ResultSet rs = pstmt.executeQuery();   
			 while (rs.next()) {
		            int idA = rs.getInt("id_deportista");
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
    
    public void insertProducto(int codigo, String nombre, String sexo, int altura, int peso, InputStream imagen) {
    	//Inserta objeto en la BBDD
    	try {
            conexion = new ConexionBD();        	
            
			String consulta = "INSERT INTO Deportista(id_deportista, nombre, sexo, altura, peso, imagen) VALUES(?,?,?,?,?,?)";
			
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);  
        	
        	pstmt.setInt(1, codigo);
        	pstmt.setString(2, nombre);
        	pstmt.setString(3, sexo);
        	pstmt.setInt(4, altura);
        	pstmt.setInt(5, peso);
        	pstmt.setBinaryStream(6, imagen);
        	
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
            String consulta = "DELETE FROM olimpiadas.Deportista WHERE id_deportista = "+codigo;
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);  
			pstmt.execute();
	        conexion.CloseConexion();
	    } catch (SQLException e) {	    	
	    	System.out.println(e.getMessage());
	    }
    }

	public InputStream cargarImg(int codigo)  {
		InputStream image = null;
		try {
            conexion = new ConexionBD();        	
        	String consulta = "select imagen from Deportista WHERE id_deportista = "+codigo;
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
        	ResultSet rs = pstmt.executeQuery();   
        	rs.next();
        	image = rs.getBinaryStream("foto");          
			rs.close();       
	        conexion.CloseConexion();
	    } catch (SQLException e) {	    	
	    	e.printStackTrace();
	    }    
        return image;    
    }
	
	/**
	 * Cargar Productos
	 * @return
	 */
	public ObservableList<Deportista> cargarDeportistas()  {
    	
    	ObservableList<Deportista> producto = FXCollections.observableArrayList();
        try {
            conexion = new ConexionBD();        	
        	String consulta = "select * from Deportista";
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
        	ResultSet rs = pstmt.executeQuery();   
			 while (rs.next()) {
				 int id = rs.getInt("id_deportista");
	             String nombre = rs.getString("nombre");
	             String sexo = rs.getString("sexo");
	             int peso = rs.getInt("peso");
	             int altura = rs.getInt("altura");
	             InputStream image = rs.getBinaryStream("foto");
	         
                 Deportista a = new Deportista(id, nombre, sexo, peso, altura, image);
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
