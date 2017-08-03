package org.myFirstHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.myFirstHibernate.dto.UserDetails;;

public class MainHibernate {

	public static void main(String[] args) {
		
		UserDetails user = new UserDetails();
		user.setUserID(4);
		user.setUsername("Henok");
		
		SessionFactory sessionFac = new Configuration().configure().buildSessionFactory();
		Session session = sessionFac.openSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
	}

}
