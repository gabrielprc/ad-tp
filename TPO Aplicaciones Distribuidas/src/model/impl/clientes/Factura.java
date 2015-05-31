package model.impl.clientes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import model.impl.PersistentObject;
import model.impl.cargas.Carga;

@Entity
@Table(name = "Facturas")
@AttributeOverride (name = "id", column = @Column(name ="id_factura"))
public class Factura extends PersistentObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6357766629462615184L;
	
	@Column(name = "monto")
	private Float monto;
	@ManyToOne
	@JoinColumn(name = "id_Carga")
	private Carga carga;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn (name="id_factura")
	private List<ItemFactura> itemsFactura;
	
	public Factura(Float monto, Carga carga){
		
		this.monto = monto;
		this.carga = carga;
	}
	
	public Factura() {
		
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
	
	public void agregarItemFactura(float monto, Date fechaVencimiento) {
		if (itemsFactura == null)
			itemsFactura = new ArrayList<ItemFactura>();
		itemsFactura.add(new ItemFactura(monto, fechaVencimiento));
	}
	
	public void pagarItemFactura(Integer id) {
		for (ItemFactura ifac : itemsFactura) {
			if (ifac.getId().equals(id)) {
				ifac.setPagado(true);
				break;
			}
		}
	}
}
