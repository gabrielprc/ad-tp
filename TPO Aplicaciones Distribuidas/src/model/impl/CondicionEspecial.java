package model.impl;

public enum CondicionEspecial {
	Temperatura("Temperatura"),
	Seguridad("Seguridad"),
	MonitoreoSatelital("Monitoreo satelital");
	
	private String condicion;
	
	private CondicionEspecial(String condicion) {
		this.condicion = condicion;
	}
}
