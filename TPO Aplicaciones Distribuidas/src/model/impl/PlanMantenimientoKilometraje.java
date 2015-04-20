package model.impl;

public class PlanMantenimientoKilometraje extends PlanMantenimiento {

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
