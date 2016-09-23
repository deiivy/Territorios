package com.david.territorios.entidades;

public class Pais {
	private int id_pais;
	private String nombre;
	
	private int cantidad_departamentos;
	
	public int getId_pais() {
		return id_pais;
	}
	
	public void setId_pais(int id_pais) {
		this.id_pais = id_pais;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCantidad_departamentos() {
		return cantidad_departamentos;
	}

	public void setCantidad_departamentos(int cantidad_departamentos) {
		this.cantidad_departamentos = cantidad_departamentos;
	}
}
