package model.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public abstract class PlanMantenimiento implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8624311545758941518L;
	protected Float kilometraje;
	protected Date fechaFabricacion;
	protected List<Tarea> tareas;
	
	private EstrategiaMantenimiento estrategiaMantenimiento;
	
	public List<Tarea> getTareas() {
		return tareas;
	}
	public void setTareas(List<Tarea> tareas) {
		this.tareas = tareas;
	}
	public EstrategiaMantenimiento getEstrategiaMantenimiento() {
		return estrategiaMantenimiento;
	}
	public void setEstrategiaMantenimiento(EstrategiaMantenimiento estrategiaMantenimiento) {
		this.estrategiaMantenimiento = estrategiaMantenimiento;
	}
	public Float getKilometraje() {
		return kilometraje;
	}
	public void setKilometraje(Float kilometraje) {
		this.kilometraje = kilometraje;
	}
	public Date getFechaFabricacion() {
		return fechaFabricacion;
	}
	public void setFechaFabricacion(Date fechaFabricacion) {
		this.fechaFabricacion = fechaFabricacion;
	}
	
	public void realizarMantenimiento() {
		tareas.add(estrategiaMantenimiento.realizarMantenimiento(this));
	}
	
	public abstract float calcularRetraso();
	
}
