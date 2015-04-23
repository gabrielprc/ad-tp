package model.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import model.impl.Cliente;
import model.impl.Cobro;
import model.impl.CompaniaSeguro;
import model.impl.CuentaCorriente;
import model.impl.DistanciaEntreSucursales;
import model.impl.Empleado;
import model.impl.Empresa;
import model.impl.EstrategiaMantenimiento;
import model.impl.ItemProducto;
import model.impl.Pago;
import model.impl.Particular;
import model.impl.Proveedor;
import model.impl.Sucursal;
import model.impl.Ubicacion;

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

	private List<DistanciaEntreSucursales> distancias;

	private ControladorPrincipal() {

		clientes = new ArrayList<Cliente>();
		sucursales = new ArrayList<Sucursal>();
		companiasSeguros = new ArrayList<CompaniaSeguro>();
		materialesNoTransportables = new ArrayList<String>();
		proveedores = new ArrayList<Proveedor>();
		pagos = new ArrayList<Pago>();
		cobros = new ArrayList<Cobro>();
		mantenimientos = new ArrayList<EstrategiaMantenimiento>();

	}
	
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
	
	public void altaSucursal(Integer numero, String nombre){
		
		sucursales.add(new Sucursal(numero, nombre));
	}

	public void altaEmpleado(String cuit, String dni, String nombre,
			String apellido, Date fechaNacimiento, Integer numeroSucursal) throws Exception {

		if (obtenerEmpleado(cuit) == null)
			obtenerSucursal(numeroSucursal).agregarEmpleado(
					new Empleado(cuit, dni, nombre, apellido, fechaNacimiento));
		else
			throw new Exception("Empleado con cuit "+cuit+" ya existe");

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

}
