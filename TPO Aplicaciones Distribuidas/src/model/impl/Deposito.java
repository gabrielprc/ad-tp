package model.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Deposito implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7943859772129611103L;
	
	private Integer codigo;
	private List<Carga> cargas;

	public List<Carga> getCargas() {
		return cargas;
	}

	public void setCargas(List<Carga> cargas) {
		this.cargas = cargas;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	public void almacenarCarga(Carga carga) {
		if (cargas == null) {
			cargas = new ArrayList<Carga>();
		}
		cargas.add(carga);
	}
	
	public void retirarCarga(Carga carga) {
		if (cargas != null) {
			for (Iterator<Carga> iterator = cargas.listIterator(); iterator.hasNext();) {
				if (iterator.next().getCodigo().equals(carga.getCodigo())) {
					iterator.remove();
				}
			}
		}
	}
}
