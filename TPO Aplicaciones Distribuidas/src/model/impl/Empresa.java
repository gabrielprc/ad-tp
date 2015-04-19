package model.impl;


public class Empresa extends Cliente {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3035063526042004506L;
	
	private boolean regular;
	private String nombre;
	private CuentaCorriente cuentaCorriente;
	
	public boolean isRegular() {
		return regular;
	}
	public void setRegular(boolean regular) {
		this.regular = regular;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public CuentaCorriente getCuentaCorriente() {
		return cuentaCorriente;
	}
	public void setCuentaCorriente(CuentaCorriente cuentaCorriente) {
		this.cuentaCorriente = cuentaCorriente;
	}
	
}
