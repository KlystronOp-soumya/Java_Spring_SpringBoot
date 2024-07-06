package com.demo.unittesting;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

public class JSonPathDemo {

	@Test
	public void test_json() {

		String responseFromService = "[" + "{\"id\" : 1000 , \"name\" : \"Pencil\" , \"price\" : 5.00 } ,"
				+ "{\"id\" : 1001 , \"name\" : \"Eraser\" , \"price\" : 6.00 }" + "]";

		DocumentContext context = JsonPath.parse(responseFromService);
		int length = context.read("$.length()");
		assertThat(length).isEqualTo(2);
		List<Integer> ids = context.read("$..id");
		System.out.println(ids);
		assertThat(ids).hasSize(2);
		assertThat(ids).containsExactly(1000, 1001);

		System.out.println(context.read("$.[1]").toString());
		System.out.println(context.read("$[0:1]").toString());
		System.out.println(context.read("$.[?(@.name=='Eraser')]").toString());
		System.out.println(context.read("$.[?(@.quantity==1)]").toString());
	}
}
