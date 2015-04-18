package model.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Viaje implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5092108929260301459L;
	
	private Integer codigo;
	private List<Envio> envios;
	private Seguro seguro;
	private Vehiculo vehiculo;
	private Date fechaSalida;
	private Date fechaLlegada;
	private List<CondicionEspecial> condicionesEspeciales;
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public List<Envio> getEnvios() {
		return envios;
	}
	public void setEnvios(List<Envio> envios) {
		this.envios = envios;
	}
	public Seguro getSeguro() {
		return seguro;
	}
	public void setSeguro(Seguro seguro) {
		this.seguro = seguro;
	}
	public Vehiculo getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
	public Date getFechaSalida() {
		return fechaSalida;
	}
	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	public Date getFechaLlegada() {
		return fechaLlegada;
	}
	public void setFechaLlegada(Date fechaLlegada) {
		this.fechaLlegada = fechaLlegada;
	}
	public List<CondicionEspecial> getCondicionesEspeciales() {
		return condicionesEspeciales;
	}
	public void setCondicionesEspeciales(
			List<CondicionEspecial> condicionesEspeciales) {
		this.condicionesEspeciales = condicionesEspeciales;
	}
}
