package model.impl;

import java.util.Calendar;
import java.util.Date;


public class MantenimientoEspecifico extends EstrategiaMantenimiento {

	@Override
	protected void mantener() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Tarea generarTarea(PlanMantenimiento plan) {
		Calendar cal = Calendar.getInstance();
		Date entrega = cal.getTime();
		
		cal.add(Calendar.DATE, 1);
		Date devolucion = cal.getTime();
		
		return new Tarea(plan.kilometraje, entrega, devolucion);
	}

}
