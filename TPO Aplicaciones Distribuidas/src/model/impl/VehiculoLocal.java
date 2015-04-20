package model.impl;

import java.util.Date;


public class VehiculoLocal extends Vehiculo {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8225795586920003275L;
	
	private PlanMantenimiento planMantenimiento;
	private Date vencimientoGarantia;
	
	public PlanMantenimiento getPlanMantenimiento() {
		return planMantenimiento;
	}
	public void setPlanMantenimiento(PlanMantenimiento planMantenimiento) {
		this.planMantenimiento = planMantenimiento;
	}
	public Date getVencimientoGarantia() {
		return vencimientoGarantia;
	}
	public void setVencimientoGarantia(Date vencimientoGarantia) {
		this.vencimientoGarantia = vencimientoGarantia;
	}
	
	public boolean estaDisponible() {
		Date ahora = new Date();
		
		for (Viaje viaje : viajes) {
			if (viaje.getFechaLlegada().after(ahora) && viaje.getFechaSalida().before(ahora)) {
				return false;
			}
		}
		
		for (Tarea tarea : planMantenimiento.getTareas()) {
			if (tarea.getFechaDevolucion().after(ahora) && tarea.getFechaEntrega().before(ahora)) {
				return false;
			}
		}
		
		return true;
	}
	
	public void realizarMantenimiento(EstrategiaMantenimiento estrategiaMantenimiento) {
		planMantenimiento.realizarMantenimiento(estrategiaMantenimiento);
	}
	
}
