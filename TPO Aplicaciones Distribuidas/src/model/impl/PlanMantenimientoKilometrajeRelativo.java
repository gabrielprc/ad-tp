package model.impl;

public class PlanMantenimientoKilometrajeRelativo extends PlanMantenimiento {

	private Float puntoControl;
	
	@Override
	public float calcularRetraso() {
		if (tareas.isEmpty()) {
			return 0;
		}
		return tareas.get(tareas.size() - 1).getKilometraje() / puntoControl;
	}

}
