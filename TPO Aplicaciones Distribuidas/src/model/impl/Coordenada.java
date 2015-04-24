package model.impl;

import model.views.CoordenadaView;

public class Coordenada {
	private Float latitud;
	private Float longitud;
	
	public Coordenada(CoordenadaView coordenadaDestino) {
		this.latitud = coordenadaDestino.getLatitud();
		this.longitud = coordenadaDestino.getLongitud();
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
