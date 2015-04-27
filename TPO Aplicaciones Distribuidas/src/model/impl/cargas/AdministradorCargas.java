package model.impl.cargas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import model.impl.productos.ItemProducto;
import model.impl.sucursales.AdministradorSucursales;
import model.impl.sucursales.Sucursal;
import model.impl.vehiculos.AdministradorVehiculos;
import model.impl.vehiculos.Vehiculo;
import model.impl.viajes.AdministradorViajes;
import model.impl.viajes.ParadaIntermedia;
import model.impl.viajes.Viaje;

public class AdministradorCargas {
	private static AdministradorCargas instance;
	private List<String> materialesProhibidos;
	
	
	public static AdministradorCargas getInstance() {
		if (instance == null) 
			instance = new AdministradorCargas();
		return instance;
	}
	
	private AdministradorCargas() {
		this.materialesProhibidos = new ArrayList<String>();
	}
	
	public void altaCarga(Carga carga, Sucursal sucursal) {
		AdministradorSucursales admSuc = AdministradorSucursales.getInstance();
		AdministradorViajes admVi = AdministradorViajes.getInstance();
		AdministradorVehiculos admVeh = AdministradorVehiculos.getInstance();
		
		sucursal.getDeposito().almacenarCarga(carga);

		Date fechaEstimadaLlegada = admSuc.estimarLlegada(sucursal, admSuc.obtenerSucursalCercana(carga.getDestino()));
		Viaje mejorViaje = admVi.obtenerMejorViaje(carga);

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
			if (admVeh.estaDisponibleVehiculo(v, salida, fechaEstimadaLlegada)) {
				vehiculo = v;
			}
		}

		admVi.altaViaje(Arrays.asList(carga), null, vehiculo, salida, null, null);
	}
	
	public boolean tieneMaterialesProhibidos(Carga carga){
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
	
	public List<String> getMaterialesProhibidos() {
		return materialesProhibidos;
	}

	public void setMaterialesProhibidos(List<String> materialesProhibidos) {
		this.materialesProhibidos = materialesProhibidos;
	}

}
