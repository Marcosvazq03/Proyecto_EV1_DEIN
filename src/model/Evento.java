package model;

import java.util.Objects;


//Clase Deportista
public class Evento {
	
	private String nombre, olimpiada, temporada, ciudad, deporte;
	private int id, anio;
	
	public Evento(int id, String nombre, String olimpiada, int anio, String temporada, String ciudad, String deporte) {
		this.id=id;
		this.nombre=nombre;
		this.anio=anio;
		this.temporada=temporada;
		this.ciudad=ciudad;
		this.deporte=deporte;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getOlimpiada() {
		return olimpiada;
	}

	public void setOlimpiada(String olimpiada) {
		this.olimpiada = olimpiada;
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

	public String getDeporte() {
		return deporte;
	}

	public void setDeporte(String deporte) {
		this.deporte = deporte;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	@Override
	public int hashCode() {
		return Objects.hash(anio, ciudad, deporte, id, nombre, olimpiada, temporada);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		return anio == other.anio && Objects.equals(ciudad, other.ciudad) && Objects.equals(deporte, other.deporte)
				&& id == other.id && Objects.equals(nombre, other.nombre) && Objects.equals(olimpiada, other.olimpiada)
				&& Objects.equals(temporada, other.temporada);
	}

	
	
}
