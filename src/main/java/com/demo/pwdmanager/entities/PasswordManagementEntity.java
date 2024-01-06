package com.demo.pwdmanager.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PasswordManagementEntity {

	private Long id;

	private String url;

	private String siteName;

	private String Description;

	private String siteUserId;

	private String sitePassword; // will be encrypted using the keyStore password

}
