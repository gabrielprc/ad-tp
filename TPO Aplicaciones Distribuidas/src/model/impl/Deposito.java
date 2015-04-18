package model.impl;

import java.io.Serializable;
import java.util.List;

public class Deposito implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7943859772129611103L;
	
	private List<Carga> cargas;

	public List<Carga> getCargas() {
		return cargas;
	}

	public void setCargas(List<Carga> cargas) {
		this.cargas = cargas;
	}
}
