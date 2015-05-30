package model.impl.clientes;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;

import model.impl.PersistentObject;

@Entity
@Table(name = "Clientes")
@AttributeOverride (name = "id", column = @Column(name ="id_cuenta_corriente"))
public class CuentaCorriente extends PersistentObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8724917442960945342L;

	@Column(name = "depositoPrevio")
	private boolean depositoPrevio;
	@Column(name = "montoAutorizado")
	private Float montoAutorizado;
	@Column(name = "montoActual")
	private Float montoActual;

	public CuentaCorriente(Float montoActual, Float montoAutorizado) {

		this.montoActual = montoActual;
		this.montoAutorizado = montoAutorizado;
	}

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
