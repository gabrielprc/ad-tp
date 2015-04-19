package model.impl;

import java.io.Serializable;
import java.util.List;

public abstract class Vehiculo implements Serializable {
	/**
	 * 
	 */
	protected static final long serialVersionUID = 8056700348323209606L;
	
	protected String patente;
	protected List<Viaje> viajes;
	protected Tamano tamano;
	protected Float peso;
	protected Float tara;
	protected Float tarifa;
	
	public String getPatente() {
		return patente;
	}
	public void setPatente(String patente) {
		this.patente = patente;
	}
	public List<Viaje> getViajes() {
		return viajes;
	}
	public void setViajes(List<Viaje> viajes) {
		this.viajes = viajes;
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
}
