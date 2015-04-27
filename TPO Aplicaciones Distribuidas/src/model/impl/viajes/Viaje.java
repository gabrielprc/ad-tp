package model.impl.viajes;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import model.impl.cargas.Carga;
import model.impl.misc.Ubicacion;
import model.impl.productos.CondicionEspecial;
import model.impl.vehiculos.Vehiculo;

public class Viaje implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5092108929260301459L;

	private int codigo;
	private List<Carga> cargas;
	private Seguro seguro;
	private Vehiculo vehiculo;
	private Ubicacion origen;
	private Ubicacion destino;
	private Date fechaSalida;
	private Date fechaLlegada;
	private List<CondicionEspecial> condicionesEspeciales;
	private boolean estaAtrasado;
	private Vector<ParadaIntermedia> paradasIntermedias;

	public Viaje(int codigo, List<Carga> cargas, Seguro seguro, Vehiculo vehiculo, Date fechaSalida,
			List<CondicionEspecial> condicionesEspeciales, Vector<ParadaIntermedia> paradasIntermedias) {
		this.codigo = codigo;
		this.cargas = cargas;
		this.seguro = seguro;
		this.vehiculo = vehiculo;
		this.fechaSalida = fechaSalida;
		this.condicionesEspeciales = condicionesEspeciales;
		this.paradasIntermedias = paradasIntermedias;
		paradasIntermedias = new Vector<ParadaIntermedia>();
	}

	public void agregarCarga(Carga carga) {

		if (carga.calcularPesoTotal() <= calcularPesoDisponible()
				&& carga.calcularVolumenTotal() <= calcularVolumenDisponible())
			cargas.add(carga);
	}

	public float calcularPesoDisponible() {

		float peso = 0;
		for (Carga c : cargas)
			peso += c.calcularPesoTotal();
		return vehiculo.getPeso() - peso;
	}

	public float calcularVolumenDisponible() {

		float volumen = 0;
		for (Carga c : cargas)
			volumen += c.calcularVolumenTotal();
		return vehiculo.getTamano().calcularVolumen() - volumen;
	}

	public int cantidadParadasIntemedias(){
		
		return paradasIntermedias.size();
	}

	public void generarRemito(){
		
	}

	public Integer getCodigo() {
		return codigo;
	}

	public List<CondicionEspecial> getCondicionesEspeciales() {
		return condicionesEspeciales;
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

	public List<Carga> getCargas() {
		return cargas;
	}

	public void setCargas(List<Carga> cargas) {
		this.cargas = cargas;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Ubicacion getOrigen() {
		return origen;
	}

	public void setOrigen(Ubicacion origen) {
		this.origen = origen;
	}

	public Ubicacion getDestino() {
		return destino;
	}

	public void setDestino(Ubicacion destino) {
		this.destino = destino;
	}
	
	public Date existeLLegadaUbicacion(Ubicacion ubicacion) {

		for (ParadaIntermedia p : paradasIntermedias)
			if (p.getUbicacion().equals(ubicacion))
				return p.getLlegada();
		return null;
	}
	
}
