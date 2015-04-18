package model.impl;

public class VehiculoExterno extends Vehiculo {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6523653868930827919L;
	
	private Proveedor proveedor;

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	
}
