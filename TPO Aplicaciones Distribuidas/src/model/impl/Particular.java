package model.impl;

import java.util.List;

public class Particular extends Cliente {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3516360528659218982L;

	private List<Receptor> receptores;
	private String dni;
	private String apellido;

	public Particular(String codigoUnico, String dni, String nombre,
			String apellido) {

		super(codigoUnico, nombre);
		this.dni = dni;
		this.apellido = apellido;

	}

	public List<Receptor> getReceptores() {
		return receptores;
	}

	public void setReceptores(List<Receptor> receptores) {
		this.receptores = receptores;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	@Override
	public void cobrarEnvio(Factura factura) {
		// TODO Auto-generated method stub
		
	}

}
