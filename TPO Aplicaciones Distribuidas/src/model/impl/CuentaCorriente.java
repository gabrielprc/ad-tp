package model.impl;

import java.io.Serializable;

public class CuentaCorriente implements Serializable {
	private boolean depositoPrevio;
	private Float montoAutorizado;
	private Float montoActual;
	
	public boolean isDepositoPrevio() {
		return depositoPrevio;
	}
	public void setDepositoPrevio(boolean depositoPrevio) {
		this.depositoPrevio = depositoPrevio;
	}
	public Float getMontoAutorizado() {
		return montoAutorizado;
	}
	public void setMontoAutorizado(Float montoAutorizado) {
		this.montoAutorizado = montoAutorizado;
	}
	public Float getMontoActual() {
		return montoActual;
	}
	public void setMontoActual(Float montoActual) {
		this.montoActual = montoActual;
	}
	
	public boolean estaAutorizado(Float monto) {
		return (montoActual + monto) <= montoAutorizado;
	}
	
	public void actualizarMontoActual(Float monto) throws Exception {
		if (estaAutorizado(monto)) {
			this.montoActual += monto;
		} else {
			throw new Exception("Monto no autorizado");
		}
	}
}
