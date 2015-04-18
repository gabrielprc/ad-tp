package model.impl;

import java.util.Date;

public class Empleado extends Persona {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7999444890126630022L;
	
	private TipoPuesto puesto;
	private Date fechaNacimiento;
	
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
