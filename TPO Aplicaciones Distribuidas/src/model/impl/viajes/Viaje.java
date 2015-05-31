package model.impl.viajes;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import model.impl.PersistentObject;
import model.impl.cargas.Carga;
import model.impl.misc.Ubicacion;
import model.impl.productos.CondicionEspecial;
import model.impl.sucursales.Sucursal;
import model.impl.vehiculos.Vehiculo;

@Entity
@Table(name = "Viajes")
@AttributeOverride(name = "id", column = @Column(name = "id_viaje"))
public class Viaje extends PersistentObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5092108929260301459L;

	@Column(name = "codigo")
	private Integer codigo;
	// @ManyToMany
	// @JoinTable(name = "ViajesCargas", joinColumns = {@JoinColumn(name =
	// "idViaje")}, inverseJoinColumns = {@JoinColumn(name = "idCarga")})
	
	@OneToMany
	@JoinColumn(name = "id_viaje")
	private List<Carga> cargas;
	
	@OneToOne
	@JoinColumn(name = "id_seguro")
	private Seguro seguro;
	
	@OneToOne
	@JoinColumn(name = "id_vehiculo")
	private Vehiculo vehiculo;
	
	@OneToOne
	@JoinColumn(name = "id_origen")
	private Ubicacion origen;
	
	@OneToOne
	@JoinColumn(name = "id_destino")	
	private Ubicacion destino;
	
	@Column(name = "fechaSalida")
	private Date fechaSalida;

	@Column(name = "fechaLlegada")
	private Date fechaLlegada;

	//@CollectionOfElements
	@JoinTable(name = "CondicionesViajes", joinColumns = { @JoinColumn(name = "idViaje") })
	@Enumerated(EnumType.STRING)
	private List<CondicionEspecial> condicionesEspeciales;
	
	@Column(name = "estaAtrasado")
	private boolean estaAtrasado;
	
	@OneToMany
	@JoinColumn(name = "idViaje")
	private Vector<ParadaIntermedia> paradasIntermedias;

	public Viaje(List<Carga> cargas, Seguro seguro, Vehiculo vehiculo,
			Date fechaSalida, List<CondicionEspecial> condicionesEspeciales,
			Vector<ParadaIntermedia> paradasIntermedias) {
		this.cargas = cargas;
		this.seguro = seguro;
		this.vehiculo = vehiculo;
		this.fechaSalida = fechaSalida;
		this.condicionesEspeciales = condicionesEspeciales;
		this.paradasIntermedias = paradasIntermedias;
		paradasIntermedias = new Vector<ParadaIntermedia>();
	}

	public void agregarCarga(Carga carga) {

		if (puedeTransportar(carga))
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

	public int cantidadParadasIntemedias() {

		return paradasIntermedias.size();
	}

	public void generarRemito() {

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

	public boolean pasaPorSucursal(Sucursal sucursal) {
		if (origen.equals(sucursal.getUbicacion())
				|| destino.equals(sucursal.getUbicacion()))
			return true;
		for (ParadaIntermedia parada : paradasIntermedias) {
			if (!parada.isChecked()
					&& parada.getUbicacion().equals(sucursal.getUbicacion()))
				return true;
		}
		return false;
	}

	public boolean puedeTransportar(Carga carga) {
		return carga.calcularPesoTotal() <= calcularPesoDisponible()
				&& carga.calcularVolumenTotal() <= calcularVolumenDisponible();
	}

	public Date obtenerLlegadaAParada(Sucursal sucursal) {
		if (pasaPorSucursal(sucursal)) {
			if (destino.equals(sucursal.getUbicacion())) {
				return fechaLlegada;
			}
			for (ParadaIntermedia parada : paradasIntermedias) {
				if (parada.getUbicacion().equals(sucursal.getUbicacion())) {
					return parada.getLlegada();
				}
			}
		}
		return null;
	}

	@Override
	public boolean equals(Object v) {
		if (v instanceof Viaje) {
			if (this.codigo == ((Viaje) v).getCodigo()) {
				return true;
			}
		}
		return false;
	}
}
