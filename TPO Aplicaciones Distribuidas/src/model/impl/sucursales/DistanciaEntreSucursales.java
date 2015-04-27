package model.impl.sucursales;

public class DistanciaEntreSucursales {
	private Sucursal sucursalA;
	private Sucursal sucursalB;
	private float distanciaEnKm;
	private float duracionEnHoras;
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
