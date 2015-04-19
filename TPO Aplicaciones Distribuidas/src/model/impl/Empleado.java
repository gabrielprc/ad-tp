package model.impl;

import java.util.Date;

public class Empleado extends Persona {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7999444890126630022L;

	private String cuil;
	private TipoPuesto puesto;
	private Date fechaNacimiento;

	public String getCuil() {
		return cuil;
	}

	public void setCuil(String cuil) {
		this.cuil = cuil;
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
