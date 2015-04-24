package model.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import model.impl.Carga;
import model.impl.Cliente;
import model.impl.Cobro;
import model.impl.CompaniaSeguro;
import model.impl.CondicionEspecial;
import model.impl.CuentaCorriente;
import model.impl.DistanciaEntreSucursales;
import model.impl.Empleado;
import model.impl.Empresa;
import model.impl.EstrategiaMantenimiento;
import model.impl.ItemProducto;
import model.impl.Pago;
import model.impl.ParadaIntermedia;
import model.impl.Particular;
import model.impl.Producto;
import model.impl.Proveedor;
import model.impl.Seguro;
import model.impl.Sucursal;
import model.impl.Ubicacion;
import model.impl.Vehiculo;
import model.impl.Viaje;
import model.views.CargaView;
import model.views.ItemProductoView;

public class ControladorPrincipal {

	public static ControladorPrincipal getInstance() {
		if (instance == null) 
			instance = new ControladorPrincipal();
		return instance;
	}
	
	private static ControladorPrincipal instance;
	private List<Sucursal> sucursales;
	private List<Cliente> clientes;
	private List<CompaniaSeguro> companiasSeguros;
	private List<String> materialesNoTransportables;
	private List<Proveedor> proveedores;
	private List<Pago> pagos;
	private List<Cobro> cobros;
	private List<EstrategiaMantenimiento> mantenimientos;
	private List<Producto> productos;
	private List<DistanciaEntreSucursales> distancias;
	private List<Viaje> viajes;

	private ControladorPrincipal() {
		clientes = new ArrayList<Cliente>();
		sucursales = new ArrayList<Sucursal>();
		companiasSeguros = new ArrayList<CompaniaSeguro>();
		materialesNoTransportables = new ArrayList<String>();
		proveedores = new ArrayList<Proveedor>();
		pagos = new ArrayList<Pago>();
		cobros = new ArrayList<Cobro>();
		mantenimientos = new ArrayList<EstrategiaMantenimiento>();
		productos = new ArrayList<Producto>();
		viajes = new ArrayList<Viaje>();
	}

	//ABM CLIENTES

	public void altaClienteEmpresa(String codigoUnico, String nombre) throws Exception {

		if (obtenerCliente(codigoUnico) == null)
			clientes.add(new Empresa(codigoUnico, nombre));
		else 
			throw new Exception("Cliente con codigo "+codigoUnico+" ya existe");
	}

	public void altaClienteParticular(String codigoUnico, String dni,
			String nombre, String apellido) throws Exception {

		if (obtenerCliente(codigoUnico) == null)
			clientes.add(new Particular(codigoUnico, dni, nombre, apellido));
		else
			throw new Exception("Cliente con codigo "+codigoUnico+" ya existe");
	}

	public void bajaCliente(String codigoUnico){
		for (Cliente c : clientes){
			if (c.getCodigoUnico().equals(codigoUnico)){
				clientes.remove(c);
				return;
			}
		}
	}

	//ABM SUCURSALES

	public void altaSucursal(Integer numero, String nombre){		
		sucursales.add(new Sucursal(numero, nombre));
	}


	//ABM EMPLEADOS

	public void altaEmpleado(String cuit, String dni, String nombre,
			String apellido, Date fechaNacimiento, Integer numeroSucursal) throws Exception {

		if (obtenerEmpleado(cuit) == null)
			obtenerSucursal(numeroSucursal).agregarEmpleado(
					new Empleado(cuit, dni, nombre, apellido, fechaNacimiento));
		else
			throw new Exception("Empleado con cuit "+cuit+" ya existe");

	}

	//OTROS

	public void asignarCuentaCorriente(String codigoUnico, Float montoActual, Float montoAutorizado) throws Exception{

		if(esClienteEmpresa(codigoUnico)){

			Empresa c = (Empresa) obtenerCliente(codigoUnico);
			c.setCuentaCorriente(new CuentaCorriente(montoActual, montoAutorizado));		
		}
		else
			throw new Exception("El cliente "+codigoUnico+" no es una empresa");
	}

	public boolean esClienteEmpresa(String codigoUnico){

		for(Cliente c : clientes)
			if(c.getCodigoUnico().equals(codigoUnico) && c.getClass().equals(Empresa.class))
				return true;
		return false;
	}

	public Date estimarLlegada(List<ItemProducto> productos, Ubicacion origen,
			Ubicacion destino) {
		Date partida = new Date();

		Sucursal cercana = obtenerSucursalCercana(destino);

		DistanciaEntreSucursales distancia = null;

		for (DistanciaEntreSucursales dist : distancias) {
			if (dist.getSucursalA().getUbicacion().equals(origen)
					&& dist.getSucursalB().getUbicacion().equals(destino)
					|| dist.getSucursalA().getUbicacion().equals(destino)
					&& dist.getSucursalB().getUbicacion().equals(origen)) {
				distancia = dist;
			}
		}

		int minutos = (int) (distancia.getDuracionEnHoras() % 1) * 60;
		int horas = (int) (distancia.getDuracionEnHoras() - distancia
				.getDuracionEnHoras() % 1);

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR, horas);
		cal.add(Calendar.MINUTE, minutos);

		return cal.getTime();
	}

	public float calcularCostoViaje(Integer sucursalA, Integer sucursalB){

		for(DistanciaEntreSucursales d : distancias)
			if(d.getSucursalA().getNumero() == sucursalA || d.getSucursalB().getNumero() == sucursalB)
				if(d.getSucursalB().getNumero() == sucursalB || d.getSucursalB().getNumero() == sucursalA)
					return d.getCosto();
		return 0;	
	}

	public Cliente obtenerCliente(String codigoUnico) {

		for (Cliente c : clientes)
			if (c.getCodigoUnico().equals(codigoUnico))
				return c;
		return null;
	}

	public void asignarCargaASucursal(int codigoSucursal, CargaView carga) throws Exception{
		Sucursal sucursal = obtenerSucursal(codigoSucursal);
		if (sucursal != null){
			Cliente cliente = obtenerCliente(carga.getCliente());
			if (cliente != null){
				if (!sucursal.getDeposito().existeCarga(carga.getCodigo())){
					sucursal.getDeposito().almacenarCarga(carga.getCodigo(), carga.getTipo(), carga.getFechaMaximaEntrega(),
							carga.getFechaProbableEntrega(), cliente, carga.getManifiesto(), carga.getOrigen(),
							carga.getDestino(), carga.getEstadoCarga());
					for (ItemProductoView ipv : carga.getProductos()){
						Producto producto = obtenerProducto(ipv.getProducto());
						if(producto != null){
							sucursal.getDeposito().obtenerCarga(carga.getCodigo()).agregarItemProducto(producto, ipv.getCantidad());	
						}
					}
				}
				else{
					throw new Exception("Esta sucursal ya tiene una carga de codigo: " + carga.getCodigo() + ".");
				}
			}
			else{
				throw new Exception("Cliente de codigo " + carga.getCliente() + " inexistente.");
			}
		}
		else{
			throw new Exception("Sucursal de codigo " + codigoSucursal + " inexistente.");
		}
	}

	public void altaViaje(int codigo, List<Carga> cargas, Seguro seguro, Vehiculo vehiculo, Date fechaSalida,
			List<CondicionEspecial> condicionesEspeciales, Vector<ParadaIntermedia> paradasIntermedias) throws Exception{
		if (obtenerViaje(codigo) == null){
			viajes.add(new Viaje(codigo, cargas, seguro, vehiculo, fechaSalida, condicionesEspeciales, paradasIntermedias));
		}
		else{
			throw new Exception("Ya existe un viaje con el codigo: " + codigo);
		}
	}

	//OBTENER COSAS

	public Empleado obtenerEmpleado(String cuit) {

		for (Sucursal s : sucursales)
			for (Empleado e : s.getEmpleados())
				if (e.getCuit().equals(cuit))
					return e;
		return null;
	}

	public Sucursal obtenerSucursal(Integer numero) {

		for (Sucursal s : sucursales)
			if (s.getNumero() == numero)
				return s;
		return null;
	}

	private Producto obtenerProducto(int codigoProducto) {
		for (Producto p : productos)
			if (p.getCodigoProducto() == codigoProducto)
				return p;
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


	private Sucursal obtenerSucursalCercana(Ubicacion ubicacion) {
		Sucursal cercana = null;

		for (Sucursal sucursal : sucursales) {
			if (cercana == null
					|| cercana.getUbicacion().calcularDistanciaEnKilometros(
							ubicacion) > sucursal.getUbicacion()
							.calcularDistanciaEnKilometros(ubicacion)) {
				cercana = sucursal;
			}
		}

		return cercana;
	}

	/*******************************/
	/****** metodos de prueba ******/
	/*******************************/

	public List<Sucursal> getSucursales() {
		return sucursales;
	}

	public void setSucursales(List<Sucursal> sucursales) {
		this.sucursales = sucursales;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<CompaniaSeguro> getCompaniasSeguros() {
		return companiasSeguros;
	}

	public void setCompaniasSeguros(List<CompaniaSeguro> companiasSeguros) {
		this.companiasSeguros = companiasSeguros;
	}

	public List<String> getMaterialesNoTransportables() {
		return materialesNoTransportables;
	}

	public void setMaterialesNoTransportables(
			List<String> materialesNoTransportables) {
		this.materialesNoTransportables = materialesNoTransportables;
	}

	public List<Proveedor> getProveedores() {
		return proveedores;
	}

	public void setProveedores(List<Proveedor> proveedores) {
		this.proveedores = proveedores;
	}

	public List<Pago> getPagos() {
		return pagos;
	}

	public void setPagos(List<Pago> pagos) {
		this.pagos = pagos;
	}

	public List<Cobro> getCobros() {
		return cobros;
	}

	public void setCobros(List<Cobro> cobros) {
		this.cobros = cobros;
	}

	public List<EstrategiaMantenimiento> getMantenimientos() {
		return mantenimientos;
	}

	public void setMantenimientos(List<EstrategiaMantenimiento> mantenimientos) {
		this.mantenimientos = mantenimientos;
	}

	public List<DistanciaEntreSucursales> getDistancias() {
		return distancias;
	}

	public void setDistancias(List<DistanciaEntreSucursales> distancias) {
		this.distancias = distancias;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}


	public List<Viaje> getViajes() {
		return viajes;
	}

	public void setViajes(List<Viaje> viajes) {
		this.viajes = viajes;
	}
}
