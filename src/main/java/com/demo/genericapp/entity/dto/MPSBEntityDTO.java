package com.demo.genericapp.entity.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class MPSBEntityDTO implements Serializable {

	private static final long serialVersionUID = -5014516109852956332L;

	private int id;
	private String collOff;
	private String PSB;
	private String minPSBPercnt;
	private String PUB;
	private String minPUBPercnt;

}
