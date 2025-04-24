package com.demo.genericapp.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class LOVModel implements Serializable {

	private static final long serialVersionUID = -6233818905593963007L;

	private String code;
	private String desc;
	private String name;
	private Date date;

}
