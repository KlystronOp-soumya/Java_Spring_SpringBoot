package com.demo.unittesting;
//Test class to test the ItemRepo

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.repository.config.BootstrapMode;
import org.springframework.test.context.TestPropertySource;

import com.demo.unittesting.dao.ItemRepository;
import com.demo.unittesting.entities.Item;

// @RunWith(SpringRunner.class) -- this is not required with Junit 5
@DataJpaTest(bootstrapMode = BootstrapMode.DEFAULT, showSql = true)
@TestPropertySource(locations = { "classpath:application-test.properties" })
public class ItemRepositoryTest {

	@Autowired
	private transient ItemRepository itemRepository;

	@Test
	public void test_findAll() {
		List<Item> items = itemRepository.findAll();

		assertThat(items.size()).isEqualTo(4);

	}
}
