package model;

import java.util.Objects;


//Clase Olimpiada
public class Olimpiada {
	
	private String nombre, temporada, ciudad;
	private int id, anio;
	
	public Olimpiada(int id,String nombre, int anio,String temporada, String ciudad) {
		this.id=id;
		this.nombre=nombre;
		this.temporada=temporada;
		this.ciudad=ciudad;
		this.anio=anio;
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

	public String getTemporada() {
		return temporada;
	}

	public void setTemporada(String temporada) {
		this.temporada = temporada;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	@Override
	public int hashCode() {
		return Objects.hash(anio, ciudad, id, nombre, temporada);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Olimpiada other = (Olimpiada) obj;
		return anio == other.anio && Objects.equals(ciudad, other.ciudad) && id == other.id
				&& Objects.equals(nombre, other.nombre) && Objects.equals(temporada, other.temporada);
	}
	
}
