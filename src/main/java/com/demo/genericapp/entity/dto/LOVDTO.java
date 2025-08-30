package com.demo.genericapp.entity.dto;

import java.io.Serializable;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LOVDTO implements Serializable {

	private static final long serialVersionUID = -2683614037779028447L;

	private int code;
	private String desc;
	private String name;
	private Date dbDate;

}
