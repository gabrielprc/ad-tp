package model.impl.sucursales;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import model.impl.PersistentObject;
import model.impl.cargas.Carga;
import model.impl.misc.Ubicacion;
import model.impl.personal.Empleado;
import model.impl.vehiculos.VehiculoLocal;
import model.impl.viajes.Seguro;
import model.impl.viajes.Viaje;

@Entity
@Table(name = "Sucursales")
public class Sucursal extends PersistentObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2534989790774285551L;

	@Column(name = "numero")
	private Integer numero;
	@Column(name = "nombre")
	private String nombre;
	@ManyToOne
	@JoinColumn(name = "idUbicacion")
	private Ubicacion ubicacion;
	@OneToOne
	@PrimaryKeyJoinColumn 
	private Deposito deposito;
	@OneToMany
	@JoinColumn(name = "idSucursal")
	private List<Empleado> empleados;
	@OneToMany
	@JoinColumn(name = "idSucursal")
	private List<VehiculoLocal> vehiculos;

	public Sucursal(int numero, String nombre, Ubicacion ubicacion) {
		this.numero = numero;
		this.nombre = nombre;
		deposito = new Deposito(1); //arreglar esto
		this.ubicacion = ubicacion;
		vehiculos = new ArrayList<VehiculoLocal>();
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

	public List<VehiculoLocal> getVehiculos() {
		return vehiculos;
	}

	public void setVehiculos(List<VehiculoLocal> vehiculos) {
		this.vehiculos = vehiculos;
	}

	public Carga retirarCarga(Integer codigoCarga) {

		for (Carga carga : deposito.getCargas()) {
			if (carga.getId().equals(codigoCarga)) {
				return carga;
			}
		}

		return null;
	}

	public VehiculoLocal obtenerVehiculo(String patente) {
		for (VehiculoLocal vehiculo : vehiculos) {
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

	public void agregarVehiculo(VehiculoLocal vehiculo) {
		vehiculos.add(vehiculo);		
	}

	public boolean existeVehiculo(String patente) {
		for (VehiculoLocal v : vehiculos){
			if (v.getPatente().equals(patente)){
				return true;
			}
		}
		return false;
	}

}
