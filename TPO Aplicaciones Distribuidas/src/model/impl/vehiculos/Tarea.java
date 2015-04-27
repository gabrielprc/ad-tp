package model.impl.vehiculos;

import java.io.Serializable;
import java.util.Date;

public class Tarea implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1988465934755698719L;
	
	private Float kilometraje;
	private Date fechaEntrega;
	private Date fechaDevolucion;
	
	public Tarea(Float kilometraje, Date fechaEntrega, Date fechaDevolucion) {
		this.kilometraje = kilometraje;
		this.fechaEntrega = fechaEntrega;
		this.fechaDevolucion = fechaDevolucion;
	}
	
	public Date getFechaEntrega() {
		return fechaEntrega;
	}
	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	public Date getFechaDevolucion() {
		return fechaDevolucion;
	}
	public void setFechaDevolucion(Date fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}
	public Float getKilometraje() {
		return kilometraje;
	}
	public void setKilometraje(Float kilometraje) {
		this.kilometraje = kilometraje;
	}
	
}
