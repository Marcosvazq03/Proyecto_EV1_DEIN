package model;

import java.io.InputStream;
import java.util.Objects;


//Clase Deportista
public class Deportista {
	
	private String nombre, sexo, evento, equipo, medalla;
	private int id, peso, altura, edad;
	private InputStream image;
	
	public Deportista(int id,String nombre, String sexo, int peso, int altura, InputStream img) {
		this.id=id;
		this.nombre=nombre;
		this.sexo=sexo;
		this.peso=peso;
		this.altura=altura;
		this.image=img;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public InputStream getImage() {
		return image;
	}

	public void setImage(InputStream image) {
		this.image = image;
	}

	@Override
	public int hashCode() {
		return Objects.hash(altura, edad, equipo, evento, id, image, medalla, nombre, peso, sexo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Deportista other = (Deportista) obj;
		return altura == other.altura && edad == other.edad && Objects.equals(equipo, other.equipo)
				&& Objects.equals(evento, other.evento) && id == other.id && Objects.equals(image, other.image)
				&& Objects.equals(medalla, other.medalla) && Objects.equals(nombre, other.nombre) && peso == other.peso
				&& Objects.equals(sexo, other.sexo);
	}

	

	
	
}
