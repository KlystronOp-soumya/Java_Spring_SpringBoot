package com.demo.genericapp.entity.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MPSBSearchResultDTO {

	private int id;
	private BigDecimal minPercent;
	private BigDecimal psRate;
	private char indicator;

}
