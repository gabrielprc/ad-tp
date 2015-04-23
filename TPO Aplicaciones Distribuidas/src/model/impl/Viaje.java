package model.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class Viaje implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5092108929260301459L;

	private Integer codigo;
	private List<Carga> envios;
	private Seguro seguro;
	private Vehiculo vehiculo;
	private Date fechaSalida;
	private Date fechaLlegada;
	private List<CondicionEspecial> condicionesEspeciales;
	private boolean estaAtrasado;
	private Vector<ParadaIntermedia> paradasIntermedias;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public List<Carga> getEnvios() {
		return envios;
	}

	public void setEnvios(List<Carga> envios) {
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

	public void agregarCarga(Carga carga) {

		float volumenActual = vehiculo.getTamano().calcularVolumen()
				- calcularVolumenCargas();
		float pesoActual = vehiculo.getPeso() - calcularPesoCargas();

		if (carga.calcularPesoTotal() <= pesoActual
				&& carga.calcularVolumenTotal() <= volumenActual)
			envios.add(carga);

	}

	public boolean isEstaAtrasado() {
		return estaAtrasado;
	}

	public void setEstaAtrasado(boolean estaAtrasado) {
		this.estaAtrasado = estaAtrasado;
	}

	public Vector<ParadaIntermedia> getParadasIntermedias() {
		return paradasIntermedias;
	}

	public void setParadasIntermedias(
			Vector<ParadaIntermedia> paradasIntermedias) {
		this.paradasIntermedias = paradasIntermedias;
	}

	public float calcularVolumenCargas() {

		float volumen = 0;
		for (Carga c : envios)
			volumen += c.calcularVolumenTotal();
		return volumen;
	}

	public float calcularPesoCargas() {

		float peso = 0;
		for (Carga c : envios)
			peso += c.calcularPesoTotal();
		return peso;
	}

}
