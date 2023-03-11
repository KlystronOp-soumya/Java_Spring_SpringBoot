package com.demo.redispubsub.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
public class Product {
	
	private int id ;
	private String name ;
	private int qty ;
	private double price ;
	
}
