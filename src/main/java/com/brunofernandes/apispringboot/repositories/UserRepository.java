package com.brunofernandes.apispringboot.repositories;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.brunofernandes.apispringboot.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query(value = "SELECT * FROM User u WHERE u.user_name = ?1 AND u.user_password = ?2", nativeQuery = true)
	public Optional<User> findByNameAndPassword(String user_name, String user_password);

	@Query(value = "SELECT * FROM User u WHERE u.user_name = ?1", nativeQuery = true)
	public Optional<User> findByName(String user_name);
	
	@Query(value = "SELECT * FROM User u WHERE u.user_id = ?1 AND u.user_password = ?2", nativeQuery = true)
	public Optional<User> findByIdAndPassword(Long user_id, String user_former_password);
	
	@Query(value = "SELECT * FROM User u WHERE u.user_id = ?1", nativeQuery = true)
	public Optional<User> findById(Long user_id);

	@Transactional
	@Modifying
	@Query(value = "UPDATE User u SET u.user_password = ?2 WHERE u.user_id = ?1", nativeQuery = true)
	public void updatePassword(Long user_id, String user_password);
}
