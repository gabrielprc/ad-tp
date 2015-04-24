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
	private Deposito deposito;
	private List<Empleado> empleados;
	private List<Vehiculo> vehiculos;

	public Sucursal(int numero, String nombre) {
		this.numero = numero;
		this.nombre = nombre;
		deposito = new Deposito(1); //arreglar esto
	}

	public Deposito getDeposito() {
		return deposito;
	}

	public void setDeposito(Deposito deposito) {
		this.deposito = deposito;
	}

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

	public Carga retirarCarga(Integer codigoCarga) {

		for (Carga carga : deposito.getCargas()) {
			if (carga.getCodigo().equals(codigoCarga)) {
				return carga;
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

	public void crearViaje() {

	}

	public void asignarSeguroAViaje(Seguro seguro, Viaje viaje) {

	}

	public void agregarEmpleado(Empleado e) {

		this.empleados.add(e);
	}

}
