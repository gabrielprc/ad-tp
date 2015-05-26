package model.persistence;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

public abstract class AbstractGenericDAO<T> {
	protected static AbstractGenericDAO instance;
	protected SessionFactory sf;
	
	protected AbstractGenericDAO () {
		this.sf = HibernateUtil.getSessionFactory();
	}
	
	public abstract AbstractGenericDAO getInstance();
	
	public abstract T get(Integer id);
	
	public void save(Object obj) {
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(obj);
		session.getTransaction().commit();
		session.close();
	};
	
}
