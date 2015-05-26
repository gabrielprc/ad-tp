package model.impl.viajes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import model.impl.PersistentObject;
import model.impl.vehiculos.VehiculoExterno;

@Entity
@Table(name = "Proveedores")
public class Proveedor{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4550038024972428465L;

	@Id
	@Column (name="id_proveedor")
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Integer id;
	@Column(name = "cuit")
	private String cuit;
	@Column(name = "nombre")
	private String nombre;
	
	public Proveedor(){
		
	}
	
	public Proveedor(String cuit, String nombre){
		
		this.cuit = cuit;
		this.nombre = nombre;
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

//	public boolean existeVehiculo(String patente) {
//		for (VehiculoExterno v : vehiculos){
//			if (v.getPatente().equals(patente)){
//				return true;
//			}
//		}
//		return false;
//	}	
//	
//	public VehiculoExterno obtenerVehiculo(String patente) {
//		for (VehiculoExterno vehiculo : vehiculos) {
//			if (vehiculo.getPatente().equals(patente)) {
//				return vehiculo;
//			}
//		}
//		return null;
//	}	
//	
//	public void agregarVehiculo(VehiculoExterno vehiculo) {
//		vehiculos.add(vehiculo);		
//	}
	
}
