package model.impl;

import java.io.Serializable;

public class Seguro implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1508681287329096593L;
	
	private Integer codigo;
	private String nombre;
	private TipoCarga tipoCarga;
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
