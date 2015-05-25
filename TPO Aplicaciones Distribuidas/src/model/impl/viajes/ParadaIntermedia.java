package model.impl.viajes;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import model.impl.misc.Ubicacion;

@Entity
@Table(name = "ParadasIntermedias")
public class ParadaIntermedia {

	@ManyToOne
	@JoinColumn(name = "idUbicacion")
	private Ubicacion ubicacion;
	@Column(name = "llegada")
	private Date llegada;
	@Column(name = "checked")
	private boolean checked;

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Date getLlegada() {
		return llegada;
	}

	public void setLlegada(Date llegada) {
		this.llegada = llegada;
	}

}
