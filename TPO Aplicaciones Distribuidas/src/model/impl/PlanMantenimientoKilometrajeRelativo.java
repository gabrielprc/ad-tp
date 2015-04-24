package model.impl;

public class PlanMantenimientoKilometrajeRelativo extends PlanMantenimiento {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9027189985164803874L;
	private float puntoControl;
	
	public PlanMantenimientoKilometrajeRelativo(float puntoControl, EstrategiaMantenimiento estrategia) {
		super(estrategia);
		this.puntoControl = puntoControl;
	}
	
	@Override
	public float calcularRetraso() {
		if (tareas.isEmpty()) {
			return 0;
		}
		return tareas.get(tareas.size() - 1).getKilometraje() / puntoControl;
	}

	public float getPuntoControl() {
		return puntoControl;
	}
	public void setPuntoControl(float puntoControl) {
		this.puntoControl = puntoControl;
	}
	
}
