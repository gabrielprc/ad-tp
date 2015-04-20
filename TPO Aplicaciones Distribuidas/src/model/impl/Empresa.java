package model.impl;

import java.util.Collection;

public class Empresa extends Cliente {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3035063526042004506L;

	private boolean regular;
	private CuentaCorriente cuentaCorriente;
	private Collection<Producto> productos;

	public Empresa(String codigoUnico, String nombre) {
		super(codigoUnico, nombre);
	}

	public boolean isRegular() {
		return regular;
	}

	public void setRegular(boolean regular) {
		this.regular = regular;
	}

	public CuentaCorriente getCuentaCorriente() {
		return cuentaCorriente;
	}

	public void setCuentaCorriente(CuentaCorriente cuentaCorriente) {
		this.cuentaCorriente = cuentaCorriente;
	}

	public Collection<Producto> getProductos() {
		return productos;
	}

	public void setProductos(Collection<Producto> productos) {
		this.productos = productos;
	}

	@Override
	public void cobrarEnvio(Factura factura) {
		// TODO Auto-generated method stub

	}

}
