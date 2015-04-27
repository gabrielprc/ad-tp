package model.impl.sucursales;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.impl.cargas.Carga;

public class Deposito implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7943859772129611103L;
	
	private int codigo;
	private List<Carga> cargas;
	
	public Deposito(int codigo){
		this.codigo = codigo;
		cargas = new ArrayList<Carga>();
	}

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
	
	public void almacenarCarga(Carga carga){
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

	public Carga obtenerCarga(int codigoCarga) {
		for (Carga c : cargas)
			if (c.getCodigo() == codigoCarga)
				return c;
		return null;
	}
	
	public boolean existeCarga(int codigoCarga){
		for (Carga c : cargas)
			if (c.getCodigo() == codigoCarga)
				return true;
		return false;
	}
}
