package model.persistence;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

public abstract class GenericDAO<T> {
	protected static GenericDAO instance;
	protected SessionFactory sf;
	
	protected GenericDAO () {
		this.sf = HibernateUtil.getSessionFactory();
	}
	
	public abstract GenericDAO getInstance();
	
	public abstract T get(Integer id);
	
	public void save(Object obj) {
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(obj);
		session.getTransaction().commit();
		session.close();
	};
	
}
