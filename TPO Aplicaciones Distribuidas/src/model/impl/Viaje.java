package model.impl;

import java.io.Serializable;
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

	public void agregarCarga(Carga carga) {

		if (carga.calcularPesoTotal() <= calcularPesoDisponible()
				&& carga.calcularVolumenTotal() <= calcularVolumenDisponible())
			envios.add(carga);
	}

	public float calcularPesoDisponible() {

		float peso = 0;
		for (Carga c : envios)
			peso += c.calcularPesoTotal();
		return vehiculo.getPeso() - peso;
	}

	public float calcularVolumenDisponible() {

		float volumen = 0;
		for (Carga c : envios)
			volumen += c.calcularVolumenTotal();
		return vehiculo.getTamano().calcularVolumen() - volumen;
	}

	public int cantidadParadasIntemedias(){
		
		return paradasIntermedias.capacity();
	}

	public void generarRemito(){
		
	}

	public Integer getCodigo() {
		return codigo;
	}

	public List<CondicionEspecial> getCondicionesEspeciales() {
		return condicionesEspeciales;
	}

	public List<Carga> getEnvios() {
		return envios;
	}

	public Date getFechaLlegada() {
		return fechaLlegada;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public Vector<ParadaIntermedia> getParadasIntermedias() {
		return paradasIntermedias;
	}

	public Seguro getSeguro() {
		return seguro;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public boolean isEstaAtrasado() {
		return estaAtrasado;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public void setCondicionesEspeciales(
			List<CondicionEspecial> condicionesEspeciales) {
		this.condicionesEspeciales = condicionesEspeciales;
	}

	public void setEnvios(List<Carga> envios) {
		this.envios = envios;
	}

	public void setEstaAtrasado(boolean estaAtrasado) {
		this.estaAtrasado = estaAtrasado;
	}

	public void setFechaLlegada(Date fechaLlegada) {
		this.fechaLlegada = fechaLlegada;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public void setParadasIntermedias(
			Vector<ParadaIntermedia> paradasIntermedias) {
		this.paradasIntermedias = paradasIntermedias;
	}
	
	public void setSeguro(Seguro seguro) {
		this.seguro = seguro;
	}
	
	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

}
