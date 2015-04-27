package model.impl.viajes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.impl.vehiculos.VehiculoExterno;

public class Proveedor implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4550038024972428465L;

	private String cuit;
	private String nombre;
	private List<VehiculoExterno> vehiculos;
	
	public Proveedor(String cuit, String nombre){
		
		this.cuit = cuit;
		this.nombre = nombre;
		vehiculos = new ArrayList<VehiculoExterno>();
	}
	
	
	public String getCuit() {
		return cuit;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<VehiculoExterno> getVehiculos() {
		return vehiculos;
	}
	public void setVehiculos(List<VehiculoExterno> vehiculos) {
		this.vehiculos = vehiculos;
	}


	public boolean existeVehiculo(String patente) {
		for (VehiculoExterno v : vehiculos){
			if (v.getPatente().equals(patente)){
				return true;
			}
		}
		return false;
	}	
	
	public VehiculoExterno obtenerVehiculo(String patente) {
		for (VehiculoExterno vehiculo : vehiculos) {
			if (vehiculo.getPatente().equals(patente)) {
				return vehiculo;
			}
		}
		return null;
	}	
	
	public void agregarVehiculo(VehiculoExterno vehiculo) {
		vehiculos.add(vehiculo);		
	}
	
}
