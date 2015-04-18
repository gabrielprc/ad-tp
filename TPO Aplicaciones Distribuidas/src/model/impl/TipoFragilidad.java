package model.impl;

public enum TipoFragilidad {
	EXTREMADAMENTE_FRAGIL("Extremadamente fragil"),
	FRAGIL("Fragil"),
	NORMAL("Normal"),
	RESISTENTE("Resistente");
	
	private String tipo;
	
	private TipoFragilidad(String tipo) {
		this.tipo = tipo;
	}
}