package model.impl.clientes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import model.impl.PersistentObject;
import model.impl.cargas.Carga;

@Entity
@Table(name = "Facturas")
public class Factura extends PersistentObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6357766629462615184L;
	
	@Column(name = "monto")
	private Float monto;
	@ManyToOne
	@JoinColumn(name = "idCarga")
	private Carga carga;
	@OneToMany
	@JoinColumn (name="")
	private List<ItemFactura> itemsFactura;
	
	public Factura(Float monto, Carga carga){
		
		this.monto = monto;
		this.carga = carga;
	}
	
	public Float getMonto() {
		return monto;
	}
	public void setMonto(Float monto) {
		this.monto = monto;
	}
	public Carga getCarga() {
		return carga;
	}
	public void setCarga(Carga carga) {
		this.carga = carga;
	}
}
