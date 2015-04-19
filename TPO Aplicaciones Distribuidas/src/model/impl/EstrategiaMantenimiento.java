package model.impl;

public abstract class EstrategiaMantenimiento {
	public Tarea realizarMantenimiento(PlanMantenimiento plan) {
		mantener();
		return generarTarea(plan);
	}
	
	protected abstract Tarea generarTarea(PlanMantenimiento plan);
	
	protected abstract void mantener();
}
