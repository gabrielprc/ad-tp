package model.impl;

import java.util.Date;

public class Empleado {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7999444890126630022L;

	private String cuit;
	private TipoPuesto puesto;
	private Date fechaNacimiento;

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public TipoPuesto getPuesto() {
		return puesto;
	}

	public void setPuesto(TipoPuesto puesto) {
		this.puesto = puesto;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

}
