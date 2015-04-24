package model.impl;

import java.io.Serializable;

import model.views.UbicacionView;

public class Ubicacion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3638263239943127552L;
	
	private String pais;
	private String provincia;
	private String ciudad;
	private String calle;
	private String altura;
	private String piso;
	private String departamento;
	private Coordenada coordenadaDestino;
	
	public Ubicacion(UbicacionView ubicacion) {
		this.pais = ubicacion.getPais();
		this.provincia = ubicacion.getProvincia();
		this.ciudad = ubicacion.getCiudad();
		this.calle = ubicacion.getCalle();
		this.altura = ubicacion.getAltura();
		this.piso = ubicacion.getPiso();
		this.departamento = ubicacion.getDepartamento();
		this.coordenadaDestino = new Coordenada(ubicacion.getCoordenadaDestino());
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getAltura() {
		return altura;
	}
	public void setAltura(String altura) {
		this.altura = altura;
	}
	public String getPiso() {
		return piso;
	}
	public void setPiso(String piso) {
		this.piso = piso;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public Coordenada getCoordenadaDestino() {
		return coordenadaDestino;
	}
	public void setCoordenadaDestino(Coordenada coordenadaDestino) {
		this.coordenadaDestino = coordenadaDestino;
	}
	
	public float calcularDistanciaEnKilometros(Ubicacion ubicacion) {
		return this.coordenadaDestino.calcularDistanciaEnKilometros(ubicacion.getCoordenadaDestino());
	}
	
}
