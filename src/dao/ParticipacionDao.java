package dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.ConexionBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Participacion;


public class ParticipacionDao {
    private ConexionBD conexion;
    
    /**
     * Insertar producto
     * @param deportista
     * @param evento
     * @param equipo
     * @param edad
     * @param medalla
     */
    public void insertProducto(String deportista, String evento, String equipo, int edad, String medalla) {
    	//Inserta objeto en la BBDD
    	try {
            conexion = new ConexionBD();        	
            
             String consulta = "select * from Deportista where nombre = '"+deportista+"'";
             PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
             ResultSet rs = pstmt.executeQuery();   
			 rs.next();
	         int id_deportista = rs.getInt("id_deportista");
	         
	         consulta = "select * from Evento where nombre = '"+evento+"'";
        	 pstmt = conexion.getConexion().prepareStatement(consulta);      
        	 rs = pstmt.executeQuery();   
			 rs.next();
	         int id_evento = rs.getInt("id_evento");
	         
	         consulta = "select * from Equipo where nombre = '"+equipo+"'";
        	 pstmt = conexion.getConexion().prepareStatement(consulta);      
        	 rs = pstmt.executeQuery();   
			 rs.next();
	         int id_equipo = rs.getInt("id_equipo");
            
			consulta = "INSERT INTO Participacion(id_deportista, id_evento, id_equipo, edad, medalla) VALUES(?,?,?,?,?)";
			
        	pstmt = conexion.getConexion().prepareStatement(consulta);  
        	
        	pstmt.setInt(1, id_deportista);
        	pstmt.setInt(2, id_evento);
        	pstmt.setInt(3, id_equipo);
        	pstmt.setInt(4, edad);
        	pstmt.setString(5, medalla);
        	
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
	public ObservableList<Participacion> cargarParticipacions()  {
    	
    	ObservableList<Participacion> producto = FXCollections.observableArrayList();
        try {
            conexion = new ConexionBD();        	
        	String consulta = "select * from Participacion";
        	PreparedStatement pstmt = conexion.getConexion().prepareStatement(consulta);      
        	ResultSet rs = pstmt.executeQuery();   
			 while (rs.next()) {
				 int id_deportista = rs.getInt("id_deportista");
	             int id_evento = rs.getInt("id_evento");
	             int id_equipo = rs.getInt("id_equipo");
	             int edad = rs.getInt("edad");
	             String medalla = rs.getString("medalla");
	             
	             consulta = "select * from Deportista where id_deportista = "+id_deportista;
	         	 pstmt = conexion.getConexion().prepareStatement(consulta);      
	         	 rs = pstmt.executeQuery();   
	 			 rs.next();
	 	         String deportista = rs.getString("nombre");
	 	         
	 	         consulta = "select * from Evento where id_evento = "+id_evento;
	         	 pstmt = conexion.getConexion().prepareStatement(consulta);      
	         	 rs = pstmt.executeQuery();   
	 			 rs.next();
	 	         String evento = rs.getString("nombre");
	 	         
	 	         consulta = "select * from Equipo where id_equipo = "+id_equipo;
	         	 pstmt = conexion.getConexion().prepareStatement(consulta);      
	         	 rs = pstmt.executeQuery();   
	 			 rs.next();
	 	         String equipo = rs.getString("nombre");
	         
                 Participacion a = new Participacion(deportista, evento, equipo, edad, medalla);
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
