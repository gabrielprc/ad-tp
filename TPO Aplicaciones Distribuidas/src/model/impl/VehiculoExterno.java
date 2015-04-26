package model.impl;


public class VehiculoExterno extends Vehiculo {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6523653868930827919L;
	
	private Proveedor proveedor;

	public VehiculoExterno(String patente, Tamano tamano, Float peso, Float tara,
			Float tarifa, TipoVehiculo tipo, Proveedor proveedor) {
		this.patente = patente;
		this.tamano = tamano;
		this.peso = peso;
		this.tara = tara;
		this.tarifa = tarifa;
		this.proveedor = proveedor;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	
}
