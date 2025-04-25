package com.demo.genericapp.entity.dto;

import java.util.List;

import lombok.Data;

@Data
public class MPSBCriteriaServcDTO {

	private String propA;
	private String propB;
	private List<MPSBSearchResultDTO> mpsbSearchResultDTOs;
}
