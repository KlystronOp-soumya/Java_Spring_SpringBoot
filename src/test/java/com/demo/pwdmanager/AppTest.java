package com.demo.pwdmanager;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.demo.pwdmanager.exceptions.PasswordManagerException;

/**
 * Unit test for simple App.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AppTest {
	/**
	 * Rigorous Test :-)
	 * 
	 * 
	 */

	@Test
	public void shouldAnswerWithTrue() {
		assertTrue(true);
		// System.out.println(System.getenv("USERNAME"));
	}

	@Test
	public void appDataFolderAccess_test() {
		System.out.println(System.getenv("LOCALAPPDATA")); // environment variable for the current user
		String localAppDataPath = System.getenv("LOCALAPPDATA");

		assertNotNull(localAppDataPath);

	}

	@Test
	public void createKeyStore_test() throws IOException, PasswordManagerException {
		/*
		 * System.out.println(configPath); File f = new
		 * File(configPath.concat("\\PasswordManager\\"));// .concat(System.getenv("
		 * USERNAME").concat(".keystore"))); if (!f.exists()) assertTrue(f.mkdir());
		 * String ks =
		 * f.getAbsolutePath().concat("\\").concat(System.getenv("USERNAME").concat(".
		 * keystore")); f = new File(ks); if (!f.exists()) { f.createNewFile(); } //
		 * assertTrue(new File(ks).createNewFile()); assertTrue(new File(ks).exists());
		 */

		assertTrue("Key store was not created", keyStoreHandler.createKeyStore("soumya", "soumya"));

	}

	/*
	 * @Test public void hibernateCreateTable_test() {
	 * 
	 * // SessionFactory sessionFactory = HibernateUtils.getSessionFactory(); //
	 * Session session = sessionFactory.openSession(); EntityManagerFactory
	 * entityManagerFactory = session.getEntityManagerFactory(); EntityManager
	 * entityManager = entityManagerFactory.createEntityManager();
	 * org.hibernate.Transaction transaction = session.beginTransaction();
	 * UserEntity userEntity = new UserEntity(); UserEntity userEntity2 = new
	 * UserEntity(); try { userEntity.setUserId("soumya");
	 * userEntity.setEncodedPassword("dev"); // transaction.begin();
	 * session.save(userEntity); // transaction.commit(); //
	 * userEntity.setUserId("deep"); // userEntity.setEncodedPassword("qa"); //
	 * entityManager.persist(userEntity);
	 * 
	 * userEntity2.setUserId("Dev"); userEntity2.setEncodedPassword("QA");
	 * session.save(userEntity2); transaction.commit(); Query query =
	 * entityManager.createNativeQuery("SELECT * FROM USERS_PWD", UserEntity.class);
	 * List<UserEntity> userEntityList = query.getResultList();
	 * userEntityList.stream().forEach((each) ->
	 * System.out.println(each.getUserId())); // org.hibernate.Transaction
	 * transaction = session.beginTransaction(); assertTrue("the list empty",
	 * !userEntityList.isEmpty()); query = entityManager.
	 * createNativeQuery("SELECT * FROM USERS_PWD WHERE USER_ID=:userId",
	 * UserEntity.class); query.setParameter("userId", "Dev"); UserEntity u =
	 * (UserEntity) query.getSingleResult(); assertNotNull("Null entity", u);
	 * assertEquals("Not matching password", "QA", u.getEncodedPassword());
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } finally { //
	 * transaction.commit(); // entityManager.flush(); session.close(); }
	 * 
	 * }
	 */
	/*
	 * @Test public void hibernateEntityManager_test() { try {
	 * 
	 * EntityManager entityManager =
	 * session.getEntityManagerFactory().createEntityManager(); UserEntity
	 * userEntity = new UserEntity(); userEntity.setUserId("paul");
	 * userEntity.setEncodedPassword("UAT"); entityManager.getTransaction().begin();
	 * entityManager.persist(userEntity); entityManager.getTransaction().commit();
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } }
	 */

	/*
	 * @Test public void databasePropertyLoad_Test() { Properties props =
	 * PropertyUtils.loadDatabaseProps(); assertNotNull("Properties are null",
	 * props); assertNotNull("driver is null", props.get("db.driver"));
	 * assertNotNull("url is null", props.get("db.url"));
	 * assertNotNull("user is null", props.get("db.user"));
	 * assertNotNull("Dialect is null", props.get("db.dialect")); }
	 * 
	 * @Test public void hikariDataSource_Test() { DataSource dataSource =
	 * DataSourceUtils.gethikariDataSource(); assertNotNull("DataSource is null",
	 * dataSource); DataSourceUtils.closeDataSource(); }
	 * 
	 * @Test public void sessionFactory_Test() {
	 * 
	 * }
	 * 
	 * @Test public void databaseCreation_Test() throws SQLException { final String
	 * query = "CREATE TABLE IF NOT EXISTS PASSWORD_ADMIN(" +
	 * " id int primary key AUTO_INCREMENT , " + " user_id varchar(40) not null , "
	 * + " pwd varchar(255) not null )";
	 * 
	 * DataSource dataSource = DataSourceUtils.gethikariDataSource();
	 * assertNotNull("DataSource is null", dataSource); Connection conn =
	 * dataSource.getConnection(); PreparedStatement pstmt =
	 * conn.prepareStatement(query); int res = pstmt.executeUpdate();
	 * 
	 * assertTrue(res == 0 ? true : false); conn.close();
	 * DataSourceUtils.closeDataSource(); }
	 * 
	 * @Test public void databaseInsertion_Test() throws SQLException { final String
	 * query = "INSERT INTO PASSWORD_ADMIN (user_id , pwd)" +
	 * " VALUES ( 'abc' , '123' )";
	 * 
	 * DataSource dataSource = DataSourceUtils.gethikariDataSource();
	 * assertNotNull("DataSource is null", dataSource); Connection conn =
	 * dataSource.getConnection(); PreparedStatement pstmt =
	 * conn.prepareStatement(query); int res = pstmt.executeUpdate();
	 * 
	 * assertTrue(res == 1 ? true : false); conn.close();
	 * DataSourceUtils.closeDataSource(); }
	 * 
	 * @Test public void databaseRead_test() throws SQLException { final String
	 * query = "SELECT * FROM PASSWORD_ADMIN";
	 * 
	 * DataSource dataSource = DataSourceUtils.gethikariDataSource();
	 * assertNotNull("DataSource is null", dataSource); Connection conn =
	 * dataSource.getConnection(); PreparedStatement pstmt =
	 * conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,
	 * ResultSet.CONCUR_READ_ONLY); ResultSet rs = pstmt.executeQuery(); int rowNum
	 * = rs.last() ? rs.getRow() : 0; rs.beforeFirst(); if (rs.next()) {
	 * assertEquals("abc", rs.getString("user_id")); } conn.close();
	 * DataSourceUtils.closeDataSource(); }
	 * 
	 * @Test public void databaseDelete_test() throws SQLException { final String
	 * query = "DELETE  FROM PASSWORD_ADMIN"; DataSource dataSource =
	 * DataSourceUtils.gethikariDataSource(); assertNotNull(dataSource);
	 * 
	 * Connection connection = dataSource.getConnection();
	 * 
	 * PreparedStatement pstmt = connection.prepareStatement(query); int res =
	 * pstmt.executeUpdate();
	 * 
	 * assertTrue(res > 0); connection.close(); DataSourceUtils.closeDataSource(); }
	 * 
	 * @Test public void passwordEncode_test() { final String password1 = "s13608";
	 * final String password2 = "s13608"; final String password3 = "s13608";
	 * BcryptUtils encryptUtils = new BcryptUtils(); String encodedPwd1 =
	 * encryptUtils.getEncodedPwd(password1);
	 * 
	 * boolean isDecodedPwd = encryptUtils.decodePwd(password1, encodedPwd1);
	 * assertTrue(isDecodedPwd); }
	 */

}
