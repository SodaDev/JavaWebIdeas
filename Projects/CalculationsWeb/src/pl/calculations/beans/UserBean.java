package pl.calculations.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import pl.calculations.factory.Factory;

@ManagedBean
public class UserBean {
    private String name;  
    private String password;
    private NewUserDataController newUserData;
  
    public UserBean() {
    	this.newUserData = new NewUserDataController();
    }

	public String getName() {  
    return name;  
    }  
  
    public void setName(String name) {  
    this.name = name;  
    }  
  
    public String getPassword() {  
    return password;  
    }  
  
    public void setPassword(String password) {  
    this.password = password;  
    }  
    
    public NewUserDataController getNewUserData() {
		return newUserData;
	}

	public void setNewUserData(NewUserDataController newUserData) {
		this.newUserData = newUserData;
	}

	public boolean checkPassword(){
		if(!Factory.getInstance().checkUserLoginAndPassword(getName(), getPassword())){
			FacesContext context = FacesContext.getCurrentInstance();  
			FacesMessage warningMessage = new FacesMessage("Wrong login or password!");
					     warningMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
    		context.addMessage(null, warningMessage);    	
    		return false;
		}
		return true;
    }
	
	public void createUser(){
		FacesContext context = FacesContext.getCurrentInstance();
		if (Factory.getInstance().createUser(newUserData.getLogin(), newUserData.getName(), newUserData.getSurname(), 
				   newUserData.geteMail(), newUserData.getCellPhone(), newUserData.getPassword())) {
			FacesMessage confirmMessage = new FacesMessage(newUserData.getLogin() + " is born! :)");
				         confirmMessage.setSeverity(FacesMessage.SEVERITY_INFO);
    		context.addMessage(null, confirmMessage);
		    RequestContext.getCurrentInstance().execute("registerDlg.hide()");
		} else {
			FacesMessage confirmMessage = new FacesMessage("One of the properties is already taken by one of our users");
	         confirmMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
	        context.addMessage(null, confirmMessage);
		}
	}
}  
