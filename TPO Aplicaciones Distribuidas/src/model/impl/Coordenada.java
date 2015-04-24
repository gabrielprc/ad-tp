package model.impl;


public class Coordenada {
	private float latitud;
	private float longitud;
	
	public Coordenada(float latitud, float longitud) {
		this.latitud = latitud;
		this.longitud = longitud;
	}
	public Float getLatitud() {
		return latitud;
	}
	public void setLatitud(Float latitud) {
		this.latitud = latitud;
	}
	public Float getLongitud() {
		return longitud;
	}
	public void setLongitud(Float longitud) {
		this.longitud = longitud;
	}
	
	public float calcularDistanciaEnKilometros(Coordenada coordenada) {
		float kilometrosPorGrado = 111.12F;
		
		float difLatitud = Math.abs(this.latitud - coordenada.getLatitud());
		float difLongitud = Math.abs(this.longitud - coordenada.getLongitud());
		
		float cateto = (float) Math.sqrt((difLatitud * difLatitud) + (difLongitud * difLongitud));
		
		return cateto * kilometrosPorGrado;
	}
	
}
