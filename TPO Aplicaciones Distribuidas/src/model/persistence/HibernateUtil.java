package model.persistence;

import model.impl.misc.Coordenada;
import model.impl.misc.Tamano;
import model.impl.misc.Ubicacion;
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
//        	 config.addAnnotatedClass(Viaje.class);
//        	 config.addAnnotatedClass(Carga.class);
//        	 config.addAnnotatedClass(EstadoCarga.class);
//        	 config.addAnnotatedClass(TipoCarga.class);
//        	 config.addAnnotatedClass(Cliente.class);
//        	 config.addAnnotatedClass(Viaje.class);
        	 config.addAnnotatedClass(Ubicacion.class);
        	 config.addAnnotatedClass(Coordenada.class);
        	 config.addAnnotatedClass(Vehiculo.class);
        	 config.addAnnotatedClass(VehiculoLocal.class);
        	 config.addAnnotatedClass(VehiculoExterno.class);
        	 config.addAnnotatedClass(Tamano.class);
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
