package model.impl.misc;

import java.io.Serializable;

public class Ubicacion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3638263239943127552L;

	private Integer codigo;
	private String pais;
	private String provincia;
	private String ciudad;
	private String calle;
	private String altura;
	private String piso;
	private String departamento;
	private Coordenada coordenadaDestino;

	public Ubicacion(int codigo, String pais, String provincia, String ciudad, String calle,
			String altura, String piso, String departamento, Coordenada coordenadaDestino) {
		this.codigo = codigo;
		this.pais = pais;
		this.provincia = provincia;
		this.ciudad = ciudad;
		this.calle = calle;
		this.altura = altura;
		this.piso = piso;
		this.departamento = departamento;
		this.coordenadaDestino = coordenadaDestino;
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

	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	@Override
	public boolean equals (Object ubicacionB){
		if (ubicacionB instanceof Ubicacion){
			if (this.codigo.equals(((Ubicacion) ubicacionB).getCodigo())){
				return true;
			}
		}
		return false;
	}
}
