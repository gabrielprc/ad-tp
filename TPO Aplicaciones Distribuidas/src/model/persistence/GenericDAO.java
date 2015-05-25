package model.persistence;

import org.hibernate.SessionFactory;

public abstract class GenericDAO<T> {
	protected static GenericDAO instance;
	protected SessionFactory sf;
	
	private GenericDAO() {
		this.sf = HibernateUtil.getSessionFactory();
	}
	
	public abstract GenericDAO getInstance();
	
	public abstract T get(Integer id);
	
	public abstract void persist(Object obj);
	
}
