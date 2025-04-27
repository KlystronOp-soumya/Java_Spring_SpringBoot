package com.demo.genericapp.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.demo.genericapp.entity.MPSBEntity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MPSBCriteraServcModel {

	private String propertA;
	private String propertB;
	private List<MPSBSearchResultModel> searchResultModels;

	private String propC;
	private char propD;
	private BigDecimal rate;
	private Date propDate;

	private MPSBEntity mpsbEntity;

	private int id;
	private String collOff;
	private String psb;
	private String minPSBPercnt;
	private String pub;
	private String minUnitPSBPercnt;

}
