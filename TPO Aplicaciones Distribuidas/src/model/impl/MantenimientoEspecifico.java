package model.impl;



public class MantenimientoEspecifico extends EstrategiaMantenimiento {

	@Override
	protected void mantener() {
		System.out.println("Se hizo un mantenimiento especifico.");		
	}

}
