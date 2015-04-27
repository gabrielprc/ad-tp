package model.impl.productos;

public enum TipoFragilidad {
	EXTREMADAMENTE_FRAGIL("Extremadamente fragil"),
	FRAGIL("Fragil"),
	NORMAL("Normal"),
	RESISTENTE("Resistente");
	
	private String tipo;
	
	private TipoFragilidad(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}	
}