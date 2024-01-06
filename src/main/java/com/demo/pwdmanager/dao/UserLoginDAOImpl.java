package com.demo.pwdmanager.dao;

import java.sql.Connection;

import javax.sql.DataSource;

import com.demo.pwdmanager.entities.Principal;
import com.demo.pwdmanager.entities.UserEntity;
import com.demo.pwdmanager.exceptions.PasswordManagerException;

public class UserLoginDAOImpl extends BaseDAO {

	private transient DataSource dataSource;

	public UserLoginDAOImpl(final DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public UserEntity verifyLogin(final Principal principal) throws PasswordManagerException {
		Connection connection = null;
		UserEntity userEntity = null;
		final String query = "SELECT USER_ID , ENCODED_PASSWORD FROM USERS_PWD WHERE USER_ID= ? ";
		try {
			connection = this.dataSource.getConnection();
			this.pstmt = connection.prepareStatement(query);
			this.pstmt.setString(1, principal.getUserId());
			this.rs = this.pstmt.executeQuery();

			if (rs.next()) {
				userEntity = new UserEntity();
				userEntity.setUserId(rs.getString(1));
			} else {
				throw new NullPointerException("Could not fetch user data");
			}
		} catch (Exception e) {
			throw new PasswordManagerException("User does not exist", e.getCause());
		} finally {
			// close the connection and rs
			super.clearResources();
		}
		return userEntity;
	}

	public boolean registerUser(final UserEntity registeringUser) {
		return false;

	}

}
