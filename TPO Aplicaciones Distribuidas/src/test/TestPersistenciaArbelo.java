package test;

import java.util.Date;

import model.impl.cargas.Carga;
import model.impl.cargas.TipoCarga;
import model.impl.misc.Coordenada;
import model.impl.misc.Ubicacion;
import model.impl.viajes.CompaniaSeguro;
import model.impl.viajes.ParadaIntermedia;
import model.impl.viajes.Proveedor;
import model.impl.viajes.Seguro;
import model.persistence.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class TestPersistenciaArbelo {
	
	public static void main(String[] args) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
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
		
		s.beginTransaction();
		s.save(u);
		s.save(seguro);
		s.save(proveedor);
		s.save(companiaSeguro);
		s.save(pi);
		//s.save(carga);
		s.flush();
		s.getTransaction().commit();
		s.close();
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
