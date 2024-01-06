package com.demo.pwdmanager.dao;

import java.util.Optional;

import com.demo.pwdmanager.entities.Principal;
import com.demo.pwdmanager.entities.UserEntity;

public interface UserLoginDAO {

	public Optional<UserEntity> registerUser(UserEntity registeringUserEntity);

	public boolean verifyLogin(Principal principal);

}
