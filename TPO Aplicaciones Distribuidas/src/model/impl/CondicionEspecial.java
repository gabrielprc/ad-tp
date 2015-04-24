package model.impl;

public enum CondicionEspecial {
	TEMPERATURA("Temperatura"),
	SEGURIDAD("Seguridad"),
	MONITOREO_SATELITAL("Monitoreo satelital");
	
	private String condicion;
	
	private CondicionEspecial(String condicion) {
		this.condicion = condicion;
	}

	public String getCondicion() {
		return condicion;
	}
}
