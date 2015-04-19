package model.impl;

import java.io.Serializable;
import java.util.List;

public class Sucursal implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2534989790774285551L;
	
	private Integer numero;
	private String nombre;
	private Ubicacion ubicacion;
	private List<Deposito> depositos;
	private List<Empleado> empleados;
	private List<Vehiculo> vehiculos;
	private List<Viaje> viajes;
	
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Ubicacion getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}
	public List<Deposito> getDepositos() {
		return depositos;
	}
	public void setDepositos(List<Deposito> depositos) {
		this.depositos = depositos;
	}
	public List<Empleado> getEmpleados() {
		return empleados;
	}
	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}
	public List<Vehiculo> getVehiculos() {
		return vehiculos;
	}
	public void setVehiculos(List<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}
	public List<Viaje> getViajes() {
		return viajes;
	}
	public void setViajes(List<Viaje> viajes) {
		this.viajes = viajes;
	}
	
	public Carga obtenerCarga(Integer codigoCarga) {
		for (Deposito deposito : depositos) {
			for (Carga carga : deposito.getCargas()) {
				if (carga.getCodigo().equals(codigoCarga)) {
					return carga;
				}
			}
		}
		return null;
	}
	
	public Vehiculo obtenerVehiculo(String patente) {
		for (Vehiculo vehiculo : vehiculos) {
			if (vehiculo.getPatente().equals(patente)) {
				return vehiculo;
			}
		}
		return null;
	}
	
	public Empleado obtenerEmpleado(String cuit) {
		for (Empleado empleado : empleados) {
			if (empleado.getCuit().equals(cuit)) {
				return empleado;
			}
		}
		return null;
	}
	
	public Viaje obtenerViaje(Integer codigoViaje) {
		for (Viaje viaje : viajes) {
			if (viaje.getCodigo().equals(codigoViaje)) {
				return viaje;
			}
		}
		return null;
	}
	
	public Deposito obtenerDeposito(Integer codigoDeposito) {
		for (Deposito deposito : depositos) {
			if (deposito.getCodigo().equals(codigoDeposito)) {
				return deposito;
			}
		}
		return null;
	}
	
}
