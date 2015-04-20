package model.impl;

import java.io.Serializable;
import java.util.Collection;

public class Envio implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4243461213353973897L;

	private Collection<Carga> cargas;
	private Ubicacion destinoIntermedio;

	public Collection<Carga> getCargas() {
		return cargas;
	}

	public void setCarga(Collection<Carga> cargas) {
		this.cargas = cargas;
	}

	public Ubicacion getDestinoIntermedio() {
		return destinoIntermedio;
	}

	public void setDestinoIntermedio(Ubicacion destinoIntermedio) {
		this.destinoIntermedio = destinoIntermedio;
	}

	public float calcularEnvioTotal(){
		
		return 0;
		
	}
	
}
