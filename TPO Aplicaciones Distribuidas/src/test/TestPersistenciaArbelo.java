package test;

import model.impl.misc.Coordenada;
import model.impl.misc.Ubicacion;
import model.persistence.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class TestPersistenciaArbelo {

	public static void main(String[] args) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session s = sf.openSession();
		Ubicacion u = new Ubicacion("argentina", "cordoba", "rio cuarto", "sobremonte", "982", "1", "A", new Coordenada(45, 55));
		s.beginTransaction();
		s.save(u);
		s.flush();
		s.getTransaction().commit();
		s.close();
	}

}
