package model.impl;

import java.io.Serializable;
import java.util.List;

public class PlanMantenimiento implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8624311545758941518L;
	
	private List<Tarea> tareas;
	private Float kilometrajeActual;
	private Float puntoControl;
	
	public List<Tarea> getTareas() {
		return tareas;
	}
	public void setTareas(List<Tarea> tareas) {
		this.tareas = tareas;
	}
	public Float getKilometrajeActual() {
		return kilometrajeActual;
	}
	public void setKilometrajeActual(Float kilometrajeActual) {
		this.kilometrajeActual = kilometrajeActual;
	}
	public Float getPuntoControl() {
		return puntoControl;
	}
	public void setPuntoControl(Float puntoControl) {
		this.puntoControl = puntoControl;
	}
	
}
