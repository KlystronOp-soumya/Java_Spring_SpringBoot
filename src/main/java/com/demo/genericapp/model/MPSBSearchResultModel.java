package com.demo.genericapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MPSBSearchResultModel {

	private int idChk;
	private String minPercent;
	private String psRate;
	private char indicator;
	private String gridKeys;

	public MPSBSearchResultModel(int id2) {
		this.idChk = id2;
	}
}
