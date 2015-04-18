package model.impl;

import java.util.List;

public class Particular extends Cliente {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3516360528659218982L;
	
	private List<Persona> receptores;
	private Persona titular;
	
	public List<Persona> getReceptores() {
		return receptores;
	}
	public void setReceptores(List<Persona> receptores) {
		this.receptores = receptores;
	}
	public Persona getTitular() {
		return titular;
	}
	public void setTitular(Persona titular) {
		this.titular = titular;
	}
	
}
