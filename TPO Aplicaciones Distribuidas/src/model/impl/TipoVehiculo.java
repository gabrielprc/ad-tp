package model.impl;

public enum TipoVehiculo {
	TRACTOR("Tractor"),
	CAMION_CON_CAJA_REFRIGERADO("Camión con caja refrigerado"),
	CAMION_CON_CAJA("Camión con caja"),
	CAMION_CON_TANQUE("Camión con tanque"),
	CAMIONETA("Camioneta");
	
	private String tipo;
	
	private TipoVehiculo(String tipo) {
		this.tipo = tipo;
	}
}
