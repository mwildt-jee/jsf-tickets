package de.hsw.jee.sample.tickets.controller.register;

import java.util.Objects;
import java.util.Optional;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import de.hsw.jee.sample.tickets.model.User;
import de.hsw.jee.sample.tickets.service.UserService;

@ManagedBean
@ViewScoped
public class Register {

	private String username;
	private String password;
	private String repassword;
	
	@Inject 
	private UserService userService;
	
	public String register() {
	
		Optional<User> userOpt = userService.findByUsername(username);
		if(userOpt.isPresent()) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username not available.", ""));
			return null;
		}
		
		if(!Objects.equals(password, repassword)) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Password confirmation does not match given password.", ""));
			return null;
		}
		
		if(null == password || password.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "password may not be empty.", ""));
			return null;
		}
		
		userService.createUser(username, password);
		FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "User has been created.", ""));
		return "login";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	
}
