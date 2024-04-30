package com.springboot.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springboot.rest.webservices.restfulwebservices.jpa.UserRepository;

@RestController
public class UserJpaResource {

	private UserRepository repository;

	public UserJpaResource(UserRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers() {
		return repository.findAll();
	}

	@GetMapping("/jpa/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		Optional<User> user = repository.findById(id);
		
		if (user.isEmpty())
			throw new UserNotFoundException("id:" + id);

		return user.get();
	}

	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		repository.deleteById(id);
	}

	@GetMapping("/jpa/users/{id}/posts ")
	public List<Post> retrievePostForUser(@PathVariable int id) {
		Optional<User> user = repository.findById(id);
		
		if (user.isEmpty())
			throw new UserNotFoundException("id:" + id);
		
		return user.get().getPosts();
	}

	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		User savedUser = repository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(null).build();
	}
}
