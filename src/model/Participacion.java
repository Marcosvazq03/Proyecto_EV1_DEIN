package model;

import java.util.Objects;


//Clase Participacion
public class Participacion {
	
	private String deportista, evento, equipo, medalla;
	private int edad;
	
	public Participacion(String deportista,String evento,String equipo,int edad,String medalla) {
		this.deportista=deportista;
		this.evento=evento;
		this.equipo=equipo;
		this.edad=edad;
		this.medalla=medalla;
	}

	public String getDeportista() {
		return deportista;
	}

	public void setDeportista(String deportista) {
		this.deportista = deportista;
	}

	public String getEvento() {
		return evento;
	}

	public void setEvento(String evento) {
		this.evento = evento;
	}

	public String getEquipo() {
		return equipo;
	}

	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}

	public String getMedalla() {
		return medalla;
	}

	public void setMedalla(String medalla) {
		this.medalla = medalla;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	@Override
	public int hashCode() {
		return Objects.hash(deportista, edad, equipo, evento, medalla);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Participacion other = (Participacion) obj;
		return Objects.equals(deportista, other.deportista) && edad == other.edad
				&& Objects.equals(equipo, other.equipo) && Objects.equals(evento, other.evento)
				&& Objects.equals(medalla, other.medalla);
	}
	
}
