package model.impl;

import java.io.Serializable;
import java.util.List;

public class Producto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2506118120974790841L;
	
	private int codigoProducto;
	private String nombre;
	private Float peso;
	private Tamano tamano;
	private TipoFragilidad fragilidad;
	private Integer apilable;
	private String manipulacion;
	private String material;
	private TipoTratamiento tratamiento;
	private String consideraciones;
	private List<CondicionEspecial> condicionesEspeciales;
	private boolean refrigerada;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Float getPeso() {
		return peso;
	}
	public void setPeso(Float peso) {
		this.peso = peso;
	}
	public Tamano getTamano() {
		return tamano;
	}
	public void setTamano(Tamano tamano) {
		this.tamano = tamano;
	}
	public TipoFragilidad getFragilidad() {
		return fragilidad;
	}
	public void setFragilidad(TipoFragilidad fragilidad) {
		this.fragilidad = fragilidad;
	}
	public Integer getApilable() {
		return apilable;
	}
	public void setApilable(Integer apilable) {
		this.apilable = apilable;
	}
	public String getManipulacion() {
		return manipulacion;
	}
	public void setManipulacion(String manipulacion) {
		this.manipulacion = manipulacion;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public TipoTratamiento getTratamiento() {
		return tratamiento;
	}
	public void setTratamiento(TipoTratamiento tratamiento) {
		this.tratamiento = tratamiento;
	}
	public String getConsideraciones() {
		return consideraciones;
	}
	public void setConsideraciones(String consideraciones) {
		this.consideraciones = consideraciones;
	}
	public List<CondicionEspecial> getCondicionesEspeciales() {
		return condicionesEspeciales;
	}
	public void setCondicionesEspeciales(
			List<CondicionEspecial> condicionesEspeciales) {
		this.condicionesEspeciales = condicionesEspeciales;
	}
	public boolean isRefrigerada() {
		return refrigerada;
	}
	public void setRefrigerada(boolean refrigerada) {
		this.refrigerada = refrigerada;
	}
	public int getCodigoProducto() {
		return codigoProducto;
	}
	public void setCodigoProducto(int codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	
	
}
