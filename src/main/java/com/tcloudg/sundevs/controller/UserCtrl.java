package com.tcloudg.sundevs.controller;

import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.tcloudg.sundevs.exception.ResourceNotFoundException;
import com.tcloudg.sundevs.model.User;
import com.tcloudg.sundevs.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1")
@Api(value = "TCloudG User REST API for SunDevs", description = "Allow a client to perform a CRUD on a REST API for users")
public class UserCtrl {
    
    @Autowired
    private UserService userService;

    @ApiOperation(value = "View a list of registered users", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list")})
	@GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

	@ApiOperation(value = "Get user by id", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved user"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException{
    	return ResponseEntity.ok().body(userService.findById(userId));
    }

	@ApiOperation(value = "Save a user", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully saved user") })
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) throws ResponseStatusException{
    	return ResponseEntity.ok(userService.createUser(user));
    }

	@ApiOperation(value = "Update a user", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated user"),
	@ApiResponse(code = 404, message = "The user id does not exist") })
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId,
        @Valid @RequestBody User userDetails) throws ResourceNotFoundException {
        return ResponseEntity.ok(userService.updateUser(userId, userDetails));
    }

	@ApiOperation(value = "Delete a user", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully deleted user"),
	@ApiResponse(code = 404, message = "The user id does not exist") })
    @DeleteMapping("/users/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId)
    throws ResourceNotFoundException {
        return userService.deleteUser(userId);
    }
}
