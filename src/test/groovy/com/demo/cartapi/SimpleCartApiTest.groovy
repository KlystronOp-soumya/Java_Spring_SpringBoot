/**
 * 
 */
package com.demo.cartapi

import static org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest

/**
 * @author SOUMYADEEP PAUL
 *
 */

@SpringBootTest(properties=["PROFILE=dev"] , webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestMethodOrder(value = OrderAnnotation.class)
class SimpleCartApiTest {

	@Test
	void testDummy1() {
		fail("Not yet implemented")
	}
	
	@Test
	void testDummy2() {
		assertTrue(true, "This is ok")
	}

}
