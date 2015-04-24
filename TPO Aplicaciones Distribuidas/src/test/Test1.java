package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.controllers.ControladorPrincipal;
import model.impl.Carga;
import model.impl.Cliente;
import model.impl.EstadoCarga;
import model.impl.ItemProducto;
import model.impl.Producto;
import model.impl.Sucursal;
import model.impl.TipoCarga;
import model.views.CargaView;
import model.views.CoordenadaView;
import model.views.ItemProductoView;
import model.views.UbicacionView;

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
			con.setMaterialesNoTransportables(materialesNoTransportables);

			//agrego un par de productos
			List<Producto> productosControlador = new ArrayList<Producto>();
			Producto producto1 = new Producto();
			producto1.setCodigoProducto(1);
			productosControlador.add(producto1);
			Producto producto2 = new Producto();
			producto2.setCodigoProducto(2);
			productosControlador.add(producto2);
			con.setProductos(productosControlador);

			//intento agregar una carga
			UbicacionView origen =  new UbicacionView ("a","a","a","a","a","a","a",new CoordenadaView(1,1));
			UbicacionView destino = new UbicacionView ("a","a","a","a","a","a","a",new CoordenadaView(1,1));
			CargaView carga = new CargaView(1, TipoCarga.BARRIL, new Date(), new Date(), "codigoempresa1", "ayylmao", origen, destino, EstadoCarga.ENTREGADA);
			List<ItemProductoView> productos = new ArrayList<ItemProductoView>();
			productos.add(new ItemProductoView(1, 10));
			productos.add(new ItemProductoView(2, 40));
			productos.add(new ItemProductoView(3, 5000));
			carga.setProductos(productos);

			con.asignarCargaASucursal(2, carga);

			System.out.println();
			System.out.println("/* CARGASSS */");
			System.out.println();

			for (Carga c : con.obtenerSucursal(2).getDeposito().getCargas()){
				System.out.println(
						"codigo:" + c.getCodigo() + "\n" +
								"pais:" + c.getDestino().getPais() + "\n");
				for (ItemProducto ip : c.getProductos()){
					System.out.println(
							"codigoProducto:" + ip.getProducto().getCodigoProducto() + "\n" +
									"cantidad:" + ip.getCantidad() + "\n");
				}
			}

		}
		catch(Exception e){
			e.printStackTrace();
		}

	}

}
