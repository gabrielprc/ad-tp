package model.persistence;

import model.impl.viajes.Viaje;

import org.hibernate.Session;

public class ViajeDAO extends GenericDAO<Viaje>{

	private ViajeDAO() {
		super();
	}
	
	@Override
	public GenericDAO<Viaje> getInstance() {
		if (instance == null)
			instance = new ViajeDAO();
		return instance;
	}

	@Override
	public Viaje get(Integer id) {
		Session session = sf.openSession();
		session.beginTransaction();
		Viaje viaje = (Viaje) session.get(Viaje.class, id);
		session.close();
		return viaje;
	}

}
