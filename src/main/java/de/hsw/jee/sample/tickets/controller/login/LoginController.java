package de.hsw.jee.sample.tickets.controller.login;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import de.hsw.jee.sample.tickets.SessionContext;
import de.hsw.jee.sample.tickets.model.User;
import de.hsw.jee.sample.tickets.service.UserService;

@Named
@ApplicationScoped
public class LoginController {

	@Inject
	private LoginRequest loginRequest;
	
	@Inject 
	private UserService userService;
	
	@Inject
	private SessionContext sessionContext;
	
	public String login() {
	
		Optional<User> userOpt = userService.findByUsername(loginRequest.getUsername());
		
		if(!userOpt.isPresent()) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unable to Login, user is unknown", ""));
			return null;
		}
		
		if(!userService.checkPassword(userOpt.get(), loginRequest.getPassword())) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unable to Login, password incorrect", ""));
			return null;
		} 
		
		sessionContext.login(userOpt.get());
		return "home";
	}
	
}
