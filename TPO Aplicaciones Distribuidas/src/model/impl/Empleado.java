package model.impl;

import java.util.Date;

public class Empleado {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7999444890126630022L;

	private String cuit;
	private String dni;
	private String nombre;
	private String apellido;
	private TipoPuesto puesto;
	private Date fechaNacimiento;

	public Empleado(String cuit, String dni, String nombre, String apellido,
			Date fechaNacimiento) {

		this.cuit = cuit;
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
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
