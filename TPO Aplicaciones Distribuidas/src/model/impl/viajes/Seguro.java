package model.impl.viajes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import model.impl.PersistentObject;
import model.impl.cargas.TipoCarga;

@Entity
@Table(name = "Seguros")
public class Seguro extends PersistentObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1508681287329096593L;
	
	@Column(name = "codigo")
	private Integer codigo;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "tipoCarga")
	@Enumerated(value = EnumType.STRING)
	private TipoCarga tipoCarga;
	@Column(name = "tarifa")
	private Float tarifa;
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public TipoCarga getTipoCarga() {
		return tipoCarga;
	}
	public void setTipoCarga(TipoCarga tipoCarga) {
		this.tipoCarga = tipoCarga;
	}
	public Float getTarifa() {
		return tarifa;
	}
	public void setTarifa(Float tarifa) {
		this.tarifa = tarifa;
	}
	
}
