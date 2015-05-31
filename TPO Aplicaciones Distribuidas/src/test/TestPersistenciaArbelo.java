package test;

import java.util.Date;

import model.impl.cargas.Carga;
import model.impl.cargas.TipoCarga;
import model.impl.clientes.CuentaCorriente;
import model.impl.clientes.Empresa;
import model.impl.clientes.Particular;
import model.impl.clientes.Receptor;
import model.impl.misc.Coordenada;
import model.impl.misc.Tamano;
import model.impl.misc.Ubicacion;
import model.impl.productos.CondicionEspecial;
import model.impl.productos.Producto;
import model.impl.productos.TipoFragilidad;
import model.impl.productos.TipoTratamiento;
import model.impl.vehiculos.TipoVehiculo;
import model.impl.vehiculos.VehiculoExterno;
import model.impl.vehiculos.VehiculoLocal;
import model.impl.viajes.CompaniaSeguro;
import model.impl.viajes.ParadaIntermedia;
import model.impl.viajes.Proveedor;
import model.impl.viajes.Seguro;
import model.persistence.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class TestPersistenciaArbelo {
	private static SessionFactory sf = HibernateUtil.getSessionFactory();
	
	public static void main(String[] args) {
		crearCosas();
		//levantarProducto(4);
	}
	
	private static void levantarProducto(int i) {
		Session s = sf.openSession();
		Producto p = (Producto) s.get(Producto.class, i);
		for (CondicionEspecial ce : p.getCondicionesEspeciales()) {
			System.out.println(ce.getCondicion());
			System.out.println(ce.getFactorCondicion());
		}
		System.out.println(p.getFragilidad().getTipo());
	}

	private static void crearCosas() {
		Session s = sf.openSession();
		Ubicacion u = new Ubicacion("ayy", "lmao", "rio cuarto", "sobremonte", "982", "1", "A", new Coordenada(45, 55));
		Seguro seguro = new Seguro();		
		seguro.setNombre("seguro contra quesos");
		seguro.setTarifa(55f);
		seguro.setTipoCarga(TipoCarga.BOLSA); //devolvela
		Proveedor proveedor = new Proveedor("123", "alvaro");	
		CompaniaSeguro companiaSeguro = new CompaniaSeguro();
		companiaSeguro.setCuil("123");
		companiaSeguro.setNombre("compania1");		
		companiaSeguro.agregarSeguro(seguro);
		Carga carga = new Carga();
		ParadaIntermedia pi = new ParadaIntermedia();
		pi.setLlegada(new Date());
		pi.setChecked(true);
		VehiculoLocal vehiculoLocal = new VehiculoLocal();
		vehiculoLocal.setPatente("ABC-123");
		vehiculoLocal.setPeso(456f);
		vehiculoLocal.setTamano(new Tamano(5f,8f,9f));
		vehiculoLocal.setTara(5f);
		vehiculoLocal.setTarifa(8f);
		vehiculoLocal.setTipo(TipoVehiculo.CAMION_CON_TANQUE);
		vehiculoLocal.setVencimientoGarantia(new Date());		
		VehiculoExterno vehiculoExterno = new VehiculoExterno();
		vehiculoExterno.setPatente("DEF-456");
		vehiculoExterno.setPeso(456f);
		vehiculoExterno.setTamano(new Tamano(8f,45f,22f));
		vehiculoExterno.setTara(8f);
		vehiculoExterno.setTarifa(89f);
		vehiculoExterno.setTipo(TipoVehiculo.TRACTOR);
		vehiculoExterno.setProveedor(proveedor);
		Producto producto = new Producto();
		producto.setApilable(2);
		producto.setConsideraciones("no comprar");
		producto.setFragilidad(TipoFragilidad.NORMAL);
		producto.setManipulacion("no manipular");
		producto.setPeso(456f);
		producto.setMaterial("jajajajsacja");
		producto.setRefrigerada(true);
		producto.setTratamiento(TipoTratamiento.PELIGROSO);
		producto.setTamano(new Tamano(2f,8f,9f));
		producto.agregarCondicionEspecial(CondicionEspecial.SEGURIDAD);
		producto.setNombre("queso");
		CuentaCorriente cuentaCorriente = new CuentaCorriente();
		cuentaCorriente.setDepositoPrevio(true);
		cuentaCorriente.setMontoActual(45f);
		cuentaCorriente.setMontoAutorizado(456f);
		Empresa empresa = new Empresa();
		empresa.setCuentaCorriente(cuentaCorriente);
		empresa.setNombre("menem");
		empresa.setRegular(false);
		empresa.setCodigoUnico("ayy");
		empresa.agregarProducto(producto);
		Receptor receptor = new Receptor();
		receptor.setApellido("asfdas");
		receptor.setDni("asdas");
		receptor.setNombre("asdasd");
		receptor.setUbicacion(u);
		Particular particular = new Particular();
		particular.setApellido("calace");
		particular.setCodigoUnico("asasd");
		particular.setDni("213123");
		particular.setNombre("arbelo");
		particular.agregarReceptor(receptor);
		
		s.beginTransaction();
		s.save(u);
		s.save(seguro);
		s.save(proveedor);
		s.save(companiaSeguro);
		s.save(pi);
		s.save(vehiculoLocal);
		s.save(vehiculoExterno);
		s.save(producto);
		s.save(cuentaCorriente);
		s.save(empresa);
		s.save(receptor);
		s.save(particular);
		//s.save(carga);
		s.flush();
		s.getTransaction().commit();
		//s.close();
	}
	
/*
	   ____       ____     __ ____     __           .---.     ,---.    ,---.   ____        ,-----.     
	   .'  __ `.    \   \   /  /\   \   /  /          | ,_|     |    \  /    | .'  __ `.   .'  .-,  '.   
	  /   '  \  \    \  _. /  '  \  _. /  '         ,-./  )     |  ,  \/  ,  |/   '  \  \ / ,-.|  \ _ \  
	  |___|  /  |     _( )_ .'    _( )_ .'          \  '_ '`)   |  |\_   /|  ||___|  /  |;  \  '_ /  | : 
	     _.-`   | ___(_ o _)' ___(_ o _)'            > (_)  )   |  _( )_/ |  |   _.-`   ||  _`,/ \ _/  | 
	  .'   _    ||   |(_,_)' |   |(_,_)'            (  .  .-'   | (_ o _) |  |.'   _    |: (  '\_/ \   ; 
	  |  _( )_  ||   `-'  /  |   `-'  /              `-'`-'|___ |  (_,_)  |  ||  _( )_  | \ `"/  \  ) /  
	  \ (_ o _) / \      /    \      /                |        \|  |      |  |\ (_ o _) /  '. \_/``".'   
	   '.(_,_).'   `-..-'      `-..-'                 `--------`'--'      '--' '.(_,_).'     '-----'     
	                                                                                                     
*/
	
}
