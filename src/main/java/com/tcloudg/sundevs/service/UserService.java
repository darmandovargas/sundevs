package com.tcloudg.sundevs.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import com.tcloudg.sundevs.exception.ResourceNotFoundException;
import com.tcloudg.sundevs.model.User;

public interface UserService {
	List<User> findAll();
	User findById(Long userId) throws ResourceNotFoundException;
	User createUser(User user) throws ResponseStatusException;
	User updateUser(Long userId, User userDetails) throws ResourceNotFoundException;
	Map<String, Boolean> deleteUser(Long userId) throws ResourceNotFoundException;	
}
