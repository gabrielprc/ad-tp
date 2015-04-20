package model.impl;

import java.util.Date;

public class PlanMantenimientoTemporal extends PlanMantenimiento {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8994537799759906446L;
	
	private int intervaloMantenimiento;
	
	public PlanMantenimientoTemporal(int intervaloMantenimiento, EstrategiaMantenimiento estrategia) {
		super(estrategia);
		this.intervaloMantenimiento = intervaloMantenimiento;
	}
	
	@Override
	public float calcularRetraso() {
		Date hoy = new Date();
		Date ultimoMantenimiento = fechaFabricacion;
		
		if (!tareas.isEmpty()) {
			ultimoMantenimiento = tareas.get(tareas.size() - 1).getFechaDevolucion();
		}

		long diferenciaMilisegundos = hoy.getTime() - ultimoMantenimiento.getTime();

		float diferenciaDias = (float) diferenciaMilisegundos / (24 * 60 * 60 * 1000);
		
		return diferenciaDias / intervaloMantenimiento;
	}

	public int getIntervaloMantenimiento() {
		return intervaloMantenimiento;
	}

	public void setIntervaloMantenimiento(int intervaloMantenimiento) {
		this.intervaloMantenimiento = intervaloMantenimiento;
	}

}
