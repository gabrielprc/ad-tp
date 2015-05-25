package model.impl.sucursales;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DistanciaEntreSucursales")
public class DistanciaEntreSucursales {
	
	@ManyToOne
	@JoinColumn(name = "idSucursalA")
	private Sucursal sucursalA;
	@ManyToOne
	@JoinColumn(name = "idSucursalB")
	private Sucursal sucursalB;
	@Column(name = "distanciaEnKm")
	private float distanciaEnKm;
	@Column(name = "duracionEnHoras")
	private float duracionEnHoras;
	@Column(name = "costo")
	private float costo;
	
	public DistanciaEntreSucursales (Sucursal sucursalA, Sucursal sucursalB, float distanciaEnKm,
			float duracionEnHoras, float costo){
		this.sucursalA = sucursalA;
		this.sucursalB = sucursalB;
		this.distanciaEnKm = distanciaEnKm;
		this.duracionEnHoras = duracionEnHoras;
		this.costo = costo;
	}

	public Sucursal getSucursalA() {
		return sucursalA;
	}

	public void setSucursalA(Sucursal sucursalA) {
		this.sucursalA = sucursalA;
	}

	public Sucursal getSucursalB() {
		return sucursalB;
	}

	public void setSucursalB(Sucursal sucursalB) {
		this.sucursalB = sucursalB;
	}

	public float getDistanciaEnKm() {
		return distanciaEnKm;
	}

	public void setDistanciaEnKm(float distanciaEnKm) {
		this.distanciaEnKm = distanciaEnKm;
	}

	public float getDuracionEnHoras() {
		return duracionEnHoras;
	}

	public void setDuracionEnHoras(float duracionEnHoras) {
		this.duracionEnHoras = duracionEnHoras;
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

}
