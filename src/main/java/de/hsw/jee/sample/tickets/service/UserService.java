package de.hsw.jee.sample.tickets.service;

import java.util.Objects;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import de.hsw.jee.sample.tickets.model.User;

@ApplicationScoped
public class UserService {
	
	@Inject
	private UserRepository userRepository;
	
	public Optional<User> findByUsername(String username){
		return this.userRepository.findByUsername(username);
	}
	
	public boolean checkPassword(User u, String password) {
		return Objects.equals(u.getPassword(), password);
	}

	@Transactional
	public User createUser(String username, String password) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		return userRepository.save(user);
	}

}
