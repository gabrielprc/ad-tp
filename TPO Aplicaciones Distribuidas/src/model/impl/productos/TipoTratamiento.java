package model.impl.productos;

public enum TipoTratamiento {
	EXTREMADAMENTE_PELIGROSO("Extremadamente peligroso"),
	PELIGROSO("Peligroso"),
	INOCUO("Inocuo");
	
	private String tipo;
	
	private TipoTratamiento(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}	
}