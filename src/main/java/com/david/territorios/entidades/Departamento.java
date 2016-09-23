package com.david.territorios.entidades;

public class Departamento {
	private int id_departamento;
	private String nombre;
	private int id_pais;
	private Pais pais;
	
	private int cantidad_ciudades;
	
	public int getId_departamento() {
		return id_departamento;
	}
	
	public void setId_departamento(int id_departamento) {
		this.id_departamento = id_departamento;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getId_pais() {
		return id_pais;
	}
	
	public void setId_pais(int id_pais) {
		this.id_pais = id_pais;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public int getCantidad_ciudades() {
		return cantidad_ciudades;
	}

	public void setCantidad_ciudades(int cantidad_ciudades) {
		this.cantidad_ciudades = cantidad_ciudades;
	}
}
