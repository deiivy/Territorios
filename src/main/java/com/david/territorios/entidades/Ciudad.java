package com.david.territorios.entidades;

public class Ciudad {
	private int id_ciudad;
	private String nombre;
	private int id_departamento;
	
	private Departamento departamento;
	
	public int getId_ciudad() {
		return id_ciudad;
	}
	
	public void setId_ciudad(int id_ciudad) {
		this.id_ciudad = id_ciudad;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getId_departamento() {
		return id_departamento;
	}
	
	public void setId_departamento(int id_departamento) {
		this.id_departamento = id_departamento;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
}
