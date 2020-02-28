package com.tcloudg.sundevs.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.tcloudg.sundevs.exception.ResourceNotFoundException;
import com.tcloudg.sundevs.model.User;
import com.tcloudg.sundevs.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Override
	public List<User> findAll() {
		return userRepo.findAll();
	}
	
	@Override
	public User findById(Long userId) throws ResourceNotFoundException {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
        return user;
	}

	@Override
	public User createUser(User user) throws ResponseStatusException{
		// Zipcode max length validation
    	if(user.getZip().length()>10) {
    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Zipcode should have maximun 10 characters");    		
        }
    	
    	userRepo.save(user);
    	
        return user;
	}

	@Override
	public User updateUser(Long userId, User userDetails) throws ResourceNotFoundException {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

        // Zipcode max length validation
    	if(user.getZip().length()>10) {
    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Zipcode should have maximun 10 characters");    		
        }
        
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setAddress(userDetails.getAddress());
        user.setCity(userDetails.getCity());
        user.setState(userDetails.getState());
        user.setZip(userDetails.getZip());
        
        final User updatedUser = userRepo.save(user);
        return updatedUser;
	}

	@Override
	public Map<String, Boolean> deleteUser(Long userId) throws ResourceNotFoundException{
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
        userRepo.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
	}	
}
