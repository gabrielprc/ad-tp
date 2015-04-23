package model.impl;

public class DistanciaEntreSucursales {
	private Sucursal sucursalA;
	private Sucursal sucursalB;
	private float distancia;
	
	public DistanciaEntreSucursales (Sucursal sucursalA, Sucursal sucursalB, float distancia){
		this.sucursalA = sucursalA;
		this.sucursalB = sucursalB;
		this.distancia = distancia;
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

	public float getDistancia() {
		return distancia;
	}

	public void setDistancia(float distancia) {
		this.distancia = distancia;
	}
}
