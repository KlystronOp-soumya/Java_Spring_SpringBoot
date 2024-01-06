package com.demo.pwdmanager;

import com.demo.pwdmanager.service.RunHandler;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		// TODO: upon startup create a different thread to check the database and
		// keystore
		// TODO: if the new user registration then delete existing keystore
		// and delete
		// the db file
		// TODO: get the user password as the public key
		// TODO: use the user raw password to generate a secret key
		// TODO: store the secret key in a KeyStore keep the user id as the alias
		// TODO: use this secret key to decrypt the different entries

		RunHandler runHandler = new RunHandler();
		runHandler.run();

		/*
		 * Get the keystore password from the Database Load the ks get the secret key
		 * generate public key encrypt the entries store it into the database
		 * 
		 */
		// SessionFactory sessionFactory = HibernateUtils.getSessionFactory() ;
		// Session session = sessionFactory.openSession() ;
		// EntityManager entityManager =
		// session.getEntityManagerFactory().createEntityManager() ;

		// org.hibernate.Transaction transaction = session.beginTransaction() ;
		// session.close();

	}
}
