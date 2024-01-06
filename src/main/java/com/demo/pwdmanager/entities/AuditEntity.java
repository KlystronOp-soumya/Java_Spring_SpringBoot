package com.demo.pwdmanager.entities;

import java.io.Serializable;

public class AuditEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String userId;

	private String date;

	private String description;

	private String passwordType;

}
