package model.impl.vehiculos;

import java.io.Serializable;

import model.impl.misc.Tamano;

public abstract class Vehiculo implements Serializable {
	/**
	 * 
	 */
	protected static final long serialVersionUID = 8056700348323209606L;
	
	protected String patente;
	protected Tamano tamano;
	protected Float peso;
	protected Float tara;
	protected Float tarifa;
	protected TipoVehiculo tipo;
	
	public TipoVehiculo getTipo() {
		return tipo;
	}
	public void setTipo(TipoVehiculo tipo) {
		this.tipo = tipo;
	}
	public String getPatente() {
		return patente;
	}
	public void setPatente(String patente) {
		this.patente = patente;
	}
	public Tamano getTamano() {
		return tamano;
	}
	public void setTamano(Tamano tamano) {
		this.tamano = tamano;
	}
	public Float getPeso() {
		return peso;
	}
	public void setPeso(Float peso) {
		this.peso = peso;
	}
	public Float getTara() {
		return tara;
	}
	public void setTara(Float tara) {
		this.tara = tara;
	}
	public Float getTarifa() {
		return tarifa;
	}
	public void setTarifa(Float tarifa) {
		this.tarifa = tarifa;
	}
	
	public abstract boolean estaDisponible();
	
	@Override
	public boolean equals(Object v) {
		if (v instanceof Vehiculo) {
			if (this.patente.equals(((Vehiculo) v).getPatente())) {
				return true;
			}
		}
		return false;
	}
}
