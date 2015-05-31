package model.persistence;

import model.impl.cargas.Carga;
import model.impl.clientes.Cliente;
import model.impl.clientes.CobroParcial;
import model.impl.clientes.CuentaCorriente;
import model.impl.clientes.Empresa;
import model.impl.clientes.Factura;
import model.impl.clientes.Pago;
import model.impl.clientes.Particular;
import model.impl.clientes.Receptor;
import model.impl.misc.Coordenada;
import model.impl.misc.Tamano;
import model.impl.misc.Ubicacion;
import model.impl.personal.Empleado;
import model.impl.productos.ItemProducto;
import model.impl.productos.Producto;
import model.impl.sucursales.Sucursal;
import model.impl.vehiculos.Vehiculo;
import model.impl.vehiculos.VehiculoExterno;
import model.impl.vehiculos.VehiculoLocal;
import model.impl.viajes.CompaniaSeguro;
import model.impl.viajes.ParadaIntermedia;
import model.impl.viajes.Proveedor;
import model.impl.viajes.Seguro;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil
{
    private static final SessionFactory sessionFactory;
    static
    {
        try
        {
        	 AnnotationConfiguration config = new AnnotationConfiguration();
        	 config.addAnnotatedClass(CompaniaSeguro.class);
        	 config.addAnnotatedClass(ParadaIntermedia.class);
        	 config.addAnnotatedClass(Proveedor.class);
        	 config.addAnnotatedClass(Seguro.class);
        	 config.addAnnotatedClass(Ubicacion.class);
        	 config.addAnnotatedClass(Coordenada.class);
        	 config.addAnnotatedClass(Vehiculo.class);
        	 config.addAnnotatedClass(VehiculoLocal.class);
        	 config.addAnnotatedClass(VehiculoExterno.class);
        	 config.addAnnotatedClass(Tamano.class);
        	 config.addAnnotatedClass(Producto.class);
        	 config.addAnnotatedClass(Empresa.class);
        	 config.addAnnotatedClass(CuentaCorriente.class);
        	 config.addAnnotatedClass(Cliente.class);
        	 config.addAnnotatedClass(Receptor.class);
        	 config.addAnnotatedClass(Particular.class);
        	 config.addAnnotatedClass(Carga.class);
        	 config.addAnnotatedClass(ItemProducto.class);
        	 config.addAnnotatedClass(Factura.class);
        	 config.addAnnotatedClass(CobroParcial.class);
        	 config.addAnnotatedClass(Sucursal.class);
        	 config.addAnnotatedClass(Empleado.class);
        	 config.addAnnotatedClass(Pago.class);
             sessionFactory = config.buildSessionFactory();
        }
        catch (Throwable ex)
        {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
  
    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }
}
