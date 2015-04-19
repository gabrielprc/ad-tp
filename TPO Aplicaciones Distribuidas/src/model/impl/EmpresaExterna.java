package model.impl;

import java.util.List;

public class EmpresaExterna extends Empresa {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5525574833721644593L;
	private List<Producto> productos;

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	
}
