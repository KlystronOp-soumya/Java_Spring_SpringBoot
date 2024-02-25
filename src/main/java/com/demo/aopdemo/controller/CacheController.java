package com.demo.aopdemo.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.aopdemo.entity.ResponseModel;

@RestController
@RequestMapping("/api/v1/cacheControl")
public class CacheController {
	
	@DeleteMapping(path = "/deleteAllCache")
	public ResponseEntity<Map<String, ResponseModel>> deleteAllCache()
	{
		// TODO Create a service class to evict all cache
		// TODO Create controller advice for any exception
		return null;
	}

}
