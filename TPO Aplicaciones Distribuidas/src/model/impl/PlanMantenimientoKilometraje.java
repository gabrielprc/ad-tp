package model.impl;

public class PlanMantenimientoKilometraje extends PlanMantenimiento {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4030564496922700825L;
	private float puntoControl;
	
	public PlanMantenimientoKilometraje(float puntoControl, EstrategiaMantenimiento estrategia) {
		super(estrategia);
		this.puntoControl = puntoControl;
	}
	
	@Override
	public float calcularRetraso() {
		return kilometraje / puntoControl;
	}

	public float getPuntoControl() {
		return puntoControl;
	}

	public void setPuntoControl(float puntoControl) {
		this.puntoControl = puntoControl;
	}

}
