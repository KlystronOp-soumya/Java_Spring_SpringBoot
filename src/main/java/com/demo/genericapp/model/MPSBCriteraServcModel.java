package com.demo.genericapp.model;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MPSBCriteraServcModel {

	private String propA;
	private String propB;
	private List<MPSBSearchResultModel> searchResultModels;

}
