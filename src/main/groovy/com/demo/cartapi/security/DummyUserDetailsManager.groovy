package com.demo.cartapi.security

import org.springframework.context.annotation.Profile
import org.springframework.security.core.userdetails.User.UserBuilder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.provisioning.UserDetailsManager
import org.springframework.stereotype.Component

import com.demo.cartapi.service.util.LoggerUtil


import javax.sound.midi.MidiDevice.Info
@Component
@Profile("dev")
class DummyUserDetailsManager implements UserDetailsManager {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		LoggerUtil.info("Loading user by user name")
		
		UserDetails user = new DummyUser("sdas" , "kolkata@100")
		
		return user
	}

	@Override
	public void createUser(UserDetails user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(UserDetails user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(String username) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changePassword(String oldPassword, String newPassword) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean userExists(String username) {
		// TODO Auto-generated method stub
		return false;
	}
}
