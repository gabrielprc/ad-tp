package model.impl;

import java.util.Date;

import model.interfaces.EstrategiaMantenimiento;


public class VehiculoLocal extends Vehiculo {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8225795586920003275L;
	
	private PlanMantenimiento planMantenimiento;
	private EstrategiaMantenimiento estrategiaMantenimiento;
	private Date vencimientoGarantia;
	
	public PlanMantenimiento getPlanMantenimiento() {
		return planMantenimiento;
	}
	public void setPlanMantenimiento(PlanMantenimiento planMantenimiento) {
		this.planMantenimiento = planMantenimiento;
	}
	public EstrategiaMantenimiento getEstrategiaMantenimiento() {
		return estrategiaMantenimiento;
	}
	public void setEstrategiaMantenimiento(
			EstrategiaMantenimiento estrategiaMantenimiento) {
		this.estrategiaMantenimiento = estrategiaMantenimiento;
	}
	public Date getVencimientoGarantia() {
		return vencimientoGarantia;
	}
	public void setVencimientoGarantia(Date vencimientoGarantia) {
		this.vencimientoGarantia = vencimientoGarantia;
	}
	
}
