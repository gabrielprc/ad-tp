package model.impl.viajes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import model.impl.PersistentObject;
import model.impl.vehiculos.VehiculoExterno;

@Entity
@Table(name = "Proveedores")
public class Proveedor extends PersistentObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4550038024972428465L;

	@Column(name = "cuit")
	private String cuit;
	@Column(name = "nombre")
	private String nombre;
	@OneToMany
	@JoinColumn(name = "idProveedor")
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
