package model.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import model.impl.cargas.Carga;
import model.impl.clientes.Cliente;
import model.impl.clientes.Cobro;
import model.impl.clientes.CuentaCorriente;
import model.impl.clientes.Empresa;
import model.impl.clientes.Pago;
import model.impl.clientes.Particular;
import model.impl.misc.Tamano;
import model.impl.misc.Ubicacion;
import model.impl.personal.Empleado;
import model.impl.productos.CondicionEspecial;
import model.impl.productos.ItemProducto;
import model.impl.productos.Producto;
import model.impl.sucursales.DistanciaEntreSucursales;
import model.impl.sucursales.Sucursal;
import model.impl.vehiculos.EstrategiaMantenimiento;
import model.impl.vehiculos.PlanMantenimiento;
import model.impl.vehiculos.TipoVehiculo;
import model.impl.vehiculos.Vehiculo;
import model.impl.vehiculos.VehiculoExterno;
import model.impl.vehiculos.VehiculoLocal;
import model.impl.viajes.CompaniaSeguro;
import model.impl.viajes.ParadaIntermedia;
import model.impl.viajes.Proveedor;
import model.impl.viajes.Seguro;
import model.impl.viajes.Viaje;

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
	private List<Viaje> viajesExternos;

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
		viajesExternos = new ArrayList<Viaje>();
	}


	/* ABM CARGAS */
	//	public void altaCarga(Carga carga, Sucursal sucursal) {
	//		sucursal.getDeposito().almacenarCarga(carga);
	//		
	//		Viaje mejorViaje = obtenerMejorViaje(carga);
	//		
	//		if (mejorViaje != null) {
	//			mejorViaje.agregarCarga(carga);
	//		}		
	//		
	//		else {
	//			//TODO implementar correctamente la l�gica de asignaci�n de veh��ulo y viaje
	//			Vehiculo vehiculo = null;
	//			
	//			Calendar cal = Calendar.getInstance();
	//			cal.add(Calendar.HOUR, 6);
	//			Date salida = cal.getTime();
	//			Date llegada = estimarLlegada(carga.getProductos(), sucursal, obtenerSucursalCercana(carga.getDestino()));		
	//
	//			
	//			for (Vehiculo v : sucursal.getVehiculos()) {
	//				if (estaDisponibleVehiculo(v, salida, llegada)) {
	//					vehiculo = v;
	//				}
	//			}
	//			altaViaje(Arrays.asList(carga), null, vehiculo, salida, null, null);
	//		}
	//	}

	public void altaCarga(Carga carga, Sucursal sucursal) {
		sucursal.getDeposito().almacenarCarga(carga);

		Date fechaEstimadaLlegada = estimarLlegada(sucursal, obtenerSucursalCercana(carga.getDestino()));
		Viaje mejorViaje = obtenerMejorViaje(carga);

		if(mejorViaje != null){
			for(ParadaIntermedia pi : mejorViaje.getParadasIntermedias()){
				if(pi.getUbicacion().equals(sucursal.getUbicacion())){
					if (pi.getLlegada().before(fechaEstimadaLlegada)){
						mejorViaje.agregarCarga(carga);
						return;
					}
				}
			}
		}

		Vehiculo vehiculo = null;
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR, 6);
		Date salida = cal.getTime();			
		for (Vehiculo v : sucursal.getVehiculos()) {
			if (estaDisponibleVehiculo(v, salida, fechaEstimadaLlegada)) {
				vehiculo = v;
			}
		}

		altaViaje(Arrays.asList(carga), null, vehiculo, salida, null, null);
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

	private void altaViaje(List<Carga> cargas, Seguro seguro, Vehiculo vehiculo, Date fechaSalida,
			List<CondicionEspecial> condicionesEspeciales, Vector<ParadaIntermedia> paradasIntermedias) {
		viajes.add(new Viaje(cargas, seguro, vehiculo, fechaSalida, condicionesEspeciales, paradasIntermedias));
	}

	public void actualizarViaje(Viaje viaje, Sucursal sucursal) {
		for (Iterator<Carga> iterator = viaje.getCargas().iterator(); iterator.hasNext();) {
			Carga carga = iterator.next();
			if (obtenerMejorViaje(carga) != null) {
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
		for (Iterator<Carga> iterator = sucursal.getDeposito().getCargas().iterator(); iterator.hasNext();) {
			Carga carga = iterator.next();
			Viaje mejorViaje = obtenerMejorViaje(carga);
			if (mejorViaje != null && mejorViaje.equals(viaje) && viaje.puedeTransportar(carga)) {
				viaje.agregarCarga(carga);
				iterator.remove();
			}
		}
	}

	public Viaje obtenerMejorViaje(Carga carga) {
		Sucursal sucursal = null;

		for (Sucursal suc : sucursales) {
			if (suc.getDeposito().existeCarga(carga.getCodigo())) { //cambie sucursal suc
				sucursal = suc;
				break;
			}
		}

		Viaje mejorViaje = null;

		for (Viaje viaje : viajes) {
			if (viaje.pasaPorSucursal(sucursal) && viaje.puedeTransportar(carga)) {
				if (mejorViaje == null
						|| (
								viaje.puedeTransportar(carga)
								&& viaje.obtenerLlegadaAParada(sucursal).before(mejorViaje.obtenerLlegadaAParada(sucursal))
								)) {
					mejorViaje = viaje;
				}
			}
		}
		return mejorViaje;
	}

	public void altaViajeExterno(List<Carga> cargas, Seguro seguro, Date fechaSalida, Date fechaLLegada, 

		Proveedor proveedor, TipoVehiculo tipoVehiculo, List<CondicionEspecial> condicionesEspeciales){

		Vehiculo vehiculo = null;
		for(Vehiculo v : proveedor.getVehiculos())
			if(v.getTipo().equals(tipoVehiculo))
				vehiculo = v;

		viajesExternos.add(new Viaje(cargas, seguro, vehiculo, fechaSalida, condicionesEspeciales, null));

	}

	public List<Proveedor> obtenerViajesDeProveedores(Date fechaSalida, Date fechaLLegada, TipoVehiculo tipoVehiculo){

		List<Proveedor> proveedores = new ArrayList<Proveedor>();

		for(Proveedor p : proveedores){
			for(VehiculoExterno ve : p.getVehiculos())
				if(ve.getTipo().equals(tipoVehiculo)){
					proveedores.add(p);
					break;
				}				
		}

		return proveedores;
	}

	/* ABM VEHICULOS */

	public void altaVehiculoLocal(Integer idSucursal, String patente, Tamano tamano, Float peso, Float tara, Float tarifa, TipoVehiculo tipo,
			PlanMantenimiento planMantenimiento, Date vencimientoGarantia){
		Sucursal s = obtenerSucursal(idSucursal);
		if (s != null){
			if (!s.existeVehiculo(patente)){
				s.agregarVehiculo(new VehiculoLocal(patente, tamano, peso, tara, tarifa, tipo, planMantenimiento, vencimientoGarantia));
			}
		}
	}

	public void altaVehiculoExterno(String cuitProveedor, String patente, Tamano tamano, Float peso, Float tara, Float tarifa, TipoVehiculo tipo){
		Proveedor p = obtenerProveedor(cuitProveedor);
		if (p != null){
			if (p.existeVehiculo(patente)){
				p.agregarVehiculo(new VehiculoExterno(patente, tamano, peso, tara, tarifa, tipo));
			}
		}
	}

	public void realizarMantenimientoVehiculo(Integer idSucursal, String patente, boolean esEspecifico){
		Sucursal s = obtenerSucursal(idSucursal);
		if (s != null){
			VehiculoLocal v = s.obtenerVehiculo(patente);
			if (v != null){
				v.realizarMantenimiento(esEspecifico);
			}
		}
	}

	/* OTROS */

	public boolean estaDisponibleVehiculo(Vehiculo vehiculo, Date fechaDesde, Date fechaHasta) {
		for (Viaje viaje : viajes) {
			if (viaje.getVehiculo().equals(vehiculo)) {
				if ((viaje.getFechaSalida().after(fechaDesde) && viaje.getFechaLlegada().before(fechaDesde))
						|| (viaje.getFechaSalida().before(fechaDesde) && viaje.getFechaLlegada().after(fechaHasta))
						|| (viaje.getFechaSalida().before(fechaDesde) && viaje.getFechaLlegada().before(fechaHasta))) {
					return false;
				}
			}
		}
		return true;
	}

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

	public Date estimarLlegada(Sucursal origen,	Sucursal destino) {
		Date partida = new Date();

		Float distancia = null;

		for (Viaje v : viajes) {
			if (v.getOrigen().equals(origen.getUbicacion()))
				if (v.getDestino().equals(destino.getUbicacion())) 
					return v.getFechaLlegada();
				else {
					Date llegada = v.existeLLegadaUbicacion(destino.getUbicacion());
					if (llegada != null)
						return llegada;
				}
		}

		distancia = calcularHorasEntreSucursales(origen, destino);

		int minutos = (int) (distancia % 1) * 60;
		int horas = (int) (distancia - distancia % 1);

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR, horas);
		cal.add(Calendar.MINUTE, minutos);

		return cal.getTime();
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

	public float calcularHorasEntreSucursales(Sucursal sucursalA, Sucursal sucursalB) {

		for (DistanciaEntreSucursales d : distancias)
			if (d.getSucursalA().getNumero() == sucursalA.getNumero()
			|| d.getSucursalB().getNumero() == sucursalB.getNumero())
				if (d.getSucursalB().getNumero() == sucursalB.getNumero()
				|| d.getSucursalB().getNumero() == sucursalA
				.getNumero())
					return d.getDuracionEnHoras();
		return 0;
	}

	public void determinarCostoViaje(Viaje v) {
		if (v == null)
			return;

		Calendar cal;

		if (v.getParadasIntermedias().size() == 0) {
			cal = Calendar.getInstance();
			Sucursal sucursalA = null, sucursalB = null;

			for (Sucursal s : sucursales) {
				if (v.getOrigen().equals(s.getUbicacion()))
					sucursalA = s;
				else if (v.getDestino().equals(s.getUbicacion()))
					sucursalB = s;
			}
			if (sucursalA == null || sucursalB == null)
				return;
			float costo = calcularHorasEntreSucursales(sucursalA, sucursalB);
			int horas = (int) costo;
			int minutos = (int) (60 * (costo - horas));

			cal.add(Calendar.HOUR, horas);
			cal.add(Calendar.MINUTE, minutos);
			v.setFechaLlegada(cal.getTime());
		} else if (v.getParadasIntermedias().size() > 0) {
			float horasDistancia = calcularHorasEntreSucursales(obtenerSucursalCercana(v.getOrigen()), obtenerSucursalCercana(v.getParadasIntermedias().firstElement().getUbicacion()));
			int horas = (int) horasDistancia;
			int minutos = (int) (60 * (horasDistancia - horas));

			cal = Calendar.getInstance();
			cal.add(Calendar.HOUR, horas);
			cal.add(Calendar.MINUTE, minutos);

			v.getParadasIntermedias().firstElement().setLlegada(cal.getTime());

			if (v.getParadasIntermedias().size() > 1) {
				for (int i = 1; i < v.getParadasIntermedias().size() - 1; i++) {
					Sucursal sucA = obtenerSucursalCercana(v.getParadasIntermedias().get(i - 1).getUbicacion());
					Sucursal sucB = obtenerSucursalCercana(v.getParadasIntermedias().get(i).getUbicacion());

					horasDistancia = calcularHorasEntreSucursales(sucA, sucB);
					horas = (int) horasDistancia;
					minutos = (int) (60 * (horasDistancia - horas));
					cal.add(Calendar.HOUR, horas);
					cal.add(Calendar.MINUTE, minutos);

					v.getParadasIntermedias().get(i).setLlegada(cal.getTime());
				}
			}

			Sucursal sucA = obtenerSucursalCercana(v.getParadasIntermedias().get(v.getParadasIntermedias().size() - 1).getUbicacion());
			Sucursal sucB = obtenerSucursalCercana(v.getDestino());

			horasDistancia = calcularHorasEntreSucursales(sucA, sucB);
			horas = (int) horasDistancia;
			minutos = (int) (60 * (horasDistancia - horas));

			cal.add(Calendar.HOUR, horas);
			cal.add(Calendar.MINUTE, minutos);

			v.setFechaLlegada(cal.getTime());
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

	//	Emplear obtenerSucursalCercana, que puede recibir por par�metro una ubicaci�n no necesariamente de sucursal
	//	public Sucursal obtenerSucursalPorUbicacion(Ubicacion u) {
	//		for (Sucursal s : sucursales)
	//			if (s.getUbicacion().equals(u))
	//				return s;
	//		return null;
	//	}

	public Proveedor obtenerProveedor(String cuit){
		for (Proveedor p : proveedores){
			if (p.getCuit().equals(cuit)){
				return p;
			}
		}
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
