package com.brunofernandes.apispringboot.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.brunofernandes.apispringboot.entities.PasswordChange;
import com.brunofernandes.apispringboot.entities.User;
import com.brunofernandes.apispringboot.entities.UserResponse;
import com.brunofernandes.apispringboot.services.TokenService;
import com.brunofernandes.apispringboot.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService service;

	@Autowired
	private TokenService tokenService;

	@GetMapping
	public ResponseEntity<List<User>> findAll(
			@RequestHeader(value = "x-access-token", defaultValue = "") String token) {
		List<User> list = service.findAll();
		tokenService.verifyToken(token);
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id,
			@RequestHeader(value = "x-access-token", defaultValue = "") String token) {
		User obj = service.findById(id);
		tokenService.verifyToken(token);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getUser_id())
				.toUri();
		return ResponseEntity.created(uri).body(obj);
	}

	@PostMapping(value = "/login")
	public ResponseEntity<UserResponse> findByNameAndPassword(@RequestBody User obj) {
		User user = service.findByNameAndPassword(obj.getUser_name(), obj.getUser_password());
		UserResponse resp = new UserResponse(user.getUser_id(), user.getUser_name(), user.getUser_email(),
				user.getUser_full_name());
		String token = tokenService.generateToken(resp);
		return ResponseEntity.ok().header("x-access-token", token)
				.header("Access-Control-Expose-Headers", "x-access-token").body(resp);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<UserResponse> update(@PathVariable Long id, @RequestBody UserResponse obj) {
		UserResponse resp = service.update(id, obj);
		String token = tokenService.generateToken(resp);
		return ResponseEntity.ok().header("x-access-token", token)
				.header("Access-Control-Expose-Headers", "x-access-token").body(resp);
	}

	@PutMapping(value = "/change-password/{id}")
	public ResponseEntity<Void> changePassword(@PathVariable Long id, @RequestBody PasswordChange obj) {
		service.changePassword(id, obj.getUser_former_password(), obj.getUser_password());
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
