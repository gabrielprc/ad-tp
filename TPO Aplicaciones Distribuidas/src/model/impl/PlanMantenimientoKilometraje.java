package model.impl;

public class PlanMantenimientoKilometraje extends PlanMantenimiento {

	private Float puntoControl;
	
	@Override
	public float calcularRetraso() {
		return kilometraje / puntoControl;
	}

	public Float getPuntoControl() {
		return puntoControl;
	}

	public void setPuntoControl(Float puntoControl) {
		this.puntoControl = puntoControl;
	}

}
