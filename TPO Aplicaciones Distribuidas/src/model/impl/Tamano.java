package model.impl;

import java.io.Serializable;

public class Tamano implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1221961491892312944L;
	private Float profundidad;
	private Float alto;
	private Float ancho;

	public Float getProfundidad() {
		return profundidad;
	}

	public void setProfundidad(Float profundidad) {
		this.profundidad = profundidad;
	}

	public Float getAlto() {
		return alto;
	}

	public void setAlto(Float alto) {
		this.alto = alto;
	}

	public Float getAncho() {
		return ancho;
	}

	public void setAncho(Float ancho) {
		this.ancho = ancho;
	}

	public Float calcularVolumen() {
		return profundidad * alto * ancho;
	}
}
