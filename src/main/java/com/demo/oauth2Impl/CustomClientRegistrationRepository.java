package com.demo.oauth2Impl;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;

public class CustomClientRegistrationRepository implements ClientRegistrationRepository {

	@Override
	public ClientRegistration findByRegistrationId(String registrationId) {
		// TODO find the registration id from database
		return null;
	}

}
