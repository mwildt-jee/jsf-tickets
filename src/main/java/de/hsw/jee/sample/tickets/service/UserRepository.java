package de.hsw.jee.sample.tickets.service;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import de.hsw.jee.sample.tickets.model.User;

@ApplicationScoped
@Transactional
public class UserRepository {
	
	@PersistenceContext(unitName = "tickets")
	private EntityManager entityManager;
	
	public Optional<User> findByUsername(String username){
		try {
			TypedQuery<User> query = entityManager.createQuery("select u from User u where u.username = :username", User.class);
			query.setParameter("username", username);
			return Optional.of(query.getSingleResult());
		} catch (NoResultException e) {
			return Optional.empty();
		}
	}

	public User save(User user) {
		entityManager.persist(user);
		return user;
	}
	
}