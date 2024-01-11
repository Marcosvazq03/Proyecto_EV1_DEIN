package model;

import java.util.Objects;


//Clase Deporte
public class Deporte {
	
	private String nombre;
	private int id;
	
	public Deporte(int id,String nombre) {
		this.id=id;
		this.nombre=nombre;
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

	@Override
	public int hashCode() {
		return Objects.hash(id, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Deporte other = (Deporte) obj;
		return id == other.id && Objects.equals(nombre, other.nombre);
	}
	
}
