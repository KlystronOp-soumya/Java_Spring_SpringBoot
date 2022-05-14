package com.demo.SpringJPADemo;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

	@Before
	public void initialize() {
		// method to initialize db
	}

	@Test
	public void dummy_test() {
		assertTrue("This test will be failed", false);
	}

	@Test(expected = SQLException.class)
	public void unseccessful_DBConnectivity_test() {

	}

	@Test
	public void doCheckDBConnection_test() {

	}

}
