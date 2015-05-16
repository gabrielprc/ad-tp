package model.impl.vehiculos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.impl.PersistentObject;

public abstract class PlanMantenimiento extends PersistentObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8624311545758941518L;
	protected Float kilometraje;
	protected Date fechaFabricacion;
	protected List<Tarea> tareas;

	public PlanMantenimiento() {
		this.tareas = new ArrayList<Tarea>();
		this.fechaFabricacion = new Date();
	}

	public List<Tarea> getTareas() {
		return tareas;
	}

	public void setTareas(List<Tarea> tareas) {
		this.tareas = tareas;
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

	public void realizarMantenimiento(
			EstrategiaMantenimiento estrategiaMantenimiento) {
		tareas.add(estrategiaMantenimiento.realizarMantenimiento(this));
	}

	public abstract float calcularRetraso();

}
