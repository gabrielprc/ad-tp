package model.impl.clientes;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import model.impl.PersistentObject;
import model.impl.viajes.Proveedor;

@Entity
@Table(name = "Pagos")
public class Pago extends PersistentObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6122982765032430365L;
	
	@ManyToOne
	@JoinColumn(name = "proveedor")
	private Proveedor proveedor;
	@Column(name = "monto")
	private Float monto;
	@Column(name = "fecha")
	private Date fecha;
	@Column(name = "estado")
	private boolean estado;
	
	public Pago(Proveedor proveedor, Float monto, Date fecha){
		
		this.proveedor = proveedor;
		this.monto = monto;
		this.fecha = fecha;
	}
	
	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	public Float getMonto() {
		return monto;
	}
	public void setMonto(Float monto) {
		this.monto = monto;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
}
