package com.tcloudg.sundevs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tcloudg.sundevs.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
	Optional<User> findById(Long userId);
	User findByFirstName(String string);	
}