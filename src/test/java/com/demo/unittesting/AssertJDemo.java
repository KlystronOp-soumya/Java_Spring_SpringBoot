package com.demo.unittesting;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

public class AssertJDemo {

	@Test
	public void test_list() {
		List<Integer> numbers = Arrays.asList(1, 3, 4, 5);

		Predicate<Integer> checkGreatrThanZeroPredicate = (num) -> num > 0;
		assertThat(numbers).hasSize(4).contains(1, 3).allMatch(checkGreatrThanZeroPredicate);
		assertThat("").isEmpty();
		assertThat("ABCDE").hasSize(5).startsWith("AB").endsWith("DE");
	}

}
