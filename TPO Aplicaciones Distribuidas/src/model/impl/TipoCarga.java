package model.impl;

public enum TipoCarga {
	PAQUETE("Paquete"),
	CAJA("Caja"),
	PALLET("Pallet"),
	BOLSA("Bolsa"),
	BIDON("Bidon"),
	TAMBOR("Tambor"),
	BARRIL("Barril"),
	GRANEL("Granel");
	
	private String tipo;
	
	private TipoCarga(String tipo) {
		this.tipo = tipo;
	}
}
