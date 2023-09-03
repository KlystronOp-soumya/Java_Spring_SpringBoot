package com.demo.cartapp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.json.JSONArray;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.demo.cartapp.entity.Cart;
import com.demo.cartapp.entity.Category;
import com.demo.cartapp.entity.Product;
import com.demo.cartapp.entity.User;
import com.demo.cartapp.repo.CartProductRepo;
import com.demo.cartapp.repo.CartRepo;
import com.demo.cartapp.repo.CategoryRepo;
import com.demo.cartapp.repo.ProductRepo;
import com.demo.cartapp.repo.UserRepo;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class WingsT4CartAppApplicationTests {
	
	
	
	@PersistenceContext
	private EntityManager entityManager ;
	
	@Autowired
	private JdbcTemplate jdbcTemplate ;
	
	private CartRepo cartRepo ;
	private CategoryRepo categoryRepo ;
	private ProductRepo productRepo ;
	private UserRepo userRepo ;
	private CartProductRepo cartProductRepo ;
	/*
	@Test
	@Transactional(readOnly = true  )
	void checkDataEntry_User_Test()
	{
		int count =  Long.valueOf(this.userRepo.count()).intValue() ;
		assertThat(count).isEqualTo(6) ;
	}
	
	@Test
	void checkEntityManager_Nullable_Test()
	{
		assertThat(this.entityManager).isNotNull();
	}
	
	@Test
	@Transactional(readOnly = true)
	void checkDataEntry_Category_Test()
	{
		Query query = this.entityManager.createNativeQuery("SELECT COUNT(*) FROM CATEGORY") ;
		
		int actualCount= Integer.valueOf(query.getSingleResult().toString()) ;
		
		assertThat(actualCount).isEqualTo(6);
	}
	
	@Test
	void checkJdbcTemplate_Test() throws SQLException
	{
		assertThat(this.jdbcTemplate.getDataSource().getConnection()).isNotNull() ;
	}
	
	@Test
	@Transactional(readOnly = true)
	void checkTransaction_JdbcTemplate_Test1()
	{
		String query = "INSERT INTO category (category_id,category_name) VALUES (6 , 'SHOES')" ;
		int updatedRowCount = this.jdbcTemplate.update(query) ;
		
		assertThat(updatedRowCount).isEqualTo(1) ;
		
	}
	
	@Test
	@Transactional
	void checkTransaction_entitManager_Test()
	{
		Query query = this.entityManager.createNativeQuery("DELETE FROM CATEGORY WHERE CATEGORY_ID= :categoryId") ;
		query.setParameter("categoryId", 6) ;
	    Integer wasDeleted =	query.executeUpdate() ;
	    
	    assertThat(wasDeleted).isEqualTo(1) ;
	  
	}*/
	
	//Tests
	@Test
	@Order(1)
	public void dbCategoryDefaultData_test1() throws Exception
	{
		
		String categories[]= {"Fashion" , "Electronics" , "Books" , "Groceries" , "Medicines"} ;
		List<Category> categoryList = this.categoryRepo.findAll() ;
		
		for(ListIterator<Category> iterator = categoryList.listIterator() ; iterator.hasNext() ; )
		{
			assertEquals(categories[iterator.nextIndex()], iterator.next().getCategoryName()) ;
		}
	}
	
	@Test
	@Order(2)
	public void dbUserDefaultData_test2() throws Exception
	{
		String users[] = {"jack" , "bob" , "apple" , "glaxo"} ;
		List<User> userList = this.userRepo.findAll() ;
		
		for(ListIterator<User> iterator = userList.listIterator() ; iterator.hasNext() ;)
		{
			assertEquals(users[iterator.nextIndex()], iterator.next().getUserName());
		}
		
	}
	
	@Test
	@Order(3)
	public void dbProductDefaultData_test3() throws Exception
	{
		String[] products = {"Apple Ipad 10.2" , "Crocin Tablet"} ;
		String[]  prices = {"29190" , "10"} ;
		
		List<Product> productList = this.productRepo.findAll() ;
		assertEquals(2, productList.size());
		for(ListIterator<Product> iterator = productList.listIterator() ; iterator.hasNext() ; )
		{
			assertEquals(products[iterator.nextIndex()], iterator.next().getProductName());
			assertEquals(prices[iterator.nextIndex()], String.valueOf(Math.round(iterator.next().getPrice())));
		}
	}
	
	@Test
	@Order(4)
	public void updateUserInUserDB_test4()
	{
		User user = this.userRepo.findById(1).get() ;
		assertThat("jack").isEqualTo(user.getUserName()) ;
		user.setUserName("jackie");
		this.userRepo.save(user) ;
	}
	
	@Test
	@Order(5)
	public void checkUpdatedDataInUSerDB_test5()
	{
		String users[] = {"jackie" , "bob" , "apple" , "glaxo"} ;
		List<User> userList = this.userRepo.findAll() ;
		
		for(ListIterator<User> iterator = userList.listIterator() ; iterator.hasNext() ;)
		{
			assertEquals(users[iterator.nextIndex()], iterator.next().getUserName());
		}
	}
	
	@Test
	@Order(6)
	public void compareUserAndCartOwner_test6()
	{
		Cart c = this.cartRepo.findById(1).get() ;
		User u = this.userRepo.findById(1).get() ;
		
		assertThat(c).isNotNull() ;
		assertThat(u).isNotNull() ;
		
		assertEquals(u.getUserName(), c.getUser().getUserName());
		assertThat(u.getRoles().toString().contains("CONSUMER")).isTrue() ;
		
	}
	
	@Autowired
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Autowired
	public void setCartRepo(CartRepo cartRepo) {
		this.cartRepo = cartRepo;
	}

	@Autowired
	public void setCategoryRepo(CategoryRepo categoryRepo) {
		this.categoryRepo = categoryRepo;
	}
	
	@Autowired
	public void setProductRepo(ProductRepo productRepo) {
		this.productRepo = productRepo;
	}
	
	@Autowired
	public void setUserRepo(UserRepo userRepo) {
		this.userRepo = userRepo;
	}
	
	@Autowired
	public void setCartProductRepo(CartProductRepo cartProductRepo) {
		this.cartProductRepo = cartProductRepo;
	}
	
	

	
}
