package model.impl.clientes;

import java.util.Date;

import model.impl.PersistentObject;

public class CobroParcial extends PersistentObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1811141989154565682L;
	
	private Date fecha;
	private Float monto;
	private boolean pagado;
	
	public CobroParcial(Date fecha, Float monto){
		
		this.fecha = fecha;
		this.monto = monto;
	}
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Float getMonto() {
		return monto;
	}
	public void setMonto(Float monto) {
		this.monto = monto;
	}

	public boolean isPagado() {
		return pagado;
	}

	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}
	
}
