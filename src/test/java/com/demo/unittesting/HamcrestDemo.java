package com.demo.unittesting;

import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class HamcrestDemo {

	@Test
	public void test_list() {
		List<Integer> numbers = Arrays.asList(1, 3, 4, 5);

		// use Hamcrest
		assertThat(numbers, hasSize(4));
		assertThat(numbers, hasItem(1));
		MatcherAssert.assertThat(numbers, everyItem(Matchers.greaterThan(0)));

		// to assert the string
		assertThat("", Matchers.isEmptyString());
		assertThat("ABCDE", Matchers.startsWith("AB"));
	}

}
