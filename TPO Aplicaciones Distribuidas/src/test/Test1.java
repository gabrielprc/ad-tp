package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.controllers.ControladorPrincipal;
import model.impl.Carga;
import model.impl.Cliente;
import model.impl.CondicionEspecial;
import model.impl.Coordenada;
import model.impl.EstadoCarga;
import model.impl.ItemProducto;
import model.impl.Producto;
import model.impl.Sucursal;
import model.impl.TipoCarga;
import model.impl.Ubicacion;
import model.impl.Viaje;

public class Test1 {

	public static void main(String[] args) {
		try{
			ControladorPrincipal con = ControladorPrincipal.getInstance();

			//dar de alta clientes
			for (int i = 0; i<5; i++)
				con.altaClienteEmpresa("codigoempresa" + i, "nombreempresa" + i);
			for (int i = 0; i<5; i++)
				con.altaClienteParticular("codigoparticular" + i, "dniparticular" + i, "nombreparticular" + i, "apellidoparticular" + i);

			con.bajaCliente("codigoempresa4");

			for (Cliente c : con.getClientes()){
				System.out.println(c.getCodigoUnico());
				System.out.println(c.getNombre());
			}

			//dar de alta sucursales
			for (int i = 0; i<5; i++)
				con.altaSucursal(i, "sucursal" + i);		

			for (Sucursal s : con.getSucursales()){
				System.out.println(s.getNumero());
				System.out.println(s.getNombre());
			}

			//materiales no transportables
			List<String> materialesNoTransportables = new ArrayList<String>();
			materialesNoTransportables.add("Bombas");
			materialesNoTransportables.add("Droga");
			con.setMaterialesProhibidos(materialesNoTransportables);

			//agrego un par de productos
			List<Producto> productosControlador = new ArrayList<Producto>();
			Producto producto1 = new Producto();
			producto1.setCodigoProducto(1);
			productosControlador.add(producto1);
			//producto1.setMaterial("Bombas");
			Producto producto2 = new Producto();
			producto2.setCodigoProducto(2);
			productosControlador.add(producto2);
			con.setProductos(productosControlador);

			//intento agregar una carga
			Ubicacion origen =  new Ubicacion (1,"a","a","a","a","a","a","a",new Coordenada(1,1));
			Ubicacion destino = new Ubicacion (2,"a","a","a","a","a","a","a",new Coordenada(1,1));
			//CargaView carga = new CargaView(1, TipoCarga.BARRIL, new Date(), new Date(), "codigoempresa1", "ayylmao", origen, destino, EstadoCarga.ENTREGADA);
			List<ItemProducto> productos = new ArrayList<ItemProducto>();
			productos.add(new ItemProducto(producto1, 10));
			productos.add(new ItemProducto(producto2, 40));
			

			//con.asignarCargaASucursal(1, carga);
			
			Carga carga1 = new Carga(1, TipoCarga.BARRIL, new Date(),
			new Date(), con.obtenerCliente("codigoempresa1"), "a",
			origen, destino, EstadoCarga.EN_DEPOSITO);
			carga1.setProductos(productos);
			
			con.asignarCargaASucursal(1, carga1);
			
			System.out.println();
			System.out.println("/* CARGASSS */");
			System.out.println();

			for (Carga c : con.obtenerSucursal(1).getDeposito().getCargas()){
				System.out.println(
						"codigo:" + c.getCodigo() + "\n" +
								"pais:" + c.getDestino().getPais() + "\n");
				for (ItemProducto ip : c.getProductos()){
					System.out.println(
							"codigoProducto:" + ip.getProducto().getCodigoProducto() + "\n" +
									"cantidad:" + ip.getCantidad() + "\n");
				}
			}
			
			System.out.println();
			System.out.println("/* VIAJES */");
			System.out.println();

			//creo un viaje
			List<CondicionEspecial> condicionesEspeciales = new ArrayList<CondicionEspecial>();
			condicionesEspeciales.add(CondicionEspecial.SEGURIDAD);
			condicionesEspeciales.add(CondicionEspecial.TEMPERATURA);
			con.altaViaje(1, con.obtenerSucursal(1).getDeposito().getCargas(), null, null, 
					new Date(), condicionesEspeciales, null);

			for (Viaje v : con.getViajes()){
				System.out.println(
						"codigoViaje:" + v.getCodigo() + "\n" +
						"condicion especial 0:" + v.getCondicionesEspeciales().get(0));
				for (Carga c : v.getCargas()){
					System.out.println(
							"codigo:" + c.getCodigo() + "\n" +
									"pais:" + c.getDestino().getPais() + "\n");
				}
			}
			
			/*
			Carga cg = new Carga(carga.getCodigo(), carga.getTipo(), carga.getFechaMaximaEntrega(),
			carga.getFechaProbableEntrega(), cliente, carga.getManifiesto(), origen,
			destino, carga.getEstadoCarga());
			 */
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
