package com.demo.pwdmanager.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Principal {

	private String userId;
	private String rawPwd;

	public Principal(String userId, String rawPwd) {
		super();
		this.userId = userId;
		this.rawPwd = rawPwd;
	}

}
