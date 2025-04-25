package com.demo.genericapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MPSBEntity {

	private int id;
	private String collectionOffice;
	private String persistencyBns;
	private String minPSBPercnt;
	private String unitPersistencyBns;
	private String minUnitPSBPercnt;

}
