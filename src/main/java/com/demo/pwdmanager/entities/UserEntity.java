package com.demo.pwdmanager.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@javax.persistence.Entity
@javax.persistence.Table(name = "USERS_PWD")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "USER_ID", nullable = false, insertable = true, unique = true)
	private String userId;

	@Transient
	private transient String password;

	@Column(name = "ENCODED_PWD", nullable = false)
	private String encodedPassword;

}
