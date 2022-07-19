package com.brunofernandes.apispringboot.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.brunofernandes.apispringboot.entities.User;
import com.brunofernandes.apispringboot.entities.UserResponse;
import com.brunofernandes.apispringboot.repositories.UserRepository;
import com.brunofernandes.apispringboot.services.exceptions.AuthenticationException;
import com.brunofernandes.apispringboot.services.exceptions.DatabaseException;
import com.brunofernandes.apispringboot.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private PasswordEncoder encoder;

	public List<User> findAll() {
		return repository.findAll();
	}

	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public User findByNameAndPassword(String user_name, String user_password) {
		Optional<User> obj = repository.findByName(user_name);
		if (encoder.matches(user_password, obj.get().getUser_password())) {
			return obj.orElseThrow(() -> new AuthenticationException());
		} else {
			throw new AuthenticationException();
		}
	}

	public User insert(User obj) {
		List<User> list = this.findAll();
		for (User u : list) {
			if (u.getUser_email().equals(obj.getUser_email()) || u.getUser_name().equals(obj.getUser_name())) {
				throw new DatabaseException("Email or username already registered");
			}
		}
		obj.setUser_password(encoder.encode(obj.getUser_password()));
		return repository.save(obj);
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	@SuppressWarnings("deprecation")
	public UserResponse update(Long id, UserResponse obj) {
		try {
			User entity = repository.getOne(id);
			UserResponse resp = new UserResponse(entity.getUser_id(), entity.getUser_name(), entity.getUser_email(),
					entity.getUser_full_name());
			List<User> list = this.findAll();
			for (User u : list) {
				if (u.getUser_email().equals(obj.getUser_email()) || u.getUser_name().equals(obj.getUser_name())) {
					if (u.getUser_id() == id) {
						UpdateDataU(entity, obj);
						UpdateDataUR(resp, obj);
						repository.save(entity);
						return resp;
					} else {
						throw new DatabaseException("Email or username already registered");
					}
				}
			}
			UpdateDataU(entity, obj);
			UpdateDataUR(resp, obj);
			repository.save(entity);
			return resp;
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void UpdateDataU(User entity, UserResponse obj) {
		entity.setUser_name(obj.getUser_name());
		entity.setUser_email(obj.getUser_email());
		entity.setUser_full_name(obj.getUser_full_name());
	}

	private void UpdateDataUR(UserResponse entity, UserResponse obj) {
		entity.setUser_name(obj.getUser_name());
		entity.setUser_email(obj.getUser_email());
		entity.setUser_full_name(obj.getUser_full_name());
	}

	public void changePassword(Long user_id, String user_former_password, String user_password) {
		try {
			Optional<User> obj = repository.findById(user_id);
			if (encoder.matches(user_former_password, obj.get().getUser_password())) {
				if (!obj.isEmpty()) {
					repository.updatePassword(user_id, encoder.encode(user_password));
				} else {
					throw new DatabaseException("Invalid former password");
				}
			}
		} catch (EntityNotFoundException e) {
			throw new DatabaseException("Invalid former password");
		}
	}
}
