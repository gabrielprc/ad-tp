package model.impl.clientes;

import java.util.Date;

import model.impl.PersistentObject;

import javax.persistence.*;

@Entity
@Table (name="Items_Factura")
public class ItemFactura extends PersistentObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 259038040842891437L;
	
	@Column (name="monto")
	private Float monto;
	@Column (name="fecha_vencimiento")
	private Date fechaVecimiento;
	@Column (name="pagado")
	private boolean pagado;

	public ItemFactura() {

	}

	public Float getMonto() {
		return monto;
	}

	public void setMonto(Float monto) {
		this.monto = monto;
	}

	public Date getFechaVecimiento() {
		return fechaVecimiento;
	}

	public void setFechaVecimiento(Date fechaVecimiento) {
		this.fechaVecimiento = fechaVecimiento;
	}

	public boolean isPagado() {
		return pagado;
	}

	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}

}
