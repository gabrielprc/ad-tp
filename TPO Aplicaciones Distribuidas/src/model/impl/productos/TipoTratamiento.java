package model.impl.productos;

public enum TipoTratamiento {
	EXTREMADAMENTE_PELIGROSO("Extremadamente peligroso", 0.05f),
	PELIGROSO("Peligroso", 0.025f),
	INOCUO("Inocuo", 0.0f);
	
	private String tipo;
	private Float factorTratamiento; 
	
	private TipoTratamiento(String tipo, float factorTratamiento) {
		this.tipo = tipo;
		this.factorTratamiento = factorTratamiento;
	}

	public String getTipo() {
		return tipo;
	}

	public Float getFactorTratamiento() {
		return factorTratamiento;
	}
	
}