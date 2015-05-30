package model.impl.clientes;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import model.impl.productos.Producto;

@Entity
@Table(name = "Empresas")
public class Empresa extends Cliente {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3035063526042004506L;

	@Column(name = "regular")
	private boolean regular;
	@OneToOne
	@PrimaryKeyJoinColumn
	private CuentaCorriente cuentaCorriente;
	@ManyToMany
	@JoinTable(name = "EmpresasProductos", joinColumns = @JoinColumn(name = "idEmpresa"), inverseJoinColumns = @JoinColumn(name = "idProducto"))
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
