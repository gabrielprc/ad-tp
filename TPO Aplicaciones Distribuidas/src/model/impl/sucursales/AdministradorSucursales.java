package model.impl.sucursales;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import model.impl.cargas.AdministradorCargas;
import model.impl.cargas.Carga;
import model.impl.clientes.Cliente;
import model.impl.misc.Ubicacion;
import model.impl.personal.Empleado;
import model.impl.viajes.AdministradorViajes;
import model.impl.viajes.Viaje;

public class AdministradorSucursales {
	private static AdministradorSucursales instance;	
	private List<Sucursal> sucursales;
	private List<DistanciaEntreSucursales> distancias;

	
	public static AdministradorSucursales getInstance() {
		if (instance == null)
			instance = new AdministradorSucursales();
		return instance;
	}
	
	private AdministradorSucursales() {
		this.sucursales = new ArrayList<Sucursal>();
		this.distancias = new ArrayList<DistanciaEntreSucursales>();
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
	
	public Sucursal obtenerSucursalCercana(Ubicacion ubicacion) {
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
	
	public void asignarCargaASucursal(int codigoSucursal, Carga carga) throws Exception{
		Sucursal sucursal = obtenerSucursal(codigoSucursal);
		if (sucursal != null){
			Cliente cliente = carga.getCliente();
			if (cliente != null){
				if (!sucursal.getDeposito().existeCarga(carga.getCodigo())){
					if (!AdministradorCargas.getInstance().tieneMaterialesProhibidos(carga)){
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
	
	public Date estimarLlegada(Sucursal origen,	Sucursal destino) {
		Date partida = new Date();

		Float distancia = null;

		for (Viaje v : AdministradorViajes.getInstance().getViajes()) {
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
	
	public Sucursal obtenerSucursal(Integer numero) {
		for (Sucursal s : sucursales)
			if (s.getNumero() == numero)
				return s;
		return null;
	}
	
	public Ubicacion obtenerUbicacion(int codigoUbicacion) {
		for (Sucursal sucursal : sucursales) {
			if (sucursal.getUbicacion().getCodigo() == codigoUbicacion) {
				return sucursal.getUbicacion();
			}
		}
		return null;
	}
	
	public Empleado obtenerEmpleado(String cuit) {

		for (Sucursal s : sucursales)
			for (Empleado e : s.getEmpleados())
				if (e.getCuit().equals(cuit))
					return e;
		return null;
	}

	
	public List<Sucursal> getSucursales() {
		return sucursales;
	}

	public void setSucursales(List<Sucursal> sucursales) {
		this.sucursales = sucursales;
	}

	public List<DistanciaEntreSucursales> getDistancias() {
		return distancias;
	}

	public void setDistancias(List<DistanciaEntreSucursales> distancias) {
		this.distancias = distancias;
	}
}
