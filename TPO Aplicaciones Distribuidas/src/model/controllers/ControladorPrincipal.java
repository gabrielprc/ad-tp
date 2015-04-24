package model.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
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
	private List<String> materialesProhibidos;
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
		materialesProhibidos = new ArrayList<String>();
		proveedores = new ArrayList<Proveedor>();
		pagos = new ArrayList<Pago>();
		cobros = new ArrayList<Cobro>();
		mantenimientos = new ArrayList<EstrategiaMantenimiento>();
		productos = new ArrayList<Producto>();
		viajes = new ArrayList<Viaje>();
	}

	/* ABM CLIENTES */

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

	/* ABM SUCURSALES */

	public void altaSucursal(Integer numero, String nombre){		
		sucursales.add(new Sucursal(numero, nombre));
	}

	/* ABM EMPLEADOS */

	public void altaEmpleado(String cuit, String dni, String nombre,
			String apellido, Date fechaNacimiento, Integer numeroSucursal) throws Exception {

		if (obtenerEmpleado(cuit) == null)
			obtenerSucursal(numeroSucursal).agregarEmpleado(
					new Empleado(cuit, dni, nombre, apellido, fechaNacimiento));
		else
			throw new Exception("Empleado con cuit "+cuit+" ya existe");

	}

	/* ABM VIAJES */

	public void altaViaje(int codigo, List<Carga> cargas, Seguro seguro, Vehiculo vehiculo, Date fechaSalida,
			List<CondicionEspecial> condicionesEspeciales, Vector<ParadaIntermedia> paradasIntermedias) throws Exception{
		if (obtenerViaje(codigo) == null){
			viajes.add(new Viaje(codigo, cargas, seguro, vehiculo, fechaSalida, condicionesEspeciales, paradasIntermedias));
		}
		else{
			throw new Exception("Ya existe un viaje con el codigo: " + codigo);
		}
	}
	
	public void actualizarViaje(Viaje viaje, Sucursal sucursal) {
		for (Iterator<Carga> iterator = viaje.getCargas().iterator(); iterator.hasNext();) {
			Carga carga = iterator.next();
			if (false/*TODO chequear si hay un viaje mejor parando en esta sucursal ahora*/) {
				sucursal.getDeposito().almacenarCarga(carga);
				iterator.remove();
			}
		}
		for (ParadaIntermedia parada : viaje.getParadasIntermedias()) {
			if (parada.getUbicacion().equals(sucursal.getUbicacion())) {
				parada.setChecked(true);
				break;
			}
		}
	}
	
	/* OTROS */

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

	public float calcularCostoSucursales(Integer sucursalA, Integer sucursalB){

		for(DistanciaEntreSucursales d : distancias)
			if(d.getSucursalA().getNumero() == sucursalA || d.getSucursalB().getNumero() == sucursalB)
				if(d.getSucursalB().getNumero() == sucursalB || d.getSucursalB().getNumero() == sucursalA)
					return d.getCosto();
		return 0;	
	}

	public void asignarCargaASucursal(int codigoSucursal, Carga carga) throws Exception{
		Sucursal sucursal = obtenerSucursal(codigoSucursal);
		if (sucursal != null){
			Cliente cliente = carga.getCliente();
			if (cliente != null){
				if (!sucursal.getDeposito().existeCarga(carga.getCodigo())){
					if (!tieneMaterialesProhibidos(carga)){
						sucursal.getDeposito().almacenarCarga(carga);
					}
					else{
						throw new Exception("La carga de codigo " + carga.getCodigo() + " tiene materiales prohibidos.");
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

	private boolean tieneMaterialesProhibidos(Carga carga){
		for (ItemProducto ip : carga.getProductos()){
			if (esMaterialProhibido(ip.getProducto().getMaterial())){
				return true;
			}
		}
		return false;
	}

	private boolean esMaterialProhibido(String material){
		for (String s : materialesProhibidos){
			if (s.equals(material)){
				return true;
			}
		}
		return false;
	}
	
	public float calcularCostoSucursales(Sucursal sucursalA, Sucursal sucursalB) {

		for (DistanciaEntreSucursales d : distancias)
			if (d.getSucursalA().getNumero() == sucursalA.getNumero()
					|| d.getSucursalB().getNumero() == sucursalB.getNumero())
				if (d.getSucursalB().getNumero() == sucursalB.getNumero()
						|| d.getSucursalB().getNumero() == sucursalA
								.getNumero())
					return d.getDuracionEnHoras();
		return 0;
	}

	public void determinarCostoViaje(Viaje viaje) {

		Viaje v = obtenerViaje(viaje.getCodigo());
		Date llegada = new Date();

		if (v == null)
			return;
		if (v.getParadasIntermedias().size() == 0) {

			Sucursal sucursalA = null, sucursalB = null;

			for (Sucursal s : sucursales) {
				if (viaje.getOrigen().equals(s.getUbicacion()))
					sucursalA = s;
				if (viaje.getOrigen().equals(s.getUbicacion()))
					sucursalB = s;
			}
			if (sucursalA == null || sucursalB == null)
				return;
			float costo = calcularCostoSucursales(sucursalA, sucursalB);
			int horas = (int) costo;
			int minutos = (int) (60 * (costo - horas));

			llegada.setHours(llegada.getHours() + horas);
			llegada.setMinutes(llegada.getMinutes() + minutos);
			v.setFechaLlegada(llegada);
		}
		if (v.getParadasIntermedias().size() > 0) {

			float costo = calcularCostoSucursales(
					obtenerSucursalPorUbicacion(v.getOrigen()), obtenerSucursalPorUbicacion(v
							.getParadasIntermedias().firstElement()
							.getUbicacion()));

			int horas = (int) costo;
			int minutos = (int) (60 * (costo - horas));

			v.getParadasIntermedias().firstElement()
					.setLlegada(v.getFechaSalida());
			v.getParadasIntermedias().firstElement().getLlegada()
					.setHours(horas);
			;

			v.getParadasIntermedias().firstElement().setChecked(true);

			for (int i = 0; i < v.getParadasIntermedias().size(); i++) {

				if (!v.getParadasIntermedias().get(i).isChecked()) {

					
				}

			}

		}

	}

	/* OBTENER */
	
	public Cliente obtenerCliente(String codigoUnico) {

		for (Cliente c : clientes)
			if (c.getCodigoUnico().equals(codigoUnico))
				return c;
		return null;
	}

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

	private Viaje obtenerViaje(Integer codigoViaje) {
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

	private Ubicacion obtenerUbicacion(int codigoUbicacion) {
		for (Sucursal sucursal : sucursales) {
			if (sucursal.getUbicacion().getCodigo() == codigoUbicacion) {
				return sucursal.getUbicacion();
			}
		}
		return null;
	}

	public Sucursal obtenerSucursalPorUbicacion(Ubicacion u) {
		for (Sucursal s : sucursales)
			if (s.getUbicacion().equals(u))
				return s;
		return null;
	}
	
	/* GETTERS Y SETTERS PARA TESTEAR */

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

	public List<String> getMaterialesProhibidos() {
		return materialesProhibidos;
	}

	public void setMaterialesProhibidos(List<String> materialesProhibidos) {
		this.materialesProhibidos = materialesProhibidos;
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
