package model.impl.vehiculos;

import model.impl.misc.Tamano;


public class VehiculoExterno extends Vehiculo {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6523653868930827919L;
	
	public VehiculoExterno(String patente, Tamano tamano, Float peso, Float tara,
			Float tarifa, TipoVehiculo tipo) {
		this.patente = patente;
		this.tamano = tamano;
		this.peso = peso;
		this.tara = tara;
		this.tarifa = tarifa;
	}
	
	public boolean estaDisponible(){
		return true;
	}
}
