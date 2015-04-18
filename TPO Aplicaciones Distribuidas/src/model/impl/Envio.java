package model.impl;

import java.io.Serializable;

public class Envio implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4243461213353973897L;
	
	private Carga carga;
	private Ubicacion destinoIntermedio;
	
	public Carga getCarga() {
		return carga;
	}
	public void setCarga(Carga carga) {
		this.carga = carga;
	}
	public Ubicacion getDestinoIntermedio() {
		return destinoIntermedio;
	}
	public void setDestinoIntermedio(Ubicacion destinoIntermedio) {
		this.destinoIntermedio = destinoIntermedio;
	}
	
}
