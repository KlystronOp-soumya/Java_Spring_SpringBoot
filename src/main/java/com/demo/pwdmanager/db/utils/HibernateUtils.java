package com.demo.pwdmanager.db.utils;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.demo.pwdmanager.entities.UserEntity;

public class HibernateUtils {
	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {

		if (sessionFactory == null) {
			try {
				Configuration configuration = new Configuration();
				Properties dbProperties = PropertyUtils.loadDatabaseProps();
				// Hibernate settings equivalent to hibernate.cfg.xml's properties
				Properties settings = new Properties();
				settings.put(Environment.DRIVER, dbProperties.get("db.driver"));
				settings.put(Environment.URL, dbProperties.get("db.url"));
				settings.put(Environment.USER, dbProperties.get("db.user"));
				settings.put(Environment.PASS, dbProperties.get("db.password"));
				settings.put(Environment.DIALECT, "org.hibernate.dialect.H2Dialect");
				settings.put(Environment.SHOW_SQL, "true");
				settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
				settings.put(Environment.HBM2DDL_AUTO, "update");
				settings.put(Environment.GENERATE_STATISTICS, true);
				settings.put(Environment.AUTOCOMMIT, "true");// hibernate.generate_statistics
				settings.put(Environment.PERSISTENCE_UNIT_NAME, "passwordManagerPersistentUnit");
				settings.put(Environment.JPA_PERSISTENCE_PROVIDER, "org.hibernate.jpa.HibernatePersistenceProvider");
				configuration.setProperties(settings);
				configuration.addPackage("com.demo.pwdmanager.entities");
				configuration.addAnnotatedClass(UserEntity.class);
				// configuration.addAnnotatedClass(Student.class);

				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();

				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {

			}
		}
		return sessionFactory;
	}
}
