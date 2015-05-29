package br.ufsc.creche.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	@SuppressWarnings("deprecation")
	private static SessionFactory buildSessionFactory() {
		AnnotationConfiguration cfg = new AnnotationConfiguration();
		cfg.configure("hibernate.cfg.xml");
		return cfg.buildSessionFactory();
	}
	
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
}
