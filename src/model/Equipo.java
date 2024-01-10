package model;

import java.util.Objects;


//Clase Equipo
public class Equipo {
	
	private String nombre, iniciales;
	private int id;
	
	public Equipo(int id,String nombre,String iniciales) {
		this.id=id;
		this.nombre=nombre;
		this.iniciales=iniciales;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIniciales() {
		return iniciales;
	}

	public void setIniciales(String iniciales) {
		this.iniciales = iniciales;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, iniciales, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equipo other = (Equipo) obj;
		return id == other.id && Objects.equals(iniciales, other.iniciales) && Objects.equals(nombre, other.nombre);
	}
	
}
