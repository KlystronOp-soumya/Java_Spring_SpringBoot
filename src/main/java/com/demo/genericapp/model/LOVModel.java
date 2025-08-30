package com.demo.genericapp.model;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LOVModel implements Serializable {

	private static final long serialVersionUID = -6233818905593963007L;

	private String code;
	private String desc;
	private String name;
	private Date date;

}
