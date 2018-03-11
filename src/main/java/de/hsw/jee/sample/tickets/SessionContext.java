package de.hsw.jee.sample.tickets;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import de.hsw.jee.sample.tickets.model.User;

@Named
@SessionScoped
public class SessionContext implements Serializable{

	private User currentUser = null;
	
	public boolean isLoggedIn(){
		return currentUser != null;
	}
	
	public void login(User user){
		FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Login successful", user.getUsername() + " has been logged in."));
		System.out.println("Login user with username " + user.getUsername());
		currentUser = user;
	}
	
	public User getUser(){
		return currentUser;
	}
	
	public String logout(){
		currentUser = null;
		FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Logout successful", ""));
		return "login";
	}
	
}
