package com.apap.tutorial8.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.apap.tutorial8.model.UserRoleModel;
import com.apap.tutorial8.repository.UserRoleDb;

@Service
public class UserRoleServiceImpl implements UserRoleService {
	@Autowired
	private UserRoleDb userDb;
	
	@Override
	public UserRoleModel addUser(UserRoleModel user) {
		// TODO Auto-generated method stub
		String pass = encrypt(user.getPassword());
		user.setPassword(pass);
		return userDb.save(user);
	}

	@Override
	public String encrypt(String password) {
		// TODO Auto-generated method stub
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hasehdPassword = passwordEncoder.encode(password);
		return hasehdPassword;
	}

	@Override
	public UserRoleModel findUserByUsername(String name) {
		// TODO Auto-generated method stub
		return userDb.findByUsername(name);
	}

	@Override
	public void changePassword(UserRoleModel user, String newPassword) {
		// TODO Auto-generated method stub
		String pass=encrypt(newPassword);
		user.setPassword(pass);
		userDb.save(user);
	}

}
