package com.demo.pwdmanager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.hibernate.Session;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.demo.pwdmanager.db.utils.HibernateUtils;
import com.demo.pwdmanager.entities.UserEntity;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HibtransacTestSuite {

	private static Session session;
	private static EntityManager entityManager;

	@Before
	public void initializeSession() {
		session = HibernateUtils.getSessionFactory().openSession();
		EntityManagerFactory entityManagerFactory = session.getEntityManagerFactory();
		entityManager = entityManagerFactory.createEntityManager();
	}

	@AfterClass
	public static void tearDown() {
		session.clear();
		session.close();
	}

	@Test
	public void UserEntityCreateData_test() {
		UserEntity u1 = new UserEntity();
		UserEntity u2 = new UserEntity();
		UserEntity u3 = new UserEntity();
		try {
			u1.setUserId("Jhon");
			u1.setEncodedPassword("Doe");
			u2.setUserId("Jhonny");
			u2.setEncodedPassword("Dawn");
			u3.setUserId("paul");
			u3.setEncodedPassword("UAT");
			session.beginTransaction();
			session.save(u1);
			session.save(u2);
			session.save(u3);
			session.getTransaction().commit();
			// Query query = session.createNativeQuery("SELECT * FROM USERS_PWD WHERE
			// USER_ID= :userId", UserEntity.class);
			// query.setParameter("userId", "Jhonny");
			Query query = session.createNativeQuery("SELECT * FROM USERS_PWD", UserEntity.class);
			List<UserEntity> fetchedUserEntityList = query.getResultList();
			assertTrue("No rows were fetched", fetchedUserEntityList.size() == 3);
			assertEquals("User ID is not matching", "Jhon", fetchedUserEntityList.get(0).getUserId());
			assertEquals("User ID is not matching", "Jhonny", fetchedUserEntityList.get(1).getUserId());
			assertEquals("User ID is not matching", "paul", fetchedUserEntityList.get(2).getUserId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void UserEntityFetchData_test() {
		try {
			// entityManager.getTransaction().begin();
			Query query = entityManager.createNativeQuery("SELECT * FROM USERS_PWD", UserEntity.class);
			List<UserEntity> userEntityList = query.getResultList();
			userEntityList.stream()
					.forEach((each) -> System.out.println(each.getUserId() + " " + each.getEncodedPassword()));
			assertTrue("the list empty", !userEntityList.isEmpty());
			assertEquals("There are no data", 2, userEntityList.size());
			// entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void UserEntityUpdateData_test() {
		try {
			Query query = entityManager
					.createNativeQuery("UPDATE USERS_PWD SET USER_ID=:userId WHERE USER_ID=:userIdToUpdt");
			query.setParameter("userId", "Jhon");
			query.setParameter("userIdToUpdt", "Jhonny13");
			entityManager.getTransaction().begin();
			int res = query.executeUpdate();
			entityManager.getTransaction().commit();
			assertTrue("Could not update users", res > 0);
			UserEntityFetchData_test();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void UserEntityDeleteData_test() {
		try {
			Query query = session.createNativeQuery("DELETE FROM USERS_PWD WHERE USER_ID= :userId");
			query.setParameter("userId", "paul");
			session.beginTransaction();
			int res = query.executeUpdate();
			session.getTransaction().commit();

			assertEquals("No data was deleted! Data was not present", 1, res);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void DeleteAll_UserEntityData_test() {
		try {
			Query query = entityManager.createNativeQuery("DELETE FROM USERS_PWD");
			entityManager.getTransaction().begin();
			;
			int res = query.executeUpdate();
			entityManager.getTransaction().commit();

			assertTrue("Could not delete all the entries", res > 0);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
